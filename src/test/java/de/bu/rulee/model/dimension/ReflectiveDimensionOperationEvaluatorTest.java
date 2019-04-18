package de.bu.rulee.model.dimension;

import static de.bu.rulee.model.dimension.DimensionTestBuilder.buildInsurancesDimension;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;

import de.bu.rulee.model.Insurance;
import de.bu.rulee.model.Insurance.InsuranceType;
import de.bu.rulee.model.Person;

public class ReflectiveDimensionOperationEvaluatorTest {

	@Test
	public void testEvaluateHierarchicalDimension() throws DimensionalInspectionException {

		ReflectiveDimensionOperationEvaluator evaluator = new ReflectiveDimensionOperationEvaluator();
		String dimensionalValue = evaluator.evaluate(this.buildCarInsuranceDimensionOperation(),
				this.buildTestPerson());

		assertNotNull("DimensionalValue for CarInsurance has not been found.", dimensionalValue);
		assertEquals("DimensionalValue has wrong value.", InsuranceType.CAR_INSURANCE.name(), dimensionalValue);

	}

	private Person buildTestPerson() {
		return new Person("Test", "Test", 30, true, //
				new Insurance(InsuranceType.CAR_INSURANCE, UUID.randomUUID()));
	}

	private DimensionOperation buildCarInsuranceDimensionOperation() {
		return DimensionOperation.builder() //
				.dimension(buildInsurancesDimension())//
				.findAndSelect("car-insurance")//
				.dimensionOperator(DimensionOperator.EQ)//
				.build();
	}

}
