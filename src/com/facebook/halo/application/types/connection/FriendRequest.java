package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User object connection type
 * @author JM
 *
 */
public class FriendRequest {
	private List<FriendRequestInfo> data = new ArrayList<FriendRequestInfo>();
	
	
	public final List<FriendRequestInfo> getData() {
		return data;
	}

	/**
	 *  User Object connection FriendRequestInfo type
	 * @author JM
	 *
	 */
	public static class FriendRequestInfo {
		private NamedFacebookType to;
		private NamedFacebookType from;
		private String createdTime;
		private String message;
		private boolean unread;
		
		public NamedFacebookType getTo() {
			return to;
		}
		public NamedFacebookType getFrom() {
			return from;
		}
		public Date getCreatedTime() {
			return toDateFromLongFormat(createdTime);
		}
		public String getMessage() {
			return message;
		}
		public boolean isUnread() {
			return unread;
		}	
	}
}
