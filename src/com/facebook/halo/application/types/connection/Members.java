package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * Group object connection type
 * @author JM
 *
 */
public class Members {
	private List<MembersInfo> data = new ArrayList<MembersInfo>();
	
	/**
	 * Group object connection MembersInfo type
	 * @author JM
	 *
	 */
	public static class MembersInfo {
		private NamedFacebookType member;
		private boolean administrator;
		
		public final NamedFacebookType getMember() {
			return member;
		}
		public final boolean isAdministrator() {
			return administrator;
		}
	}

	public final List<MembersInfo> getData() {
		return data;
	}
}
