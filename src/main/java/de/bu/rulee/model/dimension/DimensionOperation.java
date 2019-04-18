package de.bu.rulee.model.dimension;

/**
 * Represents an operation which is performed on a {@link Dimension} and a rule
 * value from that dimension. The {@link DimensionOperator} describes how the
 * selected value is evaluated against the {@link Dimension}.
 * 
 * @author Philipp Buchholz
 */
public class DimensionOperation {

	private Dimension dimension;
	private DimensionValue selectedDimensionValue;
	private DimensionOperator dimensionOperator;

	public Dimension getDimension() {
		return this.dimension;
	}

	public DimensionValue getSelectedDimensionValue() {
		return this.selectedDimensionValue;
	}

	public DimensionOperator getDimensionOperator() {
		return this.dimensionOperator;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private DimensionOperation dimensionOperation;

		public Builder() {
			this.dimensionOperation = new DimensionOperation();
		}

		public DimensionValueBuilder dimension(Dimension dimension) {
			this.dimensionOperation.dimension = dimension;
			return new DimensionValueBuilder();
		}

		public Builder dimensionOperator(DimensionOperator dimensionOperator) {
			this.dimensionOperation.dimensionOperator = dimensionOperator;
			return this;
		}

		public DimensionOperation build() {
			return this.dimensionOperation;
		}

		public class DimensionValueBuilder {

			public Builder direct(DimensionValue dimensionValue) {

				/* DimensionValue must be available in Dimension. */
				if (!dimensionOperation.dimension.hasDimensionValue(dimensionValue.getName())) {
					throw new RuntimeException("DimensionValue not in Dimension. Cannot build DimensionOperation.");
				}

				dimensionOperation.selectedDimensionValue = dimensionValue;
				return Builder.this;
			}

			public Builder findAndSelect(String dimensionValueName) {
				DimensionValue dimensionValue = dimensionOperation.dimension.findDimensionValue(dimensionValueName);
				dimensionOperation.selectedDimensionValue = dimensionValue;
				return Builder.this;
			}

		}

	}

}
