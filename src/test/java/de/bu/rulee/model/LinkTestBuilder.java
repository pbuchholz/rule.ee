package de.bu.rulee.model;

import de.bu.rulee.model.Link;
import de.bu.rulee.model.LogicalOperator;
import de.bu.rulee.model.dimension.Dimension;
import de.bu.rulee.model.dimension.DimensionOperation;
import de.bu.rulee.model.dimension.DimensionOperator;

public class LinkTestBuilder {

	private LinkTestBuilder() {

	}

	public static Link.Builder buildLowRiskLink(Dimension ageGroupsDimension) {
		return Link.builder() //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(ageGroupsDimension)//
						.selectDimensionValue("low risk persons") //
						.dimensionOperator(DimensionOperator.GT)//
						.build()) //
				.logicalOperator(LogicalOperator.AND) //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(ageGroupsDimension)//
						.selectDimensionValue("middle risk persons") //
						.dimensionOperator(DimensionOperator.LT)//
						.build());
	}

	public static Link.Builder buildNewsletterReaderLink(Dimension marketingChannelsDimension) {
		return Link.builder() //
				.dimensionOperation(DimensionOperation.builder() //
						.dimension(marketingChannelsDimension)//
						.selectDimensionValue("newsletter") //
						.dimensionOperator(DimensionOperator.EQ)//
						.build());
	}
}
