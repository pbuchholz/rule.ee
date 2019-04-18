package de.bu.rulee.service;

import javax.inject.Inject;

import de.bu.rulee.entity.RuleEntity;
import de.bu.rulee.model.Rule;

public class RuleMapper implements Mapper<RuleEntity, Rule> {

	private LinkMapper linkMapper;

	@Inject
	public RuleMapper(LinkMapper linkMapper) {
		this.linkMapper = linkMapper;
	}

	@Override
	public Rule map(RuleEntity input) {
		return Rule.builder() //
				.name(input.getName()) //
				.outcome(input.getOutcome()) //
				.rootLink(linkMapper.map(input.getRootLink())) //
				.build();
	}

	@Override
	public RuleEntity mapReverse(RuleEntity output) {
		// TODO Auto-generated method stub
		return null;
	}

}
