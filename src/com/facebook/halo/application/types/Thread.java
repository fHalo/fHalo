package com.facebook.halo.application.types;

import java.util.ArrayList;
import java.util.List;


import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * Thread Object Type
 * @author JM
 *
 */
public class Thread{

	private String id;
	private String snippet;
	private String updated_time;
	private int message_count;
	private int unread_count;
	
	public List<NamedFacebookType> tags = new ArrayList<NamedFacebookType>();
	public List<ThreadInfo> participants = new ArrayList<ThreadInfo>();
	public List<ThreadInfo> former_participants = new ArrayList<ThreadInfo>();
	public List<ThreadInfo> senders = new ArrayList<ThreadInfo>();
	public List<Message> messages = new ArrayList<Message>();

	/**
	 * inner class
	 * @author JM
	 *
	 */
	public static class ThreadInfo extends NamedFacebookType{
		private String email;

		public final String getEmail() {
			return email;
		}
	}

}