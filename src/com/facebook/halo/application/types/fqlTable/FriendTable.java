package com.facebook.halo.application.types.fqlTable;

import com.facebook.halo.framework.annotation.Facebook;

/**
 * fql FriendTable type
 * @author JM
 *
 */
public class FriendTable {
	
	@Facebook
	private String uid1;
	@Facebook
	private String uid2;
	
	public String getUid1() {
		return uid1;
	}
	
	public String getUid2() {
		return uid2;
	}
}
