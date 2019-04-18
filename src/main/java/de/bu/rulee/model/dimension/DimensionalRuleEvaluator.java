package de.bu.rulee.model.dimension;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.commons.lang3.math.NumberUtils;

import de.bu.rulee.model.Link;
import de.bu.rulee.model.LogicalOperator;
import de.bu.rulee.model.LogicalResult;
import de.bu.rulee.model.LogicalResult.LogicalResultPart;
import de.bu.rulee.model.Rule;
import de.bu.rulee.model.RuleEvaluationException;
import de.bu.rulee.model.RuleEvaluator;
import de.bu.rulee.model.dimension.EvaluationMethod.EvaluationMethods;

@EvaluationMethod(method = EvaluationMethods.REFLECTIVE_DIMENSIONAL)
public class DimensionalRuleEvaluator implements RuleEvaluator {

	@Inject
	private DimensionOperationEvaluator dimensionOperationEvaluator;

	@Override
	public RuleEvaluationResult evaluateRule(Rule rule, Object candidate) throws RuleEvaluationException {
		assert (null != rule);
		assert (null != candidate);
		assert (null != rule.getRootLink());

		/* First evaluate result of each link. */
		LogicalResult<Link> logicalResult = evaluateLogicalResults(rule.getRootLink(), candidate);

		/* Deduce Or. */
		logicalResult = this.deduceOr(logicalResult);

		/* Calculate final result. */
		boolean finalResult = this.calculateFinalResult(logicalResult);

		return (finalResult) ? RuleEvaluationResult.builder() //
				.successful(true) //
				.result(rule.getOutcome()) //
				.build() : RuleEvaluationResult.buildUnsuccessful();
	}

	private LogicalResult<Link> evaluateLogicalResults(Link link, Object candidate) throws RuleEvaluationException {
		LogicalResult.Builder<Link> builder = LogicalResult.<Link>builder();
		do {
			boolean result = this.evaluateDimensionOperation(link.getDimensionOperation(), candidate);
			builder.logicalResultPart(result, link);
			link = link.getNext();
		} while (null != link);
		return builder.build();
	}

	private boolean calculateFinalResult(LogicalResult<Link> logicalResult) {
		boolean finalResult = true;
		for (LogicalResultPart<Link> logicalResultPart : logicalResult.logicalResultParts()) {
			finalResult = finalResult && logicalResultPart.result();
		}
		return finalResult;
	}

	private LogicalResult<Link> deduceOr(LogicalResult<Link> logicalResult) {
		LogicalResult.Builder<Link> deduced = LogicalResult.builder();

		Iterator<LogicalResultPart<Link>> iterator = logicalResult.logicalResultParts().iterator();

		while (iterator.hasNext()) {
			LogicalResultPart<Link> logicalResultPart = iterator.next();

			if (logicalResultPart.evaluated().getLogicalOperator() == LogicalOperator.OR) {
				LogicalResultPart<Link> orLogicalResultPart = iterator.next();
				LogicalResultPart<Link> deducedLogicalResultPart = LogicalResultPart.<Link>builder() //
						.result(logicalResultPart.result() || orLogicalResultPart.result()) //
						.evaluated((logicalResultPart.result()) ? logicalResultPart.evaluated()
								: orLogicalResultPart.evaluated()) //
						.build();

				if (!iterator.hasNext()) {
					/* Reset LogicalOperator if on last element . */
					deducedLogicalResultPart.evaluated().setLogicalOperator(null);
				}

				deduced.logicalResultPart(deducedLogicalResultPart);
			} else {
				deduced.logicalResultPart(logicalResultPart);
			}
		}

		LogicalResult<Link> deducedLogicalResult = deduced.build();

		/* Deduce it recursively. */
		while (this.canFurtherDeduceOr(deducedLogicalResult)) {
			deducedLogicalResult = this.deduceOr(deducedLogicalResult);
		}

		return deducedLogicalResult;
	}

	private boolean canFurtherDeduceOr(LogicalResult<Link> deducedLogicalResult) {
		return deducedLogicalResult.logicalResultParts().size() > 1
				&& (deducedLogicalResult.logicalResultParts().stream() //
						.filter(filter -> filter.evaluated().getLogicalOperator() == LogicalOperator.OR) //
						.count() > 0);
	}

	private boolean evaluateDimensionOperation(DimensionOperation dimensionOperation, Object candidate)
			throws RuleEvaluationException {

		try {
			String dimensionalValue = this.dimensionOperationEvaluator.evaluate(dimensionOperation, candidate);
			assert (null != dimensionalValue);

			DimensionOperator operator = dimensionOperation.getDimensionOperator();
			String selectedValue = dimensionOperation.getSelectedDimensionValue().getValue();

			switch (dimensionOperation.getDimensionOperator()) {
			case EQ:
				return dimensionalValue.equals(selectedValue);
			case NEQ:
				return !dimensionalValue.equals(selectedValue);
			case GT:
			case LT:
				if (this.bothAreNumbers(selectedValue, dimensionalValue)) {
					BigDecimal selectedBd = NumberUtils.createBigDecimal(selectedValue);
					BigDecimal dimensionalBd = NumberUtils.createBigDecimal(dimensionalValue);

					return (operator == DimensionOperator.GT) ? dimensionalBd.compareTo(selectedBd) == 1
							: dimensionalBd.compareTo(selectedBd) == -1;
				}
			}

			return false;
		} catch (DimensionalInspectionException e) {
			throw new RuleEvaluationException(e);
		}
	}

	private boolean bothAreNumbers(String left, String right) {
		return NumberUtils.isCreatable(left) && NumberUtils.isCreatable(right);
	}

}
