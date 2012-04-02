package com.facebook.halo.application.types.connection;

import com.facebook.halo.application.types.Album;

/**
 * User Object Connection type
 * Page Object Connection type
 * @author smjxx2000
 *
 */
public class Albums extends Album{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
