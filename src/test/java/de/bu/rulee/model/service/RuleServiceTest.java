package de.bu.rulee.model.service;

import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildInsurancesDimensionEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildRiskGroupsDimensionEntity;
import static de.bu.rulee.entity.RuleEntityTestBuilder.buildLowRiskPersonAndNewsletterReaderRuleEntity;
import static de.bu.rulee.entity.RuleEntityTestBuilder.buildLowRiskPersonRuleEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.bu.rulee.entity.DimensionRepository;
import de.bu.rulee.model.Rule;
import de.bu.rulee.model.dimension.Dimension;
import de.bu.rulee.model.dimension.DimensionMapper;
import de.bu.rulee.service.LinkMapper;
import de.bu.rulee.service.RuleMapper;
import de.bu.rulee.service.RuleService;

/**
 * Contains tests for {@link RuleService}.
 * 
 * @author Philipp Buchholz
 */
@RunWith(MockitoJUnitRunner.class)
public class RuleServiceTest {

	@Mock
	private DimensionRepository dimensionRepository;

	@Spy
	private DimensionMapper dimensionMapper;

	@Spy
	@InjectMocks
	private LinkMapper linkMapper;

	private RuleService ruleService;

	@Before
	public void setupDimensionRepository() {
		when(dimensionRepository.findAllDimensions()).thenReturn(Stream
				.of(buildInsurancesDimensionEntity(), buildRiskGroupsDimensionEntity()).collect(Collectors.toList()));

		when(dimensionRepository.findAllRules()) //
				.thenReturn(Stream.of(buildLowRiskPersonRuleEntity(), buildLowRiskPersonAndNewsletterReaderRuleEntity())
						.collect(Collectors.toList()));

		ruleService = new RuleService(dimensionMapper, new RuleMapper(linkMapper), dimensionRepository);
	}

	@Test
	public void shouldReturnAllDimensions() {
		List<Dimension> allDimensions = ruleService.retrieveAllDimensions();

		assertNotNull("No DimensionEntities has been retrieved.", allDimensions);
		assertEquals("Wrong count of DimensionEntities has been retrieved.", 2, allDimensions.size());
	}

	@Test
	public void shouldReturnAllRules() {
		List<Rule> allRules = ruleService.retrieveAllRules();

		assertNotNull("No rules has been retrieved.", allRules);
		assertEquals("Wrong count of RuleEntities has been retrieved.", 2, allRules.size());
	}

}
