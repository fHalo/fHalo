package com.facebook.halo.application.types.connection;

import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.annotation.Facebook;

/**
 * User Object Connection type
 * @author immk
 *
 */
public class Family extends NamedFacebookType{
	@Facebook
	private String relationship;

	public String getRelationship() {
		return relationship;
	}
}
