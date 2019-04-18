package de.bu.rulee.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.bu.rulee.model.Rule;
import de.bu.rulee.model.RuleEvaluationException;
import de.bu.rulee.model.RuleEvaluator.RuleEvaluationResult;
import de.bu.rulee.model.dimension.Dimension;

@Stateless
@Path("rules")
public class RuleBoundary {

	@Inject
	private RuleService ruleService;

	@GET
	@Path("/dimensions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dimension> getAllDimensions() {
		return ruleService.retrieveAllDimensions();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rule> getAllRules() {
		return ruleService.retrieveAllRules();
	}

	@POST
	@Path("/evaluations")
	@Consumes(MediaType.APPLICATION_JSON)
	public <T> Response evaluateRule(String ruleName, T candidate) {
		
		try {
			RuleEvaluationResult result = this.ruleService.evaluateRule(ruleName, candidate);
			return Response.ok(result).build();
		} catch (RuleEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// TODO Response code handling.
		}
		return null;
	}

}
