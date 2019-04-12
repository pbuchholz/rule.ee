package de.bu.rulee.service;

import javax.inject.Inject;

import de.bu.rulee.entity.DimensionOperationEntity;
import de.bu.rulee.entity.LinkEntity;
import de.bu.rulee.model.Link;
import de.bu.rulee.model.dimension.DimensionMapper;
import de.bu.rulee.model.dimension.DimensionOperation;

public class LinkMapper implements Mapper<LinkEntity, Link> {

	@Inject
	private DimensionMapper dimensionMapper;

	public Link map(LinkEntity input) {
		return Link.builder() //
				.dimensionOperation(mapDimensionOperation(input.getDimensionOperation())) //
				.logicalOperator(input.getLogicalOperator()) //
				.next((input.linked()) ? map(input.getNext()) : null) //
				.build();
	}

	private DimensionOperation mapDimensionOperation(DimensionOperationEntity dimensionOperationEntity) {
		return DimensionOperation.builder() //
				.dimension(dimensionMapper.map(dimensionOperationEntity.getDimension())) //
				.selectDimensionValue(dimensionOperationEntity.getSelectedDimensionValue()) //
				.dimensionOperator(dimensionOperationEntity.getOperator()) //
				.build();

	}

	public LinkEntity mapReverse(LinkEntity output) {
		// TODO Not implemented yet.
		return null;
	}

}
