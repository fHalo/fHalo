package com.facebook.halo.framework.json;

import static com.facebook.halo.framework.formatter.StringUtils.isBlank;
import static java.util.Collections.unmodifiableSet;
import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.facebook.halo.framework.annotation.Facebook;
import com.facebook.halo.framework.exception.FacebookJsonMappingException;
import com.facebook.halo.framework.formatter.ReflectionUtils.FieldWithAnnotation;

public class BaseJsonMapper {// implements JsonMappingErrorHandler{

	/**
	 * We call this instance's
	 * {@link JsonMappingErrorHandler#handleMappingError(String)} method on
	 * mapping failure so client code can decide how to handle the problem.
	 */
	protected JsonMappingErrorHandler jsonMappingErrorHandler;
//	protected static Logger logger; // = Logger.getLogger(DefaultJsonMapper.class.getName());
	
	public BaseJsonMapper() {
	}
	
	protected <T> T createInstance(Class<T> type) {
		String errorMessage = "Unable to create an instance of " + type + ". Please make sure that if it's a nested class, is marked 'static'. " + "It should have a no-argument constructor.";

		try {
			Constructor<T> defaultConstructor = type.getDeclaredConstructor();

			if (defaultConstructor == null) throw new FacebookJsonMappingException("Unable to find a default constructor for " + type);

			// Allows protected, private, and package-private constructors to be invoked
			defaultConstructor.setAccessible(true);
			return defaultConstructor.newInstance();
		} catch (Exception e) {
			throw new FacebookJsonMappingException(errorMessage, e);
		}
	}
	
	protected String getFacebookFieldName(Logger logger, FieldWithAnnotation<Facebook> fieldWithAnnotation) {
		String facebookFieldName = fieldWithAnnotation.getAnnotation().value();
		Field field = fieldWithAnnotation.getField();

		// If no Facebook field name was specified in the annotation, assume
		// it's the same name as the Java field
		if (isBlank(facebookFieldName)) {
			if (logger.isLoggable(FINEST)) logger.finest("No explicit Facebook field name found for " + field + ", so defaulting to the field name itself (" + field.getName() + ")");

			facebookFieldName = field.getName();
		}

		return facebookFieldName;
	}
	
	/**
	   * Finds any Facebook JSON fields that are mapped to more than 1 Java field.
	   * 
	   * @param fieldsWithAnnotation
	   *          Java fields annotated with the {@code Facebook} annotation.
	   * @return Any Facebook JSON fields that are mapped to more than 1 Java field.
	   */
	  protected Set<String> facebookFieldNamesWithMultipleMappings(Logger logger, List<FieldWithAnnotation<Facebook>> fieldsWithAnnotation) {
	    Map<String, Integer> facebookFieldsNamesWithOccurrenceCount = new HashMap<String, Integer>();
	    Set<String> facebookFieldNamesWithMultipleMappings = new HashSet<String>();

	    // Get a count of Facebook field name occurrences for each
	    // @Facebook-annotated field
	    for (FieldWithAnnotation<Facebook> fieldWithAnnotation : fieldsWithAnnotation) {
	      String fieldName = getFacebookFieldName(logger, fieldWithAnnotation);
	      int occurrenceCount =
	          facebookFieldsNamesWithOccurrenceCount.containsKey(fieldName) ? facebookFieldsNamesWithOccurrenceCount
	            .get(fieldName) : 0;
	      facebookFieldsNamesWithOccurrenceCount.put(fieldName, occurrenceCount + 1);
	    }

	    // Pull out only those field names with multiple mappings
	    for (Entry<String, Integer> entry : facebookFieldsNamesWithOccurrenceCount.entrySet())
	      if (entry.getValue() > 1)
	        facebookFieldNamesWithMultipleMappings.add(entry.getKey());

	    return unmodifiableSet(facebookFieldNamesWithMultipleMappings);
	  }
	

	  /**
	   * Dumps out a log message when one of a multiple-mapped Facebook field name
	   * JSON-to-Java mapping operation fails.
	   * 
	   * @param facebookFieldName
	   *          The Facebook field name.
	   * @param fieldWithAnnotation
	   *          The Java field to map to and its annotation.
	   * @param json
	   *          The JSON that failed to map to the Java field.
	   */
	  protected void logMultipleMappingFailedForField(Logger logger, String facebookFieldName, FieldWithAnnotation<Facebook> fieldWithAnnotation, String json) {
	    if (!logger.isLoggable(FINER))
	      return;

	    Field field = fieldWithAnnotation.getField();

	    if (logger.isLoggable(FINER))
	      logger.finer("Could not map '" + facebookFieldName + "' to " + field.getDeclaringClass().getSimpleName() + "."
	          + field.getName() + ", but continuing on because '" + facebookFieldName
	          + "' is mapped to multiple fields in " + field.getDeclaringClass().getSimpleName() + ". JSON is " + json);
	  }
	  
	  /**
	   * Given a {@code json} value of something like {@code MyValue} or {@code 123}
	   * , return a representation of that value of type {@code type}.
	   * <p>
	   * This is to support non-legal JSON served up by Facebook for API calls like
	   * {@code Friends.get} (example result: {@code [222333,1240079]}).
	   * 
	   * @param <T>
	   *          The Java type to map to.
	   * @param json
	   *          The non-legal JSON to map to the Java type.
	   * @param type
	   *          Type token.
	   * @return Java representation of {@code json}.
	   * @throws FacebookJsonMappingException
	   *           If an error occurs while mapping JSON to Java.
	   */
	  @SuppressWarnings("unchecked")
	  protected <T> T toPrimitiveJavaType(String json, Class<T> type) {

	    if (String.class.equals(type)) {
	      // If the string starts and ends with quotes, remove them, since Facebook
	      // can serve up strings surrounded by quotes.
	      if (json.length() > 1 && json.startsWith("\"") && json.endsWith("\"")) {
	        json = json.replaceFirst("\"", "");
	        json = json.substring(0, json.length() - 1);
	      }

	      return (T) json;
	    }

	    if (Integer.class.equals(type) || Integer.TYPE.equals(type))
	      return (T) new Integer(json);
	    if (Boolean.class.equals(type) || Boolean.TYPE.equals(type))
	      return (T) new Boolean(json);
	    if (Long.class.equals(type) || Long.TYPE.equals(type))
	      return (T) new Long(json);
	    if (Double.class.equals(type) || Double.TYPE.equals(type))
	      return (T) new Double(json);
	    if (Float.class.equals(type) || Float.TYPE.equals(type))
	      return (T) new Float(json);
	    if (BigInteger.class.equals(type))
	      return (T) new BigInteger(json);
	    if (BigDecimal.class.equals(type))
	      return (T) new BigDecimal(json);

	    if (jsonMappingErrorHandler.handleMappingError(json, type, null))
	      return null;

	    throw new FacebookJsonMappingException("Don't know how to map JSON to " + type
	        + ". Are you sure you're mapping to the right class? " + "Offending JSON is '" + json + "'.");
	  }
	  
	  /**
	 * Is the given JSON equivalent to the empty object (<code>{}</code>)?
	 * 
	 * @param json
	 *            The JSON to check.
	 * @return {@code true} if the JSON is equivalent to the empty object,
	 *         {@code false} otherwise.
	 */
	  
	protected boolean isEmptyObject(String json) {
		return "{}".equals(json);
	}

//	@Override
//	public boolean handleMappingError(String unmappableJson, Class<?> targetType, Exception e) {
//		return false;
//	}
}
