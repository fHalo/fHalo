package com.facebook.halo.framework.json;

public interface JsonMappingErrorHandler {
	
	/**
	 * This method will be called by {@code DefaultJsonMapper} if it encounters
	 * an error while attempting to map JSON to a Java object.
	 * <p>
	 * You may perform any behavior you'd like here in response to an error,
	 * e.g. logging it.
	 * <p>
	 * If the mapper should continue processing, return {@code true} and
	 * {@code null} will be mapped to the target type. If you would like the
	 * mapper to stop processing and throw
	 * {@link com.facebook.halo.framework.exception.FacebookJsonMappingException}, return
	 * {@code false}.
	 * 
	 * @param unmappableJson
	 *          The JSON that couldn't be mapped to a Java type.
	 * @param targetType
	 *          The Java type we were attempting to map to.
	 * @param e
	 *          The exception that occurred while performing the mapping
	 *          operation, or {@code null} if there was no exception.
	 * @return {@code true} to continue processing, {@code false} to throw a
	 *         {@link com.facebook.halo.framework.exception.FacebookJsonMappingException}.
	 */
	boolean handleMappingError(String unmappableJson, Class<?> targetType, Exception e);

}
