package com.facebook.halo.framework.core;

public class DefaultFacebookString {

	  /**
	   * API endpoint URL.
	   */
	  protected static final String FACEBOOK_GRAPH_ENDPOINT_URL = "https://graph.facebook.com";
	  /**
	   * Read-only API endpoint URL.
	   */
	  protected static final String FACEBOOK_READ_ONLY_ENDPOINT_URL = "https://api-read.facebook.com/method";
	  /**
	   * Video Upload API endpoint URL.
	   */
	  protected static final String FACEBOOK_GRAPH_VIDEO_ENDPOINT_URL = "https://graph-video.facebook.com";
	  /**
	   * Reserved method override parameter name.
	   */
	  protected static final String METHOD_PARAM_NAME = "method";
	  /**
	   * Reserved "multiple IDs" parameter name.
	   */
	  protected static final String IDS_PARAM_NAME = "ids";
	  /**
	   * Reserved FQL query parameter name.
	   */
	  protected static final String QUERY_PARAM_NAME = "query";
	  /**
	   * Reserved FQL multiquery parameter name.
	   */
	  protected static final String QUERIES_PARAM_NAME = "queries";
	  /**
	   * Reserved "result format" parameter name.
	   */
	  protected static final String FORMAT_PARAM_NAME = "format";
	  /**
	   * API error response 'error' attribute name.
	   */
	  protected static final String ERROR_ATTRIBUTE_NAME = "error";
	  /**
	   * API error response 'type' attribute name.
	   */
	  protected static final String ERROR_TYPE_ATTRIBUTE_NAME = "type";
	  /**
	   * API error response 'message' attribute name.
	   */
	  protected static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "message";
	  /**
	   * Batch API error response 'error' attribute name.
	   */
	  protected static final String BATCH_ERROR_ATTRIBUTE_NAME = "error";
	  /**
	   * Batch API error response 'error_description' attribute name.
	   */
	  protected static final String BATCH_ERROR_DESCRIPTION_ATTRIBUTE_NAME = "error_description";
	  
	  
	  /**
	   * Legacy API error response 'error_code' attribute name.
	   */
	  protected static final String LEGACY_ERROR_CODE_ATTRIBUTE_NAME = "error_code";
	  /**
	   * Legacy API error response 'error_msg' attribute name.
	   */
	  protected static final String LEGACY_ERROR_MSG_ATTRIBUTE_NAME = "error_msg";
	  /**
	   * Reserved access token parameter name.
	   */
	  protected static final String ACCESS_TOKEN_PARAM_NAME = "access_token";

}
