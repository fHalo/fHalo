package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.Date;
import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User Object Connection type
 * Page Object Connection type
 * @author JM
 *
 */
public class Events extends NamedFacebookType {
	//id, name <-- NamedFacebookType
	private String startTime;
	private String endTime;
	private String location;
	private String rsvpStatus;
	
	public Date getStartTime() {
		return toDateFromLongFormat(startTime);
	}
	public Date getEndTime() {
		return toDateFromLongFormat(endTime);
	}
	public String getLocation() {
		return location;
	}
	public String getRsvpStatus() {
		return rsvpStatus;
	}
}