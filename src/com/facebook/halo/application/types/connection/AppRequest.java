package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.Date;

import com.facebook.halo.application.types.Application;
import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User Object Connection type
 * @author immk
 *
 */
public class AppRequest extends FacebookType{
	//id, type <-- FacebookType
	private Application	application;
	private NamedFacebookType to;
	private NamedFacebookType from;
	private String message;
	private String createdTime; //UNIX timesatmp
	
	public Application getApplication() {
		return application;
	}
	public NamedFacebookType getTo() {
		return to;
	}
	public NamedFacebookType getFrom() {
		return from;
	}
	public String getMessage() {
		return message;
	}
	public Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}
	
}
