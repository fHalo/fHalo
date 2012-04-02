package com.facebook.halo.framework.common;

import java.util.Date;

import com.facebook.halo.framework.annotation.Facebook;
import com.facebook.halo.framework.formatter.ReflectionUtils;

/**
 * Represents an access token/expiration date pair.
 * <p>
 * Facebook returns these types when converting from legacy session keys to
 * OAuth access tokens - see
 * {@link com.facebook.halo.framework.core.FacebookClient#convertSessionKeysToAccessTokens(String, String, String...)}
 * for details.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class AccessToken {
	
	@Facebook("access_token")
	private static String accessToken;

	@Facebook
	private Long expires;

    /**
     * @see java.lang.Object#hashCode()
     */
	@Override
	public int hashCode() {
		return ReflectionUtils.hashCode(this);
	}
	
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
	@Override
	public boolean equals(Object that) {
		return ReflectionUtils.equals(this, that);
	}

    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return ReflectionUtils.toString(this);
	}
	
    /**
     * The access token's value.
     * @return The access token's value.
     */
	public static String getAccessToken() {
		return accessToken;
	}
	
	/**
     * set access token's value.
     */
	public static void setAccessToken(String token) {
		AccessToken.accessToken = token;
	}
	
    /**
     * The date on which the access token expires.
     * @return The date on which the access token expires.
     */
	public Date getExpires() {
		return expires == null ? null : new Date(1000L * expires);
	}
}
