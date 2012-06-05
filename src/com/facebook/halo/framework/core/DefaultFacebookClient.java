/*
 * Copyright (c) 2010-2011 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.facebook.halo.framework.core;

import static com.facebook.halo.framework.formatter.StringUtils.isBlank;
import static com.facebook.halo.framework.formatter.StringUtils.join;
import static com.facebook.halo.framework.formatter.StringUtils.trimToEmpty;
import static com.facebook.halo.framework.formatter.StringUtils.trimToNull;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.logging.Level.INFO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.facebook.halo.framework.batch.BatchRequest;
import com.facebook.halo.framework.batch.BatchResponse;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.common.BinaryAttachment;
import com.facebook.halo.framework.common.Parameter;
import com.facebook.halo.framework.core.WebRequestor.Response;
import com.facebook.halo.framework.exception.DefaultGraphFacebookExceptionMapper;
import com.facebook.halo.framework.exception.FacebookExceptionMapper;
import com.facebook.halo.framework.exception.FacebookJsonMappingException;
import com.facebook.halo.framework.exception.FacebookNetworkException;
import com.facebook.halo.framework.exception.JsonException;
import com.facebook.halo.framework.json.DefaultJsonMapper;
import com.facebook.halo.framework.json.JsonArray;
import com.facebook.halo.framework.json.JsonMapper;
import com.facebook.halo.framework.json.JsonObject;

/**
 * Default implementation of a <a
 * href="http://developers.facebook.com/docs/api">Facebook Graph API</a> client.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class DefaultFacebookClient extends BaseFacebookClient implements
		FacebookClient {

	/**
	 * Knows how to map Graph API exceptions to formal Java exception types.
	 */
	protected FacebookExceptionMapper graphFacebookExceptionMapper;

	/**
	 * Creates a Facebook Graph API client with no access token.
	 * <p>
	 * Without an access token, you can view and search public graph data but
	 * can't do much else.
	 */
	public DefaultFacebookClient() {
		this(null);
	}

	/**
	 * Creates a Facebook Graph API client with the given {@code accessToken}.
	 * 
	 * @param accessToken
	 *            A Facebook OAuth access token.
	 */
	public DefaultFacebookClient(String accessToken) {
		this(accessToken, new DefaultWebRequestor(), new DefaultJsonMapper());
	}

	/**
	 * Creates a Facebook Graph API client with the given {@code accessToken},
	 * {@code webRequestor}, and {@code jsonMapper}.
	 * 
	 * @param accessToken
	 *            : A Facebook OAuth access token.
	 * @param webRequestor
	 *            : The {@link WebRequestor} implementation to use for sending
	 *            requests to the API endpoint.
	 * @param jsonMapper
	 *            : The {@link JsonMapper} implementation to use for mapping API
	 *            response JSON to Java objects.
	 * @throws NullPointerException
	 *             : If {@code jsonMapper} or {@code webRequestor} is
	 *             {@code null}.
	 */
	public DefaultFacebookClient(String accessToken, WebRequestor webRequestor,
			JsonMapper jsonMapper) {
		super();

		DefaultFacebookUtils.verifyParameterPresence("jsonMapper", jsonMapper);
		DefaultFacebookUtils.verifyParameterPresence("webRequestor",
				webRequestor);

		this.accessToken = trimToNull(accessToken);
		this.webRequestor = webRequestor;
		this.jsonMapper = jsonMapper;
		graphFacebookExceptionMapper = createGraphFacebookExceptionMapper();

		illegalParamNames.addAll(Arrays.asList(new String[] {
				DefaultFacebookString.ACCESS_TOKEN_PARAM_NAME,
				DefaultFacebookString.METHOD_PARAM_NAME,
				DefaultFacebookString.FORMAT_PARAM_NAME }));
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#deleteObject(java.lang.String)
	 */
	@Override
	public boolean deleteObject(String object) {
		DefaultFacebookUtils.verifyParameterPresence("object", object);
		return "true".equals(makeRequest(object, true, true, null));
	}
	
	/**
	 *  @see com.facebook.halo.framework.core.FacebookClient#publishUndoLikes(java.lang.String)
	 */
	@Override
	public boolean publishUndoLikes(String connection) {
		DefaultFacebookUtils.verifyParameterPresence("connection", connection);
		return "true".equals(makeRequest(connection, true, true, null));
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#fetchConnection(java.lang.String,
	 *      java.lang.Class, com.facebook.halo.framework.client.Parameter[])
	 */
	@Override
	public <T> Connection<T> fetchConnection(String connection,
			Class<T> connectionType, Parameter... parameters) {
		DefaultFacebookUtils.verifyParameterPresence("connection", connection);
		DefaultFacebookUtils.verifyParameterPresence("connectionType",
				connectionType);
		return new Connection<T>(this, makeRequest(connection, parameters),
				connectionType);
	}

	/**
	 * @see 
	 *      com.facebook.halo.framework.client.FacebookClient#fetchConnection(java
	 *      .lang.String, java.lang.Class, java.util.ArrayList<Parameter>)
	 */
	@Override
	public <T> Connection<T> fetchConnection(String connection,
			Class<T> connectionType, ArrayList<Parameter> parameterList) {
		DefaultFacebookUtils.verifyParameterPresence("connection", connection);
		DefaultFacebookUtils.verifyParameterPresence("connectionType",
				connectionType);

		Parameter[] parameters = new Parameter[parameterList.size()];

		for (int i = 0; i < parameterList.size(); i++) {
			parameters[i] = parameterList.get(i);
		}

		return new Connection<T>(this, makeRequest(connection, parameters),
				connectionType);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#fetchConnectionPage(java.lang.String,
	 *      java.lang.Class)
	 */
	@Override
	public <T> Connection<T> fetchConnectionPage(
			final String connectionPageUrl, Class<T> connectionType) {
		String connectionJson = makeRequestAndProcessResponse(new Requestor() {

			/**
			 * @see com.facebook.halo.framework.core.DefaultFacebookClient.Requestor#makeRequest()
			 */
			public Response makeRequest() throws IOException {
				return webRequestor.executeGet(connectionPageUrl);
			}
		});

		return new Connection<T>(this, connectionJson, connectionType);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#fetchObject(java.lang.String,
	 *      java.lang.Class, com.facebook.halo.framework.common.Parameter[])
	 */
	@Override
	public <T> T fetchObject(String object, Class<T> objectType,
			Parameter... parameters) {
		DefaultFacebookUtils.verifyParameterPresence("object", object);
		DefaultFacebookUtils.verifyParameterPresence("objectType", objectType);
		return jsonMapper.toJavaObject(makeRequest(object, parameters),
				objectType);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#fetchObjects(java.util.List,
	 *      java.lang.Class, com.facebook.halo.framework.common.Parameter[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T fetchObjects(List<String> ids, Class<T> objectType,
			Parameter... parameters) {
		DefaultFacebookUtils.verifyParameterPresence("ids", ids);
		DefaultFacebookUtils.verifyParameterPresence("connectionType",
				objectType);

		if (ids.size() == 0)
			throw new IllegalArgumentException(
					"The list of IDs cannot be empty.");

		for (Parameter parameter : parameters)
			if (DefaultFacebookString.IDS_PARAM_NAME.equals(parameter.name))
				throw new IllegalArgumentException("You cannot specify the '"
						+ DefaultFacebookString.IDS_PARAM_NAME
						+ "' URL parameter yourself - "
						+ "RestFB will populate this for you with "
						+ "the list of IDs you passed to this method.");

		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i).trim().toLowerCase();
			if ("".equals(id))
				throw new IllegalArgumentException(
						"The list of IDs cannot contain blank strings.");
			ids.set(i, id);
		}

		try {
			JsonObject jsonObject = new JsonObject(makeRequest(
					"",
					parametersWithAdditionalParameter(Parameter.with(
							DefaultFacebookString.IDS_PARAM_NAME, join(ids)),
							parameters)));

			return objectType.equals(JsonObject.class) ? (T) jsonObject
					: jsonMapper
							.toJavaObject(jsonObject.toString(), objectType);
		} catch (JsonException e) {
			throw new FacebookJsonMappingException(
					"Unable to map connection JSON to Java objects", e);
		}
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#publish(java.lang.String,
	 *      java.lang.Class,
	 *      com.facebook.halo.framework.common.BinaryAttachment,
	 *      com.facebook.halo.framework.common.Parameter[])
	 */
	@Override
	public <T> T publish(String connection, Class<T> objectType,
			BinaryAttachment binaryAttachment, Parameter... parameters) {
		DefaultFacebookUtils.verifyParameterPresence("connection", connection);

		List<BinaryAttachment> binaryAttachments = new ArrayList<BinaryAttachment>();
		if (binaryAttachment != null)
			binaryAttachments.add(binaryAttachment);

		return jsonMapper.toJavaObject(
				makeRequest(connection, true, false, binaryAttachments,
						parameters), objectType);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#publish(java.lang.String,
	 *      java.lang.Class, com.facebook.halo.framework.common.Parameter[])
	 */
	@Override
	public <T> T publish(String connection, Class<T> objectType,
			Parameter... parameters) {
		return publish(connection, objectType, null, parameters);
	}

	/**
	 * @see 
	 *      com.facebook.halo.framework.core.FacebookClient#publish(java.lang.String
	 *      , java.lang.Class,
	 *      com.facebook.halo.framework.common.BinaryAttachment,
	 *      java.util.ArrayList<com.facebook.halo.framework.common.Parameter[]>)
	 */
	@Override
	public <T> T publish(String connection, Class<T> objectType,
			BinaryAttachment binaryAttachment,
			ArrayList<Parameter> parameterList) {
		DefaultFacebookUtils.verifyParameterPresence("connection", connection);

		List<BinaryAttachment> binaryAttachments = new ArrayList<BinaryAttachment>();
		if (binaryAttachment != null)
			binaryAttachments.add(binaryAttachment);

		Parameter[] parameters = new Parameter[parameterList.size()];

		for (int i = 0; i < parameterList.size(); i++) {
			parameters[i] = parameterList.get(i);
		}

		return jsonMapper.toJavaObject(
				makeRequest(connection, true, false, binaryAttachments,
						parameters), objectType);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#publishLikes(java.lang.String)
	 */
	@Override
	public boolean publishLikes(String connection) {
		DefaultFacebookUtils.verifyParameterPresence("connection", connection);
		return "true".equals(makeRequest(connection, true, false, null));
	}
	

	/**
	 * @see 
	 *      com.facebook.halo.framework.core.FacebookClient#publish(java.lang.String
	 *      , java.lang.Class,
	 *      java.util.ArrayList<com.facebook.halo.framework.common.Parameter[]>)
	 */
	@Override
	public <T> T publish(String connection, Class<T> objectType,
			ArrayList<Parameter> parameterList) {
		return publish(connection, objectType, null, parameterList);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#executeMultiquery(java.util.Map,
	 *      java.lang.Class, com.facebook.halo.framework.common.Parameter[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T executeMultiquery(Map<String, String> queries,
			Class<T> objectType, Parameter... parameters) {
		DefaultFacebookUtils.verifyParameterPresence("objectType", objectType);

		for (Parameter parameter : parameters)
			if (DefaultFacebookString.QUERIES_PARAM_NAME.equals(parameter.name))
				throw new IllegalArgumentException("You cannot specify the '"
						+ DefaultFacebookString.QUERIES_PARAM_NAME
						+ "' URL parameter yourself - "
						+ "RestFB will populate this for you with "
						+ "the queries you passed to this method.");

		try {
			JsonArray jsonArray = new JsonArray(makeRequest(
					"fql.multiquery",
					false,
					false,
					null,
					parametersWithAdditionalParameter(Parameter.with(
							DefaultFacebookString.QUERIES_PARAM_NAME,
							queriesToJson(queries)), parameters)));

			JsonObject normalizedJson = new JsonObject();

			for (int i = 0; i < jsonArray.length(); i++) {
				JsonObject jsonObject = jsonArray.getJsonObject(i);

				JsonArray resultsArray = jsonObject.get("fql_result_set") instanceof JsonArray ? jsonObject
						.getJsonArray("fql_result_set") : new JsonArray();

				normalizedJson.put(jsonObject.getString("name"), resultsArray);
			}

			return objectType.equals(JsonObject.class) ? (T) normalizedJson
					: jsonMapper.toJavaObject(normalizedJson.toString(),
							objectType);
		} catch (JsonException e) {
			throw new FacebookJsonMappingException(
					"Unable to process fql.multiquery JSON response", e);
		}
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#executeQuery(java.lang.String,
	 *      java.lang.Class, com.facebook.halo.framework.common.Parameter[])
	 */
	@Override
	public <T> List<T> executeQuery(String query, Class<T> objectType,
			Parameter... parameters) {
		DefaultFacebookUtils.verifyParameterPresence("query", query);
		DefaultFacebookUtils.verifyParameterPresence("objectType", objectType);

		for (Parameter parameter : parameters)
			if (DefaultFacebookString.QUERY_PARAM_NAME.equals(parameter.name))
				throw new IllegalArgumentException("You cannot specify the '"
						+ DefaultFacebookString.QUERY_PARAM_NAME
						+ "' URL parameter yourself - "
						+ "RestFB will populate this for you with "
						+ "the query you passed to this method.");

		return jsonMapper.toJavaList(
				makeRequest(
						"fql.query",
						false,
						false,
						null,
						parametersWithAdditionalParameter(Parameter.with(
								DefaultFacebookString.QUERY_PARAM_NAME, query),
								parameters)), objectType);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#executeBatch(com.facebook.halo.framework.batch.BatchRequest[])
	 */
	@Override
	public List<BatchResponse> executeBatch(BatchRequest... batchRequests) {
		return executeBatch(asList(batchRequests),
				Collections.<BinaryAttachment> emptyList());
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#executeBatch(java.util.List,
	 *      java.util.List)
	 */
	@Override
	public List<BatchResponse> executeBatch(List<BatchRequest> batchRequests,
			List<BinaryAttachment> binaryAttachments) {
		DefaultFacebookUtils.verifyParameterPresence("binaryAttachments",
				binaryAttachments);

		if (batchRequests == null || batchRequests.size() == 0)
			throw new IllegalArgumentException(
					"You must specify at least one batch request.");

		return jsonMapper.toJavaList(
				makeRequest(
						"",
						true,
						false,
						binaryAttachments,
						Parameter.with("batch",
								jsonMapper.toJson(batchRequests, true))),
				BatchResponse.class);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#convertSessionKeysToAccessTokens(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	@Override
	public List<AccessToken> convertSessionKeysToAccessTokens(String appId,
			String secretKey, String... sessionKeys) {
		DefaultFacebookUtils.verifyParameterPresence("appId", appId);
		DefaultFacebookUtils.verifyParameterPresence("secretKey", secretKey);

		if (sessionKeys == null || sessionKeys.length == 0)
			return emptyList();

		String json = makeRequest("/oauth/exchange_sessions", true, false,
				null, Parameter.with("client_id", appId),
				Parameter.with("client_secret", secretKey),
				Parameter.with("sessions", join(sessionKeys)));

		return jsonMapper.toJavaList(json, AccessToken.class);
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#getJsonMapper()
	 */
	@Override
	public JsonMapper getJsonMapper() {
		return jsonMapper;
	}

	/**
	 * @see com.facebook.halo.framework.core.FacebookClient#getWebRequestor()
	 */
	@Override
	public WebRequestor getWebRequestor() {
		return webRequestor;
	}

	/**
	 * Coordinates the process of executing the API request GET/POST and
	 * processing the response we receive from the endpoint.
	 * 
	 * @param endpoint
	 *            Facebook Graph API endpoint.
	 * @param parameters
	 *            Arbitrary number of parameters to send along to Facebook as
	 *            part of the API call.
	 * @return The JSON returned by Facebook for the API call.
	 * @throws FacebookException
	 *             If an error occurs while making the Facebook API POST or
	 *             processing the response.
	 */
	protected String makeRequest(String endpoint, Parameter... parameters) {
		return makeRequest(endpoint, false, false, null, parameters);
	}

	/**
	 * Coordinates the process of executing the API request GET/POST and
	 * processing the response we receive from the endpoint.
	 * 
	 * @param endpoint
	 *            Facebook Graph API endpoint.
	 * @param executeAsPost
	 *            {@code true} to execute the web request as a {@code POST},
	 *            {@code false} to execute as a {@code GET}.
	 * @param executeAsDelete
	 *            {@code true} to add a special 'treat this request as a
	 *            {@code DELETE}' parameter.
	 * @param binaryAttachment
	 *            A binary file to include in a {@code POST} request. Pass
	 *            {@code null} if no attachment should be sent.
	 * @param parameters
	 *            Arbitrary number of parameters to send along to Facebook as
	 *            part of the API call.
	 * @return The JSON returned by Facebook for the API call.
	 * @throws FacebookException
	 *             If an error occurs while making the Facebook API POST or
	 *             processing the response.
	 */
	public String makeRequest(String endpoint, final boolean executeAsPost,
			boolean executeAsDelete,
			final List<BinaryAttachment> binaryAttachments,
			Parameter... parameters) {
		verifyParameterLegality(parameters);

		if (executeAsDelete)
			parameters = parametersWithAdditionalParameter(Parameter.with(
					DefaultFacebookString.METHOD_PARAM_NAME, "delete"),
					parameters);

		trimToEmpty(endpoint).toLowerCase();
		if (!endpoint.startsWith("/"))
			endpoint = "/" + endpoint;

		final String fullEndpoint = createEndpointForApiCall(endpoint,
				binaryAttachments != null && binaryAttachments.size() > 0);
		final String parameterString = toParameterString(parameters);

		return makeRequestAndProcessResponse(new Requestor() {
			public Response makeRequest() throws IOException {
				return executeAsPost ? webRequestor.executePost(
						fullEndpoint,
						parameterString,
						binaryAttachments == null ? null : binaryAttachments
								.toArray(new BinaryAttachment[] {}))
						: webRequestor.executeGet(fullEndpoint + "?"
								+ parameterString);
			}
		});
	}

	protected static interface Requestor {
		Response makeRequest() throws IOException;
	}

	protected String makeRequestAndProcessResponse(Requestor requestor) {
		Response response = null;

		// Perform a GET or POST to the API endpoint
		try {
			response = requestor.makeRequest();
		} catch (Throwable t) {
			throw new FacebookNetworkException("Facebook request failed", t);
		}

		if (logger.isLoggable(INFO))
			logger.info("Facebook responded with " + response);

		// If we get any HTTP response code other than a 200 OK or 400 Bad
		// Request
		// or 401 Not Authorized or 403 Forbidden or 500 Internal Server Error,
		// throw an exception.
		if (HTTP_OK != response.getStatusCode()
				&& HTTP_BAD_REQUEST != response.getStatusCode()
				&& HTTP_UNAUTHORIZED != response.getStatusCode()
				&& HTTP_INTERNAL_ERROR != response.getStatusCode()
				&& HTTP_FORBIDDEN != response.getStatusCode())
			throw new FacebookNetworkException("Facebook request failed",
					response.getStatusCode());

		String json = response.getBody();

		throwFacebookResponseStatusExceptionIfNecessary(json);

		if (HTTP_INTERNAL_ERROR == response.getStatusCode()
				|| HTTP_UNAUTHORIZED == response.getStatusCode())
			throw new FacebookNetworkException("Facebook request failed",
					response.getStatusCode());

		return json;
	}

	/**
	 * Throws an exception if Facebook returned an error response. Using the
	 * Graph API, it's possible to see both the new Graph API-style errors as
	 * well as Legacy API-style errors, so we have to handle both here. This
	 * method extracts relevant information from the error JSON and throws an
	 * exception which encapsulates it for end-user consumption.
	 * <p>
	 * For Graph API errors:
	 * <p>
	 * If the {@code error} JSON field is present, we've got a response status
	 * error for this API call.
	 * <p>
	 * For Legacy errors (e.g. FQL):
	 * <p>
	 * If the {@code error_code} JSON field is present, we've got a response
	 * status error for this API call.
	 * 
	 * @param json
	 *            The JSON returned by Facebook in response to an API call.
	 * @throws FacebookGraphException
	 *             If the JSON contains a Graph API error response.
	 * @throws FacebookResponseStatusException
	 *             If the JSON contains an Legacy API error response.
	 * @throws FacebookJsonMappingException
	 *             If an error occurs while processing the JSON.
	 */
	protected void throwFacebookResponseStatusExceptionIfNecessary(String json) {
		// If we have a legacy exception, throw it.
		throwLegacyFacebookResponseStatusExceptionIfNecessary(json);
		// If we have a batch API exception, throw it.
		throwBatchFacebookResponseStatusExceptionIfNecessary(
				legacyFacebookExceptionMapper, json);
		// If we have a Json exception, throw it.
		throwJsonFacebookResponseStatusExceptionIfNecessary(
				graphFacebookExceptionMapper, json);
	}

	/**
	 * @see com.facebook.halo.framework.client.
	 *      throwBatchFacebookResponseStatusExceptionIfNecessary
	 *      (FacebookExceptionMapper legacyFacebookExceptionMapper, String json)
	 * @since 1.6.5
	 */
	protected void throwBatchFacebookResponseStatusExceptionIfNecessary(
			String json) {
		throwBatchFacebookResponseStatusExceptionIfNecessary(
				legacyFacebookExceptionMapper, json);
	}

	/**
	 * @see com.facebook.halo.framework.exception.DefaultGraphFacebookExceptionMapper
	 * @since 1.6.5
	 */
	protected FacebookExceptionMapper createGraphFacebookExceptionMapper() {
		return new DefaultGraphFacebookExceptionMapper();
	}

	/**
	 * Generate the parameter string to be included in the Facebook API request.
	 * 
	 * @param parameters
	 *            Arbitrary number of extra parameters to include in the
	 *            request.
	 * @return The parameter string to include in the Facebook API request.
	 * @throws FacebookJsonMappingException
	 *             If an error occurs when building the parameter string.
	 */
	protected String toParameterString(Parameter... parameters) {

		if (!isBlank(accessToken))
			parameters = parametersWithAdditionalParameter(
					Parameter.with(
							DefaultFacebookString.ACCESS_TOKEN_PARAM_NAME,
							accessToken), parameters);
		parameters = parametersWithAdditionalParameter(
				Parameter.with(DefaultFacebookString.FORMAT_PARAM_NAME, "json"),
				parameters);
		//
		// StringBuilder parameterStringBuilder = new StringBuilder();
		// boolean first = true;
		//
		// for (Parameter parameter : parameters) {
		// if (first)
		// first = false;
		// else
		// parameterStringBuilder.append("&");
		//
		// parameterStringBuilder.append(urlEncode(parameter.name));
		// parameterStringBuilder.append("=");
		// parameterStringBuilder.append(urlEncodedValueForParameterName(parameter.name,
		// parameter.value));
		// }
		//
		// return parameterStringBuilder.toString();
		/**
		 * @see com.facebook.halo.framework.client.DefaultFacebookUtils.
		 *      toParameterString(Parameter... parameters)
		 * @return String
		 */
		return DefaultFacebookUtils.toParameterString(parameters);
	}
}