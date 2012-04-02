package com.facebook.halo.application.types.connection;

import com.facebook.halo.application.types.Link;

/**
 * User Object Connection type
 * Page Object Connection type
 * @author JM
 *
 */
public class Links extends Link{
	
	private String caption;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
