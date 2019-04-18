package de.bu.rulee.entity;

import static de.bu.rulee.entity.LinkEntityTestBuilder.buildLowRiskLinkEntity;
import static de.bu.rulee.entity.LinkEntityTestBuilder.buildNewsletterReaderLinkEntity;

/**
 * Builds test data for {@link RuleEntity}s.
 * 
 * @author Philipp Buchholz
 */
public final class RuleEntityTestBuilder {

	private RuleEntityTestBuilder() {

	}

	public static RuleEntity buildLowRiskPersonRuleEntity() {
		return RuleEntity.builder() //
				.id(1L) //
				.name("Low risk persons rule") //
				.outcome("Person is low risk person") //
				.rootLink(buildLowRiskLinkEntity()) //
				.build();
	}

	public static RuleEntity buildLowRiskPersonAndNewsletterReaderRuleEntity() {
		RuleEntity lowRiskPersonAndNewsletterRuleEntity = buildLowRiskPersonRuleEntity();
		lowRiskPersonAndNewsletterRuleEntity.getRootLink().setNext(buildNewsletterReaderLinkEntity());
		return lowRiskPersonAndNewsletterRuleEntity;
	}

}
