package com.facebook.halo.framework.exception;


public class DefaultGraphFacebookExceptionMapper implements FacebookExceptionMapper {
	/**
	 * @see com.facebook.halo.framework.exception.FacebookExceptionMapper#exceptionForTypeAndMessage(java.lang.Integer,
	 *      java.lang.String, java.lang.String)
	 */
	public FacebookException exceptionForTypeAndMessage(Integer errorCode, String type, String message) {
		if ("OAuthException".equals(type) || "OAuthAccessTokenException".equals(type)) return new FacebookOAuthException(type, message);

		if ("QueryParseException".equals(type)) return new FacebookQueryParseException(type, message);

		// Don't recognize this exception type? Just go with the standard
		// FacebookGraphException.
		return new FacebookGraphException(type, message);
	}
}

