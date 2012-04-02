package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User Object Connection type
 * @author JM
 *
 */
public class MutualFriends {
	List<NamedFacebookType> data = new ArrayList<NamedFacebookType>();

	public final List<NamedFacebookType> getData() {
		return data;
	}
}
