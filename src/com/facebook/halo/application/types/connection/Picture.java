package com.facebook.halo.application.types.connection;

import com.facebook.halo.framework.annotation.Facebook;

/**
 * User Object connection
 * Page Object connection
 * Group Object connection
 * Event Object connection
 * @author smjxx2000
 *
 */
public class Picture {
	@Facebook()
	String profilePictureUrl;

	public String getProfilePicture() {
		return profilePictureUrl;
	}

}
