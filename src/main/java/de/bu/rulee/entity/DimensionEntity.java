package de.bu.rulee.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Base entity for dimensions.
 * 
 * @author Philipp Buchholz
 */
@Entity
@Table(name = "dimensions")
@NamedQueries({
		@NamedQuery(name = DimensionEntity.FIND_ALL_DIMENSIONS, query = "SELECT dimension FROM DimensionEntity dimension") })
public class DimensionEntity {

	public static final String FIND_ALL_DIMENSIONS = "findAllDimensions";

	public DimensionEntity() {

	}

	public DimensionEntity(long id, String name, List<DimensionValueEntity> dimensionValues) {
		this.id = id;
		this.name = name;
		this.dimensionValues = dimensionValues;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Basic
	@Column(name = "name")
	private String name;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "dimension")
	private List<DimensionValueEntity> dimensionValues;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DimensionValueEntity> getDimensionValues() {
		return this.dimensionValues;
	}

	public void setDimensionValues(List<DimensionValueEntity> dimensionValues) {
		this.dimensionValues = dimensionValues;
	}

	public void addDimensionValue(DimensionValueEntity dimensionValue) {
		if (Objects.isNull(this.dimensionValues)) {
			this.dimensionValues = new ArrayList<>();
		}

		this.dimensionValues.add(dimensionValue);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private DimensionEntity dimensionEntity;

		public Builder id(long id) {
			this.dimensionEntity.id = id;
			return this;
		}

		public Builder name(String name) {
			this.dimensionEntity.name = name;
			return this;
		}

		public Builder dimensionValue(DimensionValueEntity dimensionValue) {
			dimensionEntity.addDimensionValue(dimensionValue);
			return this;
		}

		public DimensionEntity build() {
			return this.dimensionEntity;
		}

	}

}
