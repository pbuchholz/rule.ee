package de.bu.rulee.model.dimension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field of an object as provider of a value used to evaluate against a
 * {@link Dimension};
 * 
 * @author pbuchholz
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dimensional {

	/**
	 * The name of the {@link Dimension}.
	 * 
	 * @return
	 */
	String dimension();

}
