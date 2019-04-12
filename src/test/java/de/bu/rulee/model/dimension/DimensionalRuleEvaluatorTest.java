package de.bu.rulee.model.dimension;

import static de.bu.rulee.model.LinkTestBuilder.buildLowRiskLink;
import static de.bu.rulee.model.LinkTestBuilder.buildNewsletterReaderLink;
import static de.bu.rulee.model.dimension.DimensionTestBuilder.buildAgeGroupsDimension;
import static de.bu.rulee.model.dimension.DimensionTestBuilder.buildMarketingChannelsDimension;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.bu.rulee.model.Person;
import de.bu.rulee.model.Rule;
import de.bu.rulee.model.RuleEvaluationException;
import de.bu.rulee.model.RuleEvaluator.RuleEvaluationResult;

@RunWith(MockitoJUnitRunner.class)
public class DimensionalRuleEvaluatorTest {

	@Spy
	private DimensionOperationEvaluator dimensionOperationEvaluator = new ReflectiveDimensionOperationEvaluator();

	@InjectMocks
	private DimensionalRuleEvaluator evaluator = new DimensionalRuleEvaluator();

	@Test
	public void whenEvaluateLowRiskAndNewsletterReaderThenRuleNotSuccessful() throws RuleEvaluationException {
		Rule rule = Rule.builder() //
				.name("Low risk persons rule") //
				.outcome("Person is low risk person") //
				.rootLink(buildLowRiskLink(buildAgeGroupsDimension()) //
						.next(buildNewsletterReaderLink(buildMarketingChannelsDimension()) //
								.build())
						.build()) //
				.build();

		RuleEvaluationResult result = evaluator.evaluateRule(rule, new Person("Low", "Risk", 33, false));

		assertNotNull("RuleEvaluationResult was null.", result);
		assertFalse("Expected rule to fail when evaluate newsletter reader.", result.isSuccessful());
	}

	@Test
	public void whenEvaluateLowRiskRuleThenRuleSuccessful() throws RuleEvaluationException {
		Rule rule = Rule.builder() //
				.name("Low risk persons rule") //
				.outcome("Person is low risk person") //
				.rootLink(buildLowRiskLink(buildAgeGroupsDimension()) //
						.build()) //
				.build();

		RuleEvaluationResult result = evaluator.evaluateRule(rule, new Person("Low", "Risk", 33, false));

		assertNotNull("RuleEvaluationResult was null.", result);
		assertTrue("Expected rule to evaluate successful.", result.isSuccessful());
	}

}
