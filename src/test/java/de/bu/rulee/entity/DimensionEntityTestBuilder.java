package de.bu.rulee.entity;

import java.util.ArrayList;
import java.util.List;

import de.bu.rulee.model.Insurance.InsuranceType;

/**
 * Builds {@link DimensionEntity}s for testing purposes.
 * 
 * @author Philipp Buchholz
 */
public final class DimensionEntityTestBuilder {

	private DimensionEntityTestBuilder() {

	}

	public static List<DimensionEntity> buildInsuranceDimensionEntities() {
		DimensionEntity dimensionEntity = new DimensionEntity();

		DimensionValueEntity dimensionValueEntity = new DimensionValueEntity();
		dimensionValueEntity.setId(1L);
		dimensionValueEntity.setName("car-insurance");
		dimensionValueEntity.setValue(InsuranceType.CAR_INSURANCE.name());
		dimensionEntity.addDimensionValue(dimensionValueEntity);

		dimensionValueEntity = new DimensionValueEntity();
		dimensionValueEntity.setId(2L);
		dimensionValueEntity.setName("health-insurance");
		dimensionValueEntity.setValue(InsuranceType.HEALTH_INSURANCE.name());
		dimensionEntity.addDimensionValue(dimensionValueEntity);

		dimensionValueEntity = new DimensionValueEntity();
		dimensionValueEntity.setId(3L);
		dimensionValueEntity.setName("household-insurance");
		dimensionValueEntity.setValue(InsuranceType.HOUSEHOLD_INSURANCE.name());
		dimensionEntity.addDimensionValue(dimensionValueEntity);

		List<DimensionEntity> dimensionEntities = new ArrayList<>();
		dimensionEntities.add(dimensionEntity);

		return dimensionEntities;
	}

}
