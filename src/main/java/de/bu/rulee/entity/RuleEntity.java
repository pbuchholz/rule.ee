package de.bu.rulee.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rules")
@NamedQueries({ @NamedQuery(name = RuleEntity.FIND_ALL_RULES, query = "SELECT rule from RuleEntity rule") })
public class RuleEntity {

	public static final String FIND_ALL_RULES = "findAllRules";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "outcome")
	private String outcome;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "rootlink", referencedColumnName = "id")
	private LinkEntity rootLink;

	public RuleEntity() {

	}

	public RuleEntity(long id, String name, String outcome, LinkEntity rootLink) {
		this.id = id;
		this.name = name;
		this.outcome = outcome;
		this.rootLink = rootLink;
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

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public LinkEntity getRootLink() {
		return rootLink;
	}

	public void setRootLink(LinkEntity rootLink) {
		this.rootLink = rootLink;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private RuleEntity ruleEntity;

		public Builder() {
			this.ruleEntity = new RuleEntity();
		}

		public Builder id(long id) {
			this.ruleEntity.id = id;
			return this;
		}

		public Builder name(String name) {
			this.ruleEntity.name = name;
			return this;
		}

		public Builder outcome(String outcome) {
			this.ruleEntity.outcome = outcome;
			return this;
		}

		public Builder rootLink(LinkEntity rootLink) {
			this.ruleEntity.rootLink = rootLink;
			return this;
		}

		public RuleEntity build() {
			return this.ruleEntity;
		}

	}

}
