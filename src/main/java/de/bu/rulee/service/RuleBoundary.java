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

import de.bu.rulee.model.Dimension;
import de.bu.rulee.model.Rule;

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
	public <T> Response evaluateRule(Rule rule, T candidate) {

		// TODO Set HttpLink header to refer to actual evaluation

		// TODO
		return null;
	}

}
