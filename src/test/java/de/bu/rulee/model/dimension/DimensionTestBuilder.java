package de.bu.rulee.model.dimension;

import de.bu.rulee.model.Insurance.InsuranceType;

public final class DimensionTestBuilder {

	private DimensionTestBuilder() {

	}

	public static Dimension buildInsuranceTypeDimension() {
		return Dimension.builder() //
				.name("insurance-types") //
				.dimensionValue(DimensionValue.builder() //
						.name("car-insurance") //
						.value(InsuranceType.CAR_INSURANCE.name())//
						.build())
				.dimensionValue(DimensionValue.builder() //
						.name("health-insurance") //
						.value(InsuranceType.HEALTH_INSURANCE.name()) //
						.build())
				.dimensionValue(DimensionValue.builder() //
						.name("household-insurance") //
						.value(InsuranceType.HOUSEHOLD_INSURANCE.name()) //
						.build())
				.build();
	}

	public static Dimension buildMarketingChannelsDimension() {
		return Dimension.builder() //
				.name("marketing-channels") //
				.dimensionValue(DimensionValue.builder() //
						.name("newsletter") //
						.value("true") //
						.build()) //
				.dimensionValue(DimensionValue.builder() //
						.name("phone") //
						.value("true") //
						.build()) //
				.build();
	}

	public static Dimension buildAgeGroupsDimension() {
		return Dimension.builder() //
				.name("age-groups") //
				.dimensionValue(DimensionValue.builder() //
						.name("low risk persons") //
						.value("30") //
						.build()) //
				.dimensionValue(DimensionValue.builder() //
						.name("middle risk persons") //
						.value("45") //
						.build()) //
				.dimensionValue(DimensionValue.builder() //
						.name("high risk persons") //
						.value("65") //
						.build())
				.build();
	}

}
