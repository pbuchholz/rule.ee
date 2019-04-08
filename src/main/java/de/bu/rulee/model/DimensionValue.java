package de.bu.rulee.model;

/**
 * Represents the value of a Dimension which is evaluated against a Dimensional.
 * 
 * @author Philipp Buchholz
 */
public class DimensionValue {

	private String name;
	private String value;

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private DimensionValue dimensionValue;

		public Builder() {
			this.dimensionValue = new DimensionValue();
		}

		public Builder name(String name) {
			this.dimensionValue.name = name;
			return this;
		}

		public Builder value(String value) {
			this.dimensionValue.value = value;
			return this;
		}

		public DimensionValue build() {
			return this.dimensionValue;
		}

	}

}
