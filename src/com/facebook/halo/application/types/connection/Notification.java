package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User Object Connection type
 * @author JM
 *
 */
public class Notification {
	List<NotificationInfo> data = new ArrayList<NotificationInfo>();
	
	
	public final List<NotificationInfo> getData() {
		return data;
	}


	/**
	 * User Object connection NotificationInfo type
	 * @author JM
	 *
	 */
	public static class NotificationInfo extends FacebookType{
		private NamedFacebookType to;
		private NamedFacebookType from;
		private String createdTime;
		private String updatedTime;
		private String title;
		private String link;
		private NamedFacebookType application;
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
		public boolean isUnread() {
			return unread;
		}
	}

}