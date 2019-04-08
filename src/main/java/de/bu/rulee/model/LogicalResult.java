package de.bu.rulee.model;

import java.util.ArrayList;
import java.util.List;

public class LogicalResult<T> {

	public static class LogicalResultPart<T> {
		private boolean result;
		private T evaluated;

		public boolean result() {
			return this.result;
		}

		public T evaluated() {
			return this.evaluated;
		}

		private LogicalResultPart(boolean result, T evaluated) {
			this.result = result;
			this.evaluated = evaluated;
		}

		private LogicalResultPart() {

		}

		public static <T> Builder<T> builder() {
			return new Builder<>();
		}

		public static class Builder<T> {

			private LogicalResultPart<T> logicalResultPart;

			public Builder() {
				this.logicalResultPart = new LogicalResultPart<>();
			}

			public Builder<T> result(boolean result) {
				this.logicalResultPart.result = result;
				return this;
			}

			public Builder<T> evaluated(T evaluated) {
				this.logicalResultPart.evaluated = evaluated;
				return this;
			}

			public LogicalResultPart<T> build() {
				return this.logicalResultPart;
			}

		}
	}

	private List<LogicalResultPart<T>> logicalResultParts;

	public List<LogicalResultPart<T>> logicalResultParts() {
		return this.logicalResultParts;
	}

	public static <T> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T> {

		private LogicalResult<T> logicalResult;

		private Builder() {
			this.logicalResult = new LogicalResult<>();
		}

		public Builder<T> logicalResultPart(LogicalResultPart<T> logicalResultPart) {
			if (null == logicalResult.logicalResultParts) {
				logicalResult.logicalResultParts = new ArrayList<>();
			}

			logicalResult.logicalResultParts.add(logicalResultPart);
			return this;
		}

		public Builder<T> logicalResultPart(boolean result, T evaluated) {
			if (null == logicalResult.logicalResultParts) {
				logicalResult.logicalResultParts = new ArrayList<>();
			}

			logicalResult.logicalResultParts.add(new LogicalResultPart<>(result, evaluated));
			return this;
		}

		public LogicalResult<T> build() {
			return this.logicalResult;
		}

	}

}
