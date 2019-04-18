package de.bu.rulee.model;

import static de.bu.rulee.model.dimension.DimensionTestBuilder.buildAgeGroupsDimension;
import static de.bu.rulee.model.dimension.DimensionTestBuilder.buildMarketingChannelsDimension;

import de.bu.rulee.model.dimension.DimensionOperation;
import de.bu.rulee.model.dimension.DimensionOperator;

public class LinkTestBuilder {

	private LinkTestBuilder() {

	}

	public static Link.Builder buildLowRiskLink() {
		return Link.builder() //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(buildAgeGroupsDimension())//
						.findAndSelect("low risk persons") //
						.dimensionOperator(DimensionOperator.GT)//
						.build()) //
				.logicalOperator(LogicalOperator.AND) //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(buildAgeGroupsDimension())//
						.findAndSelect("middle risk persons") //
						.dimensionOperator(DimensionOperator.LT)//
						.build());
	}

	public static Link.Builder buildNewsletterReaderLink() {
		return Link.builder() //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(buildMarketingChannelsDimension())//
						.findAndSelect("newsletter") //
						.dimensionOperator(DimensionOperator.EQ)//
						.build());
	}
}
