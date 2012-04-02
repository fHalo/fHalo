package com.facebook.halo.framework.core;

import static com.facebook.halo.framework.formatter.StringUtils.urlEncode;

import com.facebook.halo.framework.common.Parameter;
import com.facebook.halo.framework.exception.FacebookExceptionMapper;
import com.facebook.halo.framework.exception.FacebookJsonMappingException;
import com.facebook.halo.framework.exception.JsonException;
import com.facebook.halo.framework.json.JsonObject;

public class DefaultFacebookUtils {

  /**
   * Ensures that {@code parameter} isn't {@code null} or an empty string.
   * 
   * @param parameterName
   *          The name of the parameter (to be used in exception message).
   * @param parameter
   *          The parameter to check.
   * @throws IllegalArgumentException
   *           If {@code parameter} is {@code null} or an empty string.
   */
	public static void verifyParameterPresence(String parameterName, String parameter) {
		verifyParameterPresence(parameterName, (Object) parameter);
		if (parameter.trim().length() == 0) 
			throw new IllegalArgumentException("The '" + parameterName + "' parameter cannot be an empty string.");
	}

  /**
   * Ensures that {@code parameter} isn't {@code null}.
   * 
   * @param parameterName
   *          The name of the parameter (to be used in exception message).
   * @param parameter
   *          The parameter to check.
   * @throws IllegalArgumentException
   *           If {@code parameter} is {@code null}.
   */
	public static void verifyParameterPresence(String parameterName, Object parameter) {
		if (parameter == null) throw new NullPointerException("The '" + parameterName + "' parameter cannot be null.");
	}

  /**
   * Generate the parameter string to be included in the Facebook API request.
   * 
   * @param parameters
   *          Arbitrary number of extra parameters to include in the request.
   * @return The parameter string to include in the Facebook API request.
   * @throws FacebookJsonMappingException
   *           If an error occurs when building the parameter string.
   */
	public static String toParameterString(Parameter... parameters) {

		StringBuilder parameterStringBuilder = new StringBuilder();
		boolean first = true;

		for (Parameter parameter : parameters) {
			if (first) first = false;
			else parameterStringBuilder.append("&");

			parameterStringBuilder.append(urlEncode(parameter.name));
			parameterStringBuilder.append("=");
			parameterStringBuilder.append(urlEncodedValueForParameterName(parameter.name, parameter.value));
		}

		return parameterStringBuilder.toString();
	}
//	
//	public static void throwFacebookResponseStatusExceptionIfNecessary(FacebookExceptionMapper graphFacebookExceptionMapper, String json) {
//		// If we have a legacy exception, throw it.
//		try {
//			// If the result is not an object, bail immediately.
//			if (!json.startsWith("{")) return;
//
//			JsonObject errorObject = new JsonObject(json);
//
//			if (errorObject == null || !errorObject.has(DefaultFacebookString.ERROR_ATTRIBUTE_NAME)) return;
//
//			JsonObject innerErrorObject = errorObject.getJsonObject(DefaultFacebookString.ERROR_ATTRIBUTE_NAME);
//
//			throw graphFacebookExceptionMapper.exceptionForTypeAndMessage(null, innerErrorObject.getString(DefaultFacebookString.ERROR_TYPE_ATTRIBUTE_NAME), innerErrorObject.getString(DefaultFacebookString.ERROR_MESSAGE_ATTRIBUTE_NAME));
//		} catch (JsonException e) {
//			throw new FacebookJsonMappingException("Unable to process the Facebook API response", e);
//		}
//	}
//	
//
//	public static void throwBatchFacebookResponseStatusExceptionIfNecessary(FacebookExceptionMapper legacyFacebookExceptionMapper, String json) {
//
//		try {
//			// If this is not an object, it's not an error response.
//			if (!json.startsWith("{")) return;
//
//			JsonObject errorObject = null;
//			try {
//				errorObject = new JsonObject(json);
//			} catch (JsonException e) {
//			}
//
//			if (errorObject == null || !errorObject.has(DefaultFacebookString.BATCH_ERROR_ATTRIBUTE_NAME) || !errorObject.has(DefaultFacebookString.BATCH_ERROR_DESCRIPTION_ATTRIBUTE_NAME)) return;
//
//			throw legacyFacebookExceptionMapper.exceptionForTypeAndMessage(errorObject.getInt(DefaultFacebookString.BATCH_ERROR_ATTRIBUTE_NAME), null, errorObject.getString(DefaultFacebookString.BATCH_ERROR_DESCRIPTION_ATTRIBUTE_NAME));
//		} catch (JsonException e) {
//			throw new FacebookJsonMappingException("Unable to process the Facebook API response", e);
//		}
//	}
	
  /**
   * Gets the URL-encoded version of the given {@code value} for the parameter
   * named {@code name}.
   * <p>
   * Includes special-case handling for access token parameters where we check
   * if the token is already URL-encoded - if so, we don't encode again. All
   * other parameter types are always URL-encoded.
   * 
   * @param name
   *          The name of the parameter whose value should be URL-encoded and
   *          returned.
   * @param value
   *          The value of the parameter which should be URL-encoded and
   *          returned.
   * @return The URL-encoded version of the given {@code value}.
   */
	protected static String urlEncodedValueForParameterName(String name, String value) {

		return DefaultFacebookString.ACCESS_TOKEN_PARAM_NAME.equals(name) && value.contains("%7C") ? value : urlEncode(value);
	}
}
