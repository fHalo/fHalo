package com.facebook.halo.application.types.fqlTable;

import com.facebook.halo.framework.annotation.Facebook;

/**
 * @see http://developers.facebook.com/docs/reference/fql/comment/
 * @author immk
 */
public class CommentTable {

	@Facebook
	private String xid;
	@Facebook("object_id")
	private String objectId;
	@Facebook("post_id")
	private String postId;
	@Facebook
	private int fromid;
	@Facebook
	private int time;
	@Facebook
	private String text;
	@Facebook
	private int id;
	@Facebook
	private String username;
	@Facebook("reply_xid")
	private String replyXid;
	@Facebook("post_fbid")
	private String postFbid;
	@Facebook("app_id")
	private int appId;
	@Facebook
	private int likes;
	@Facebook
	private CommentTable comments;
	@Facebook("can_like")
	private boolean canLike;
	@Facebook("user_likes")
	private boolean userLikes;
	@Facebook("is_private")
	private boolean isPrivate;
	
	public String getXid() {
		return xid;
	}
	public String getObjectId() {
		return objectId;
	}
	public String getPostId() {
		return postId;
	}
	public int getFromid() {
		return fromid;
	}
	public int getTime() {
		return time;
	}
	public String getText() {
		return text;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getReplyXid() {
		return replyXid;
	}
	public String getPostFbid() {
		return postFbid;
	}
	public int getAppId() {
		return appId;
	}
	public int getLikes() {
		return likes;
	}
	public CommentTable getComments() {
		return comments;
	}
	public boolean isCanLike() {
		return canLike;
	}
	public boolean isUserLikes() {
		return userLikes;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	
}
