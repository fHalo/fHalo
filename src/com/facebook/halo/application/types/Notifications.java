package com.facebook.halo.application.types;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.Date;

import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.annotation.Facebook;

public class Notifications extends FacebookType {
	
	@Facebook
	private NamedFacebookType from;
	@Facebook
	private NamedFacebookType to;
	@Facebook("created_time")
	private String createdTime;
	@Facebook("updated_time")
	private String updatedTime;
	@Facebook
	private String title;
	@Facebook
	private String message;
	@Facebook
	private String link;
	@Facebook
	private NamedFacebookType application;
	@Facebook
	private int unread;

	public NamedFacebookType getTo() {
		return to;
	}

	public NamedFacebookType getFrom() {
		return from;
	}

	public Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}

	public Date getUpdatedTime() {
		return toDateFromLongFormat(updatedTime);
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public NamedFacebookType getApplication() {
		return application;
	}

	public int isUnread() {
		return unread;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}