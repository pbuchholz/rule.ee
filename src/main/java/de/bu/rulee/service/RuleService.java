package de.bu.rulee.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.bu.rulee.entity.DimensionRepository;
import de.bu.rulee.model.Dimension;
import de.bu.rulee.model.DimensionMapper;
import de.bu.rulee.model.Rule;

public class RuleService {

	@Inject
	private DimensionRepository ruleStore;

	@Inject
	private DimensionMapper dimensionMapper;

	@Inject
	private RuleMapper ruleMapper;

	public List<Dimension> retrieveAllDimensions() {
		return this.ruleStore.findAllDimensions().stream() //
				.map(dimensionMapper::map) //
				.collect(Collectors.toList());
	}

	public List<Rule> retrieveAllRules() {
		return this.ruleStore.findAllRules().stream() //
				.map(ruleMapper::map) //
				.collect(Collectors.toList());
	}

}
