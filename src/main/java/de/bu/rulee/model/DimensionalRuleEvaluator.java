package de.bu.rulee.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;

import de.bu.rulee.model.LogicalResult.LogicalResultPart;

public class DimensionalRuleEvaluator implements RuleEvaluator {

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
			boolean result = this.evaluateLink(link, candidate);
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

	private boolean evaluateLink(Link link, Object candidate) throws RuleEvaluationException {
		String dimensionalValue = this.inspectDimensional(link, candidate);
		assert (null != dimensionalValue);

		DimensionOperator operator = link.getDimensionOperation().getDimensionOperator();
		String selectedValue = link.getDimensionOperation().getSelectedDimensionValue().getValue();

		switch (link.getDimensionOperation().getDimensionOperator()) {
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
	}

	private boolean bothAreNumbers(String left, String right) {
		return NumberUtils.isCreatable(left) && NumberUtils.isCreatable(right);
	}

	private String inspectDimensional(Link link, Object candidate) throws RuleEvaluationException {
		String dimensionalValue = null;

		for (Field dimensionalField : readDimensionalFields(candidate)) {
			Dimensional dimensional = dimensionalField.getAnnotation(Dimensional.class);
			if (dimensional.dimension().equals(link.getDimensionOperation().getDimension().getName())) {
				dimensionalField.setAccessible(true);
				try {
					dimensionalValue = String.valueOf(dimensionalField.get(candidate));
					break;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuleEvaluationException("Could not inspect Dimensional of candidate.", e);
				}
			}
		}

		return dimensionalValue;
	}

	private List<Field> readDimensionalFields(Object candidate) {
		return Stream.of(candidate.getClass().getDeclaredFields()) //
				.filter(field -> field.isAnnotationPresent(Dimensional.class)) //
				.collect(Collectors.toList());
	}

}
