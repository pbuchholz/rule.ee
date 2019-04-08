package de.bu.rulee.entity;

import javax.persistence.CascadeType;
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

import de.bu.rulee.model.LogicalOperator;

@Entity
@Table(name = "links")
public class LinkEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "dimensionoperation", referencedColumnName = "id")
	private DimensionOperationEntity dimensionOperation;

	@Column(name = "logicaloperator")
	@Enumerated(EnumType.STRING)
	private LogicalOperator logicalOperator;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "next", referencedColumnName = "id")
	private LinkEntity next;

	public boolean linked() {
		return null != next;
	}

	public LinkEntity() {

	}

	public LinkEntity(long id, DimensionOperationEntity dimensionOperation, LinkEntity next) {
		this.id = id;
		this.dimensionOperation = dimensionOperation;
		this.next = next;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DimensionOperationEntity getDimensionOperation() {
		return dimensionOperation;
	}

	public void setDimensionOperation(DimensionOperationEntity dimensionOperation) {
		this.dimensionOperation = dimensionOperation;
	}

	public LogicalOperator getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public LinkEntity getNext() {
		return next;
	}

	public void setNext(LinkEntity next) {
		this.next = next;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private LinkEntity linkEntity;

		public Builder() {
			this.linkEntity = new LinkEntity();
		}

		public Builder id(long id) {
			this.linkEntity.id = id;
			return this;
		}

		public Builder dimensionOperation(DimensionOperationEntity dimensionOperation) {
			this.linkEntity.dimensionOperation = dimensionOperation;
			return this;
		}

		public Builder logicalOperator(LogicalOperator logicalOperator) {
			this.linkEntity.logicalOperator = logicalOperator;
			return this;
		}

		public Builder next(LinkEntity next) {
			this.linkEntity.next = next;
			return this;
		}

		public LinkEntity build() {
			return this.linkEntity;
		}

	}

}
