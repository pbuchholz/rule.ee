package de.bu.rulee.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.bu.rulee.model.dimension.DimensionOperator;

@Entity
@Table(name = "dimensionoperations")
public class DimensionOperationEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "operator")
	@Enumerated(EnumType.STRING)
	private DimensionOperator operator;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dimension", referencedColumnName = "id")
	private DimensionEntity dimension;

	@Basic
	@Column(name = "selecteddimensionvalue")
	private String selectedDimensionValue;

	public String getSelectedDimensionValue() {
		return selectedDimensionValue;
	}

	public void setSelectedDimensionValue(String selectedDimensionValue) {
		this.selectedDimensionValue = selectedDimensionValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DimensionOperator getOperator() {
		return operator;
	}

	public void setOperator(DimensionOperator operator) {
		this.operator = operator;
	}

	public DimensionEntity getDimension() {
		return dimension;
	}

	public void setDimension(DimensionEntity dimension) {
		this.dimension = dimension;
	}

	public DimensionOperationEntity() {

	}

	public DimensionOperationEntity(long id, DimensionOperator operator, DimensionEntity dimension,
			String selectedValue) {
		this.id = id;
		this.operator = operator;
		this.dimension = dimension;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private DimensionOperationEntity dimensionOperationEntity;

		public Builder() {
			this.dimensionOperationEntity = new DimensionOperationEntity();
		}

		public Builder id(long id) {
			this.dimensionOperationEntity.id = id;
			return this;
		}

		public Builder operator(DimensionOperator operator) {
			this.dimensionOperationEntity.operator = operator;
			return this;
		}

		public Builder dimension(DimensionEntity dimension) {
			this.dimensionOperationEntity.dimension = dimension;
			return this;
		}

		public DimensionOperationEntity build() {
			return this.dimensionOperationEntity;
		}

	}

}