package de.bu.rulee.model.dimension;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface EvaluationMethod {

	EvaluationMethods method();

	public enum EvaluationMethods {
		REFLECTIVE_DIMENSIONAL
	}

}
