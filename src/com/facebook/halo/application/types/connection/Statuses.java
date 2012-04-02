package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.StatusMessage;

/**
 * User Object Connection type
 * Page Object Connection type
 * @author immk
 *
 */
public class Statuses {

	List<StatusMessage> data = new ArrayList<StatusMessage>();

	public final List<StatusMessage> getData() {
		return data;
	}
}
