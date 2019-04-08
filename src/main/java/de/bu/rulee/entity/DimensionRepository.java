package de.bu.rulee.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Store for {@link RuleEntity}s.
 * 
 * @author Philipp Buchholz
 */
public class DimensionRepository {

	@PersistenceContext(unitName = "rulee-ds")
	private EntityManager entityManager;

	public List<DimensionEntity> findAllDimensions() {
		TypedQuery<DimensionEntity> query = entityManager //
				.createNamedQuery(DimensionEntity.FIND_ALL_DIMENSIONS, DimensionEntity.class);
		return query.getResultList();
	}

	public List<RuleEntity> findAllRules() {
		TypedQuery<RuleEntity> query = entityManager //
				.createNamedQuery(RuleEntity.FIND_ALL_RULES, RuleEntity.class);
		return query.getResultList();
	}

}
