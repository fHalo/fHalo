package com.facebook.halo.application.types;

import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.core.DefaultFacebookClient;
import com.facebook.halo.framework.core.FacebookClient;

public class Action extends NamedFacebookType{
	private FacebookClient facebookClient;
	
	/**
	 * constructor
	 */
	public Action() {
		facebookClient = new DefaultFacebookClient(AccessToken.getAccessToken());
	}
	
	public FacebookType publishAction(String appNameSpace, String action, String object, String url) {
		System.out.println("" + getId());
		return facebookClient.publish("me/" + appNameSpace + ":" + action + "?" + object + "=" + url, FacebookType.class);
	}
}
