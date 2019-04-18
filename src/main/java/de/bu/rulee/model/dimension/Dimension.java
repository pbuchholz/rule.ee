package de.bu.rulee.model.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A {@link Dimension} represents a named range of possible values which are
 * used to evaluate rules against.
 * 
 * @author Philipp Buchholz
 */
public class Dimension {

	private String name;

	private List<DimensionValue> dimensionValues;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DimensionValue> getDimensionValues() {
		return this.dimensionValues;
	}

	public void setDimensionValues(List<DimensionValue> dimensionValues) {
		this.dimensionValues = dimensionValues;
	}

	public DimensionValue findDimensionValue(String dimensionValueName) {
		return this.filterFor(dimensionValueName) //
				.get();
	}

	public boolean hasDimensionValue(String dimensionValueName) {
		return this.filterFor(dimensionValueName) //
				.isPresent();
	}

	private Optional<DimensionValue> filterFor(String dimensionValueName) {
		return this.dimensionValues.stream() //
				.filter((v) -> v.getName().equals(dimensionValueName)) //
				.findFirst();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Dimension dimension;

		public Builder() {
			this.dimension = new Dimension();
		}

		public Builder name(String name) {
			this.dimension.name = name;
			return this;
		}

		public Builder dimensionValue(DimensionValue dimensionValue) {
			if (null == this.dimension.dimensionValues) {
				this.dimension.dimensionValues = new ArrayList<>();
			}
			this.dimension.dimensionValues.add(dimensionValue);
			return this;
		}

		public Dimension build() {
			return this.dimension;
		}

	}

}
