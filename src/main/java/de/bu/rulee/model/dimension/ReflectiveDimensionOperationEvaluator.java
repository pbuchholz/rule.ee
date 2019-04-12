package de.bu.rulee.model.dimension;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Evaluates {@link DimensionOperation}s using reflection.
 * 
 * @author Philipp Buchholz
 */
public class ReflectiveDimensionOperationEvaluator implements DimensionOperationEvaluator {

	@Override
	public String evaluate(DimensionOperation dimensionOperation, Object candidate)
			throws DimensionalInspectionException {
		Optional<String> dimensionalValue = this.readRecursive(candidate, //
				dimensionOperation.getDimension().getName(), //
				candidate.getClass().getPackage());
		return dimensionalValue.get();
	}

	private Optional<String> readRecursive(Object candidate, String dimensionName, Package basePackage) {
		try {
			Optional<String> dimensionValue = this.read(candidate, dimensionName);

			if (!dimensionValue.isPresent()) {
				for (Field field : candidate.getClass().getDeclaredFields()) {
					Class<?> fieldType = field.getType();

					if (partOf(basePackage, fieldType.getPackage())) {

						field.setAccessible(true);
						Object value = field.get(candidate);

						if (Collection.class.isAssignableFrom(fieldType)) {

							/* Iterate over child collection. */
							Collection<?> collection = Collection.class.cast(value);
							collection.forEach((c) -> readRecursive(c, dimensionName, basePackage));
						} else {
							dimensionValue = this.readRecursive(value, dimensionName, basePackage);
						}
					}
				}
			}
			return dimensionValue;
		} catch (ReflectiveOperationException | DimensionalInspectionException e) {
			throw new RuntimeException(e);
		}

	}

	private boolean partOf(Package basePackage, Package candidatePackage) {
		return Objects.nonNull(candidatePackage) && candidatePackage.getName().contains(basePackage.getName());
	}

	private Optional<String> read(Object candidate, String dimensionName) throws DimensionalInspectionException {
		for (Field dimensionalField : readDimensionalFields(candidate)) {
			Dimensional dimensional = dimensionalField.getAnnotation(Dimensional.class);
			if (dimensional.dimension().equals(dimensionName)) {
				dimensionalField.setAccessible(true);
				try {
					return Optional.of(String.valueOf(dimensionalField.get(candidate)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new DimensionalInspectionException(e);
				}
			}
		}

		return Optional.empty();
	}

	/**
	 * Reads and returns a {@link List} of {@link Dimensional} fields from the
	 * passed in candidate object.
	 * 
	 * @param candidate The candidate object to read {@link Dimensional} fields
	 *                  from.
	 * @return List of {@link Dimensional} fields.
	 */
	private List<Field> readDimensionalFields(Object candidate) {
		return Stream.of(candidate.getClass().getDeclaredFields()) //
				.filter(field -> field.isAnnotationPresent(Dimensional.class)) //
				.collect(Collectors.toList());
	}

}
