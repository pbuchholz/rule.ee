package de.bu.rulee.model;

import static de.bu.rulee.model.LinkTestBuilder.buildLowRiskLink;
import static de.bu.rulee.model.LinkTestBuilder.buildNewsletterReaderLink;

/**
 * Builds {@link Rule} instances for test purposes.
 * 
 * @author Philipp Buchholz
 */
public final class RuleTestBuilder {

	private RuleTestBuilder() {

	}

	public static Rule buildLowRiskPersonAndNewsletterReaderRule() {
		Rule lowRiskPersonAndNewsletterReaderRule = buildLowRiskPersonRule();
		lowRiskPersonAndNewsletterReaderRule.getRootLink().setNext(buildNewsletterReaderLink() //
				.build());
		return lowRiskPersonAndNewsletterReaderRule;

	}

	public static Rule buildLowRiskPersonRule() {
		return Rule.builder() //
				.name("Low risk persons rule") //
				.outcome("Person is low risk person") //
				.rootLink(buildLowRiskLink() //
						.build()) //
				.build();
	}

}
