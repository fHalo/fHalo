package com.facebook.halo.application.handler;

import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.core.DefaultFacebookClient;
import com.facebook.halo.framework.core.FacebookClient;

/**
 * Facebook Graph Api Delete
 * @author immk
 */
public class Delete {
	
	FacebookClient facebookClient;
	
	public Delete(){
		facebookClient = new DefaultFacebookClient(AccessToken.getAccessToken());
	}
	
	public boolean delete(String objectId) {
		return facebookClient.deleteObject(objectId);
	}
}
