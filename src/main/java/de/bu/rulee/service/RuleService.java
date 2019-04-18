package de.bu.rulee.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.bu.rulee.entity.DimensionEntity;
import de.bu.rulee.entity.DimensionRepository;
import de.bu.rulee.entity.RuleEntity;
import de.bu.rulee.model.Rule;
import de.bu.rulee.model.RuleEvaluationException;
import de.bu.rulee.model.RuleEvaluator;
import de.bu.rulee.model.RuleEvaluator.RuleEvaluationResult;
import de.bu.rulee.model.dimension.Dimension;
import de.bu.rulee.model.dimension.DimensionMapper;
import de.bu.rulee.model.dimension.EvaluationMethod;
import de.bu.rulee.model.dimension.EvaluationMethod.EvaluationMethods;

public class RuleService {

	@Inject
	private DimensionRepository ruleStore;

	@Inject
	private Mapper<DimensionEntity, Dimension> dimensionMapper;

	@Inject
	private Mapper<RuleEntity, Rule> ruleMapper;

	@Inject
	@EvaluationMethod(method = EvaluationMethods.REFLECTIVE_DIMENSIONAL)
	private RuleEvaluator ruleEvaluator;

	@Inject
	public RuleService(DimensionMapper dimensionMapper, RuleMapper ruleMapper,
			DimensionRepository dimensionRepository) {
		this.ruleMapper = ruleMapper;
		this.dimensionMapper = dimensionMapper;
		this.ruleStore = dimensionRepository;
	}

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

	public Rule retrieveOneRule(String name) {
		return ruleMapper.map(this.ruleStore.findOneRule(name));
	}

	public <T> RuleEvaluationResult evaluateRule(String ruleName, T candidate) throws RuleEvaluationException {
		Rule rule = this.retrieveOneRule(ruleName);
		return this.ruleEvaluator.evaluateRule(rule, candidate);
	}

}
