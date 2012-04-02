package com.facebook.halo.application.types.connection;

import com.facebook.halo.application.types.Photo;
/**
 * User Object connection type
 * Page Object connection type
 * @author immk
 *
 */
public class Photos extends Photo{
	
	private String message;
	private String fileName;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
