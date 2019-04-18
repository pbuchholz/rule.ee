package de.bu.rulee.entity;

import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildCarInsuranceDimensionValueEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildInsurancesDimensionEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildLowRiskPersonsDimensionValueEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildMarketingChannelsDimensionEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildMiddleRiskPersonsDimensionValueEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildNewsletterReaderDimensionValueEntity;
import static de.bu.rulee.entity.DimensionEntityTestBuilder.buildRiskGroupsDimensionEntity;

import de.bu.rulee.model.dimension.DimensionOperator;;

/**
 * Builds tests data for {@link DimensionOperationEntity}.
 * 
 * @author Philipp Buchholz
 */
public final class DimensionOperationEntityTestBuilder {

	private DimensionOperationEntityTestBuilder() {

	}

	public static DimensionOperationEntity buildCarInsuranceDimensionOperationEntity() {
		return DimensionOperationEntity.builder() //
				.id(1L) //
				.dimension(buildInsurancesDimensionEntity()) //
				.operator(DimensionOperator.EQ) //
				.selectDimensionValue(buildCarInsuranceDimensionValueEntity()) //
				.build();
	}

	public static DimensionOperationEntity buildLowRiskPersonDimensinonOperationEntity() {
		return DimensionOperationEntity.builder() //
				.id(2L) //
				.dimension(buildRiskGroupsDimensionEntity()) //
				.operator(DimensionOperator.GT) //
				.selectDimensionValue(buildLowRiskPersonsDimensionValueEntity()) //
				.build();
	}

	public static DimensionOperationEntity buildMiddleRiskPersonDimensinonOperationEntity() {
		return DimensionOperationEntity.builder() //
				.id(3L) //
				.dimension(buildRiskGroupsDimensionEntity()) //
				.operator(DimensionOperator.LT) //
				.selectDimensionValue(buildMiddleRiskPersonsDimensionValueEntity()) //
				.build();
	}

	public static DimensionOperationEntity buildNewsletterReaderDimensionOperationEntity() {
		return DimensionOperationEntity.builder() //
				.id(4L) //
				.dimension(buildMarketingChannelsDimensionEntity()) //
				.operator(DimensionOperator.EQ) //
				.selectDimensionValue(buildNewsletterReaderDimensionValueEntity()) //
				.build();
	}

}
