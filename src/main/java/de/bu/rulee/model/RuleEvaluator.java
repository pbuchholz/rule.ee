package de.bu.rulee.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author Philipp Buchholz
 */
public interface RuleEvaluator {

	public class RuleEvaluationResult {
		private boolean successful;
		private String result;

		public static RuleEvaluationResult buildUnsuccessful() {
			return RuleEvaluationResult.builder() //
					.successful(false) //
					.build();
		}

		public boolean isSuccessful() {
			return this.successful;
		}

		public String getResult() {
			return this.result;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
		}

		public static Builder builder() {
			return new Builder();
		}

		public static class Builder {

			private RuleEvaluationResult ruleEvaluationResult;

			public Builder() {
				this.ruleEvaluationResult = new RuleEvaluationResult();
			}

			public Builder successful(boolean successful) {
				this.ruleEvaluationResult.successful = successful;
				return this;
			}

			public Builder result(String result) {
				this.ruleEvaluationResult.result = result;
				return this;
			}

			public RuleEvaluationResult build() {
				return this.ruleEvaluationResult;
			}

		}
	}

	/**
	 * Evaluates the given {@link Rule} against the candidate object.
	 * 
	 * @param rule
	 * @param candidate
	 * @return {@link RuleEvaluationResult} containing information whether
	 *         evaluation was successful or not and the result of the evaluation.
	 * @throws RuleEvaluationException
	 */
	RuleEvaluationResult evaluateRule(Rule rule, Object candidate) throws RuleEvaluationException;

}
