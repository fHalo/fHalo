package com.facebook.halo.application.types.fqlTable;

import java.util.List;

import com.facebook.halo.framework.annotation.Facebook;

/**
 * @see http://developers.facebook.com/docs/reference/fql/photo/
 * @author immk
 *
 */
public class PhotoTable {
	@Facebook
	private String pid;
	@Facebook
	private String  aid;
	@Facebook
	private String  owner;
	@Facebook("src_small")
	private String  srcSmall;
	@Facebook("src_small_width")
	private int srcSmallWidth;
	@Facebook("src_small_height")
	private int srcSmallHeight;
	@Facebook("src_big")
	private String srcBig;
	@Facebook("src_big_width")
	private int srcBigWidth;
	@Facebook("src_big_height")
	private int srcBigHeight;
	@Facebook
	private String src;
	@Facebook("src_width")
	private int srcWidth;
	@Facebook("src_height")
	private int srcHeight;
	@Facebook
	private String link;
	@Facebook
	private String caption;
	@Facebook
	private String created;
	@Facebook
	private String modified;
	@Facebook
	private int position;
	@Facebook("object_id")
	private int objectId;
	@Facebook("album_object_id")
	private int albumObjectId;
	@Facebook
	private List<Image> images;
	@Facebook("like_info")
	private LikeInfo likeInfo;
	@Facebook("comment_info")
	private CommentInfo commentInfo;
	
	class Image {
		@Facebook
		private int width;
		@Facebook
		private int height;
		@Facebook
		private String source;
		
		public int getWidth() {
			return width;
		}
		public int getHeight() {
			return height;
		}
		public String getSource() {
			return source;
		}
	}
	
	class LikeInfo {
		@Facebook("can_like")
		private String canLike;
		@Facebook("like_count")
		private String likeCount;
		@Facebook("user_likes")
		private String userLikes;
		
		public String getCanLike() {
			return canLike;
		}
		public String getLikeCount() {
			return likeCount;
		}
		public String getUserLikes() {
			return userLikes;
		}
	}
	
	class CommentInfo {
		@Facebook("can_comment")
		private String canComment;
		@Facebook("comment_count")
		private String commentCount;
		
		public String getCanComment() {
			return canComment;
		}
		public String getCommentCount() {
			return commentCount;
		}
	}

	public String getPid() {
		return pid;
	}

	public String getAid() {
		return aid;
	}

	public String getOwner() {
		return owner;
	}

	public String getSrcSmall() {
		return srcSmall;
	}

	public int getSrcSmallWidth() {
		return srcSmallWidth;
	}

	public int getSrcSmallHeight() {
		return srcSmallHeight;
	}

	public String getSrcBig() {
		return srcBig;
	}

	public int getSrcBigWidth() {
		return srcBigWidth;
	}

	public int getSrcBigHeight() {
		return srcBigHeight;
	}

	public String getSrc() {
		return src;
	}

	public int getSrcWidth() {
		return srcWidth;
	}

	public int getSrcHeight() {
		return srcHeight;
	}

	public String getLink() {
		return link;
	}

	public String getCaption() {
		return caption;
	}

	public String getCreated() {
		return created;
	}

	public String getModified() {
		return modified;
	}

	public int getPosition() {
		return position;
	}

	public int getObjectId() {
		return objectId;
	}

	public int getAlbumObjectId() {
		return albumObjectId;
	}

	public List<Image> getImages() {
		return images;
	}

	public LikeInfo getLikeInfo() {
		return likeInfo;
	}

	public CommentInfo getCommentInfo() {
		return commentInfo;
	}
}
