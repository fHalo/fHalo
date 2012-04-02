package com.facebook.halo.application.types.fqlTable;

import java.util.List;

import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.framework.annotation.Facebook;
/**
 * @see http://developers.facebook.com/docs/reference/fql/checkin/
 * @author immk
 */
public class CheckinTable {
	@Facebook("checkin_id")
	private int checkinId;
	@Facebook("page_id")
	private int author_uid;
	@Facebook("page_id")
	private int page_id;
	@Facebook("page_id")
	private int app_id;
	@Facebook("post_id")
	private int post_id;
	@Facebook
	private List<Coordinate> coords;
	@Facebook
	private String timestamp;
	@Facebook("tagged_uids")
	private List<FacebookType> tagged_uids;
	@Facebook
	private String message;
	
	class Coordinate {
		@Facebook
		private String latitude;
		@Facebook
		private String logitude;
		
		public String getLatitude() {
			return latitude;
		}
		public String getLogitude() {
			return logitude;
		}
	}

	public int getCheckinId() {
		return checkinId;
	}

	public int getAuthor_uid() {
		return author_uid;
	}

	public int getPage_id() {
		return page_id;
	}

	public int getApp_id() {
		return app_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public List<Coordinate> getCoords() {
		return coords;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public List<FacebookType> getTagged_uids() {
		return tagged_uids;
	}

	public String getMessage() {
		return message;
	}	
}
