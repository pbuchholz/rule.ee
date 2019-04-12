package de.bu.rulee.model.dimension;

import javax.enterprise.context.Dependent;

import de.bu.rulee.entity.DimensionEntity;
import de.bu.rulee.service.Mapper;

@Dependent
public class DimensionMapper implements Mapper<DimensionEntity, Dimension> {

	@Override
	public Dimension map(DimensionEntity input) {
		Dimension.Builder builder = Dimension.builder() //
				.name(input.getName());
		input.getDimensionValues().forEach((dimensionValue) -> {
			builder.dimensionValue(DimensionValue.builder() //
					.name(dimensionValue.getName()) //
					.value(dimensionValue.getValue()) //
					.build());
		});

		return builder.build();
	}

	@Override
	public DimensionEntity mapReverse(DimensionEntity output) {

		// TODO Currently not implemented!
		return null;

	}

}
