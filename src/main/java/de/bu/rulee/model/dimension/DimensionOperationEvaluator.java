package de.bu.rulee.model.dimension;

public interface DimensionOperationEvaluator {

	/**
	 * Evaluates the passed in {@link DimensionOperation} against the passed in
	 * candidate object.
	 * 
	 * @param dimensionOperation
	 * @param candidate
	 * @return
	 * @throws DimensionalInspectionException
	 */
	String evaluate(DimensionOperation dimensionOperation, Object candidate) throws DimensionalInspectionException;

}
