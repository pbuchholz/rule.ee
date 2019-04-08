package de.bu.rulee.model;

public class Rule {

	private String name;

	private String outcome;

	private Link rootLink;

	public String getName() {
		return name;
	}

	public String getOutcome() {
		return outcome;
	}

	public Link getRootLink() {
		return rootLink;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Rule rule = new Rule();

		public Builder name(String name) {
			this.rule.name = name;
			return this;
		}

		public Builder outcome(String outcome) {
			this.rule.outcome = outcome;
			return this;
		}

		public Builder rootLink(Link rootLink) {
			this.rule.rootLink = rootLink;
			return this;
		}

		public Rule build() {
			return this.rule;
		}

	}
}
