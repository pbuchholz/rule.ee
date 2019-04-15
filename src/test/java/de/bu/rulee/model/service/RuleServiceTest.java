package de.bu.rulee.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.bu.rulee.entity.DimensionEntityTestBuilder;
import de.bu.rulee.entity.DimensionRepository;
import de.bu.rulee.model.dimension.Dimension;
import de.bu.rulee.model.dimension.DimensionMapper;
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
	private RuleMapper ruleMapper;
	@Spy
	private DimensionMapper dimensionMapper;

	@InjectMocks
	private RuleService ruleService;

	@Before
	public void setupDimensionRepository() {
		when(dimensionRepository.findAllDimensions())
				.thenReturn(DimensionEntityTestBuilder.buildInsuranceDimensionEntities());
	}

	@Test
	public void shouldReturnAllDimensions() {
		List<Dimension> allDimensions = ruleService.retrieveAllDimensions();

		assertNotNull("No DimensionEntities has been retrieved.", allDimensions);
		assertEquals("Wrong count of DimensionEntities has been retrieved.", 1, allDimensions.size());
	}

}
