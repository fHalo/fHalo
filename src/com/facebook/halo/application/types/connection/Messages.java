package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.Message;

/**
 * User Object Connection type
 * @author immk
 *
 */
public class Messages {
	
	List<Message> data = new ArrayList<Message>();

	public final List<Message> getData() {
		return data;
	}

}
