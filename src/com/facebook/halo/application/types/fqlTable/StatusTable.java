package com.facebook.halo.application.types.fqlTable;

import com.facebook.halo.framework.annotation.Facebook;

/**
 * @see http://developers.facebook.com/docs/reference/fql/status/
 * @author immk
 */
public class StatusTable {
	
	@Facebook
	private int uid;
	@Facebook("status_id")
	private String statusId;
	@Facebook
	private int time;
	@Facebook
	private int source;
	@Facebook
	private String message;
	
	public int getUid() {
		return uid;
	}
	public String getStatusId() {
		return statusId;
	}
	public int getTime() {
		return time;
	}
	public int getSource() {
		return source;
	}
	public String getMessage() {
		return message;
	}	
}
