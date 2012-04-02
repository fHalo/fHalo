package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User connection type
 * @author JM
 *
 */
public class Groups {
	List<GroupsInfo> data = new ArrayList<GroupsInfo>();
	
	public final List<GroupsInfo> getData() {
		return data;
	}

	/**
	 * User Object connection GroupsInfo type
	 * @author JM
	 *
	 */
	public static class GroupsInfo extends NamedFacebookType{
		private int version;
		private boolean administrator;
		private int unread;
		private int bookmarkOrder;
		
		public final int getVersion() {
			return version;
		}
		public final boolean isAdministrator() {
			return administrator;
		}
		public final int getUnread() {
			return unread;
		}
		public final int getBookmarkOrder() {
			return bookmarkOrder;
		}
		
	}

}
