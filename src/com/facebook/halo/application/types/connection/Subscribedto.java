package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.SubscribedTo;

/**
 * User Object Connection type
 * @author immk
 *
 */
public class Subscribedto {

	List<SubscribedTo> data = new ArrayList<SubscribedTo>();

	public final List<SubscribedTo> getData() {
		return data;
	}

}
