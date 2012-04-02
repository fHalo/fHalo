package com.facebook.halo.application.types;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.annotation.Facebook;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.core.DefaultFacebookClient;
import com.facebook.halo.framework.core.FacebookClient;

/**
 * Message type Object
 * @author JM
 *
 */
public class Message {
	@Facebook
	private String id;
	
	@Facebook("created_time")
	private String createdTime;
	
	@Facebook
	private NamedFacebookType from;
	
	@Facebook
	private List<NamedFacebookType> to = new ArrayList<NamedFacebookType>();
	
	@Facebook
	private String message;
	
	private FacebookClient facebookClient;
	
	public Message() {
		facebookClient = new DefaultFacebookClient(AccessToken.getAccessToken());
	}
	
	public final String getId() {
		return id;
	}
	
	public final Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}
	
	public final NamedFacebookType getFrom() {
		return from;
	}
	
	public final List<NamedFacebookType> getTo() {
		return to;
	}
	
	public final String getMessage() {
		return message;
	}
	
	public Message createInstance(String id) {
		this.id = id;
		return facebookClient.fetchObject(id, Message.class);
	}

}
