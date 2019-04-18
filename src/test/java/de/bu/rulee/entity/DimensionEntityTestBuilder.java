package de.bu.rulee.entity;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.bu.rulee.model.Insurance.InsuranceType;

/**
 * Builds {@link DimensionEntity}s for testing purposes.
 * 
 * @author Philipp Buchholz
 */
public final class DimensionEntityTestBuilder {

	private DimensionEntityTestBuilder() {

	}

	public static DimensionValueEntity buildCarInsuranceDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(1L) //
				.name("car-insurance") //
				.value(InsuranceType.CAR_INSURANCE.name()) //
				.build();
	}

	public static DimensionValueEntity buildHealthInsuranceDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(2L) //
				.name("health-insurance") //
				.value(InsuranceType.HEALTH_INSURANCE.name()) //
				.build();
	}

	public static DimensionValueEntity buildHouseholdInsuranceDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(3L) //
				.name("household-insurance") //
				.value(InsuranceType.HOUSEHOLD_INSURANCE.name()) //
				.build();
	}

	public static DimensionValueEntity buildLowRiskPersonsDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(4L) //
				.name("low risk persons") //
				.value("30") //
				.build();
	}

	public static DimensionValueEntity buildMiddleRiskPersonsDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(4L) //
				.name("middle risk persons") //
				.value("45") //
				.build();
	}

	public static DimensionValueEntity buildHighRiskPersonsDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(4L) //
				.name("high risk persons") //
				.value("65") //
				.build();
	}

	public static DimensionValueEntity buildNewsletterReaderDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(5L) //
				.name("newsletter") //
				.value("true") //
				.build();
	}

	public static DimensionValueEntity buildPhoneDimensionValueEntity() {
		return DimensionValueEntity.builder() //
				.id(6L) //
				.name("phone") //
				.value("true") //
				.build();
	}

	public static DimensionEntity buildMarketingChannelsDimensionEntity() {
		return new DimensionEntity(3L, "marketing-channels", Stream //
				.of(buildNewsletterReaderDimensionValueEntity(), //
						buildPhoneDimensionValueEntity()) //
				.collect(Collectors.toList()));
	}

	public static DimensionEntity buildInsurancesDimensionEntity() {
		return new DimensionEntity(1L, "insurances", Stream //
				.of(buildCarInsuranceDimensionValueEntity(), //
						buildHealthInsuranceDimensionValueEntity(), //
						buildHouseholdInsuranceDimensionValueEntity()) //
				.collect(Collectors.toList()));
	}

	public static DimensionEntity buildRiskGroupsDimensionEntity() {
		return new DimensionEntity(2L, "risk-groups", Stream //
				.of(buildLowRiskPersonsDimensionValueEntity(), //
						buildMiddleRiskPersonsDimensionValueEntity(), //
						buildHighRiskPersonsDimensionValueEntity()) //
				.collect(Collectors.toList()));
	}

}
