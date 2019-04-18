package de.bu.rulee.entity;

import static de.bu.rulee.entity.DimensionOperationEntityTestBuilder.buildLowRiskPersonDimensinonOperationEntity;
import static de.bu.rulee.entity.DimensionOperationEntityTestBuilder.buildMiddleRiskPersonDimensinonOperationEntity;
import static de.bu.rulee.entity.DimensionOperationEntityTestBuilder.buildNewsletterReaderDimensionOperationEntity;

import de.bu.rulee.model.LogicalOperator;

/**
 * Builds test data for {@link LinkEntity}.
 * 
 * @author Philipp Buchholz
 */
public final class LinkEntityTestBuilder {

	private LinkEntityTestBuilder() {

	}

	public static LinkEntity buildLowRiskLinkEntity() {
		return LinkEntity.builder() //
				.dimensionOperation(buildLowRiskPersonDimensinonOperationEntity()) //
				.logicalOperator(LogicalOperator.AND) //
				.dimensionOperation(buildMiddleRiskPersonDimensinonOperationEntity()) //
				.build();
	}

	public static LinkEntity buildNewsletterReaderLinkEntity() {
		return LinkEntity.builder() //
				.dimensionOperation(buildNewsletterReaderDimensionOperationEntity()) //
				.build();
	}

}
