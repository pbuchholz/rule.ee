package de.bu.rulee.model;

import de.bu.rulee.model.dimension.DimensionOperation;

public class Link {

	private DimensionOperation dimensionOperation;

	private LogicalOperator logicalOperator;

	private Link next;

	public boolean linked() {
		return null != next;
	}

	public DimensionOperation getDimensionOperation() {
		return this.dimensionOperation;
	}

	public void setDimensionOperation(DimensionOperation dimensionOperation) {
		this.dimensionOperation = dimensionOperation;
	}

	public LogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Link link;

		public Builder() {
			this.link = new Link();
		}

		public Builder dimensionOperation(DimensionOperation dimensionOperation) {
			this.link.dimensionOperation = dimensionOperation;
			return this;
		}

		public Builder logicalOperator(LogicalOperator logicalOperator) {
			this.link.logicalOperator = logicalOperator;
			return this;
		}

		public Builder next(Link next) {
			this.link.next = next;
			return this;
		}

		public Link build() {
			return this.link;
		}

	}

}
