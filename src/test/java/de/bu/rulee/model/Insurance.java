package de.bu.rulee.model;

import java.util.UUID;

import de.bu.rulee.model.dimension.Dimensional;

/**
 * Represents an {@link Insurance}.
 * 
 * @author Philipp Buchholz
 */
public class Insurance {

	public enum InsuranceType {
		HEALTH_INSURANCE, //
		CAR_INSURANCE, //
		HOUSEHOLD_INSURANCE
	}

	@Dimensional(dimension = "insurance-types")
	private InsuranceType insuranceType;

	private UUID policyIdentifier;

	public InsuranceType getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public UUID getPolicyIdentifier() {
		return policyIdentifier;
	}

	public void setPolicyIdentifier(UUID policyIdentifier) {
		this.policyIdentifier = policyIdentifier;
	}

	public Insurance(InsuranceType insuranceType, UUID policyIdentifier) {
		this.insuranceType = insuranceType;
		this.policyIdentifier = policyIdentifier;
	}

}
