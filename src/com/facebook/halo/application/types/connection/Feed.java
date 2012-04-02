package com.facebook.halo.application.types.connection;

import com.facebook.halo.application.types.Post;


public class Feed extends Post{
	
	public Feed(){
		
	}
	
	public Feed(String message, String picture, String link, String name, String caption, String description){
		if(message != null)
			setMessage(message);
		if(picture !=null)
			setPicture(picture);
		if(link!=null)
			setLink(link);
		if(name!=null)
			setName(name);
		if(caption!=null)
			setCaption(caption);
		if(description!=null)
			setDescription(description);
	}
	
	public void setMessage(String message) {
		this.message = message;

	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
