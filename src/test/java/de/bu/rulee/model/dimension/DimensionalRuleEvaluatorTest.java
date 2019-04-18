package de.bu.rulee.model.dimension;

import static de.bu.rulee.model.RuleTestBuilder.buildLowRiskPersonAndNewsletterReaderRule;
import static de.bu.rulee.model.RuleTestBuilder.buildLowRiskPersonRule;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.bu.rulee.model.Person;
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
		RuleEvaluationResult result = evaluator.evaluateRule(buildLowRiskPersonAndNewsletterReaderRule(),
				new Person("Low", "Risk", 33, false));

		assertNotNull("RuleEvaluationResult was null.", result);
		assertFalse("Expected rule to fail when evaluate newsletter reader.", result.isSuccessful());
	}

	@Test
	public void whenEvaluateLowRiskRuleThenRuleSuccessful() throws RuleEvaluationException {
		RuleEvaluationResult result = evaluator.evaluateRule(buildLowRiskPersonRule(),
				new Person("Low", "Risk", 33, false));

		assertNotNull("RuleEvaluationResult was null.", result);
		assertTrue("Expected rule to evaluate successful.", result.isSuccessful());
	}

}
