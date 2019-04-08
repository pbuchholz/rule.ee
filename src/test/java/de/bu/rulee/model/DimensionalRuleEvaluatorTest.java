package de.bu.rulee.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import de.bu.rulee.model.RuleEvaluator.RuleEvaluationResult;

@RunWith(MockitoJUnitRunner.class)
public class DimensionalRuleEvaluatorTest {

	@Test
	public void whenEvaluateLowRiskAndNewsletterReaderThenRuleNotSuccessful() throws RuleEvaluationException {
		Dimension ageGroupsDimension = this.buildAgeGroupsDimension();
		Dimension marketingChannelsDimension = this.buildMarketingChannelsDimension();
		DimensionalRuleEvaluator evaluator = new DimensionalRuleEvaluator();

		Rule rule = Rule.builder() //
				.name("Low risk persons rule") //
				.outcome("Person is low risk person") //
				.rootLink(this.buildLowRiskLink(ageGroupsDimension) //
						.next(this.buildNewsletterReaderLink(marketingChannelsDimension) //
								.build())
						.build()) //
				.build();

		RuleEvaluationResult result = evaluator.evaluateRule(rule, new Person("Low", "Risk", 33, false));

		assertNotNull("RuleEvaluationResult was null.", result);
		assertFalse("Expected rule to fail when evaluate newsletter reader.", result.isSuccessful());
	}

	@Test
	public void whenEvaluateLowRiskRuleThenRuleSuccessful() throws RuleEvaluationException {
		Dimension ageGroupsDimension = this.buildAgeGroupsDimension();
		DimensionalRuleEvaluator evaluator = new DimensionalRuleEvaluator();

		Rule rule = Rule.builder() //
				.name("Low risk persons rule") //
				.outcome("Person is low risk person") //
				.rootLink(this.buildLowRiskLink(ageGroupsDimension) //
						.build()) //
				.build();

		RuleEvaluationResult result = evaluator.evaluateRule(rule, new Person("Low", "Risk", 33, false));

		assertNotNull("RuleEvaluationResult was null.", result);
		assertTrue("Expected rule to evaluate successful.", result.isSuccessful());
	}

	private Link.Builder buildLowRiskLink(Dimension ageGroupsDimension) {
		return Link.builder() //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(ageGroupsDimension)//
						.selectDimensionValue("low risk persons") //
						.dimensionOperator(DimensionOperator.GT)//
						.build()) //
				.logicalOperator(LogicalOperator.AND) //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(ageGroupsDimension)//
						.selectDimensionValue("middle risk persons") //
						.dimensionOperator(DimensionOperator.LT)//
						.build());
	}

	private Link.Builder buildNewsletterReaderLink(Dimension marketingChannelsDimension) {
		return Link.builder() //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(marketingChannelsDimension)//
						.selectDimensionValue("newsletter") //
						.dimensionOperator(DimensionOperator.EQ)//
						.build());
	}

	private Dimension buildMarketingChannelsDimension() {
		return Dimension.builder() //
				.name("marketing-channels") //
				.dimensionValue(DimensionValue.builder() //
						.name("newsletter") //
						.value("true") //
						.build()) //
				.dimensionValue(DimensionValue.builder() //
						.name("phone") //
						.value("true") //
						.build()) //
				.build();
	}

	private Dimension buildAgeGroupsDimension() {
		return Dimension.builder() //
				.name("age-groups") //
				.dimensionValue(DimensionValue.builder() //
						.name("low risk persons") //
						.value("30") //
						.build()) //
				.dimensionValue(DimensionValue.builder() //
						.name("middle risk persons") //
						.value("45") //
						.build()) //
				.dimensionValue(DimensionValue.builder() //
						.name("high risk persons") //
						.value("65") //
						.build())
				.build();
	}

}
