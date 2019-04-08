package de.bu.rulee.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dimensionvalues")
public class DimensionValueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "value")
	private String value;

	public DimensionValueEntity() {

	}

	public DimensionValueEntity(long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private DimensionValueEntity dimensionValueEntity;

		public Builder() {
			this.dimensionValueEntity = new DimensionValueEntity();
		}

		public Builder id(long id) {
			this.dimensionValueEntity.id = id;
			return this;
		}

		public Builder name(String name) {
			this.dimensionValueEntity.name = name;
			return this;
		}

		public Builder value(String value) {
			this.dimensionValueEntity.value = value;
			return this;
		}

		public DimensionValueEntity build() {
			return this.dimensionValueEntity;
		}

	}

}
