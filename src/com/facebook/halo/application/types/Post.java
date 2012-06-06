/*
 * Copyright (c) 2010-2011 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.facebook.halo.application.types;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;
import static java.util.Collections.unmodifiableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.infra.CategorizedFacebookType;
import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.annotation.Facebook;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.common.Arguments;
import com.facebook.halo.framework.common.Parameter;
import com.facebook.halo.framework.core.Connection;
import com.facebook.halo.framework.core.DefaultFacebookClient;
import com.facebook.halo.framework.core.FacebookClient;
import com.facebook.halo.framework.formatter.ReflectionUtils;

/**
 * Represents the <a
 * href="http://developers.facebook.com/docs/reference/api/post">Post Graph API
 * type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Post extends NamedFacebookType {
	@Facebook
	private CategorizedFacebookType from;

	@Facebook
	protected String message;

	@Facebook
	protected String picture;

	@Facebook
	protected String link;

	@Facebook
	protected String caption;

	@Facebook
	protected String description;

	@Facebook
	protected String source;

	@Facebook
	private String type;

	@Facebook
	private NamedFacebookType application;

	@Facebook
	private String icon;

	@Facebook
	private String attribution;

	@Facebook
	private Privacy privacy;

	/**
	 * Duplicate mapping for "likes" since FB can return it differently in
	 * different situations.
	 */
	@Facebook("likes")
	private Long likesCount;

	/**
	 * Duplicate mapping for "likes" since FB can return it differently in
	 * different situations.
	 */
	@Facebook
	private Likes likes;

	@Facebook("created_time")
	private String createdTime;

	@Facebook("updated_time")
	private String updatedTime;

	@Facebook("object_id")
	private String objectId;

	@Facebook
	private Comments comments;

	@Facebook
	private PostPlace place;

	@Facebook
	private List<NamedFacebookType> to = new ArrayList<NamedFacebookType>();

	@Facebook
	private List<Action> actions = new ArrayList<Action>();

	@Facebook
	private List<Property> properties = new ArrayList<Property>();

	private static final long serialVersionUID = 2L;

	private FacebookClient facebookClient;

	/**
	 * Represents the <a
	 * href="http://developers.facebook.com/docs/reference/api/post">Place Graph
	 * API type</a>
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.6.8
	 */
	public static class PostPlace extends NamedFacebookType {
		@Facebook
		private Location location;

		private static final long serialVersionUID = 1L;

		/**
		 * The location of this place.
		 * 
		 * @return The location of this place.
		 */
		public Location getLocation() {
			return location;
		}
	}

	/**
	 * Represents the undocumented {@code Property} type.
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.6.4
	 */
	public static class Property implements Serializable {
		@Facebook
		private String name;

		@Facebook
		private String text;

		@Facebook
		private String href;

		private static final long serialVersionUID = 1L;

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return ReflectionUtils.hashCode(this);
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object that) {
			return ReflectionUtils.equals(this, that);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return ReflectionUtils.toString(this);
		}

		/**
		 * The name of the property.
		 * 
		 * @return The name of the property.
		 */
		public String getName() {
			return name;
		}

		/**
		 * The text of the property.
		 * 
		 * @return The text of the property.
		 */
		public String getText() {
			return text;
		}

		/**
		 * The URL of the property.
		 * 
		 * @return The URL of the property.
		 */
		public String getHref() {
			return href;
		}
	}

	/**
	 * Represents the <a
	 * href="http://developers.facebook.com/docs/reference/api/post">Likes Graph
	 * API type</a>
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.6
	 */
	public static class Likes implements Serializable {
		@Facebook
		private Long count;

		@Facebook
		private List<NamedFacebookType> data = new ArrayList<NamedFacebookType>();

		private static final long serialVersionUID = 1L;

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return ReflectionUtils.hashCode(this);
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object that) {
			return ReflectionUtils.equals(this, that);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return ReflectionUtils.toString(this);
		}

		/**
		 * The number of likes.
		 * 
		 * @return The number of likes.
		 */
		public Long getCount() {
			return count;
		}

		/**
		 * The likes.
		 * 
		 * @return The likes.
		 */
		public List<NamedFacebookType> getData() {
			return unmodifiableList(data);
		}
	}

	/**
	 * Represents the <a
	 * href="http://developers.facebook.com/docs/reference/api/post">Comments
	 * Graph API type</a>.
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.5.3
	 */
	public static class Comments implements Serializable {
		@Facebook
		private Long count;

		@Facebook
		private List<Comment> data = new ArrayList<Comment>();

		private static final long serialVersionUID = 1L;

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return ReflectionUtils.hashCode(this);
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object that) {
			return ReflectionUtils.equals(this, that);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return ReflectionUtils.toString(this);
		}

		/**
		 * The number of comments.
		 * 
		 * @return The number of comments.
		 */
		public Long getCount() {
			return count;
		}

		/**
		 * The comments.
		 * 
		 * @return The comments.
		 */
		public List<Comment> getData() {
			return unmodifiableList(data);
		}
	}

	/**
	 * Represents the <a
	 * href="http://developers.facebook.com/docs/reference/api/post">Privacy
	 * Graph API type</a>.
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.5
	 */
	public static class Privacy implements Serializable {
		@Facebook
		private String value;

		@Facebook
		private String description;

		@Facebook
		private String friends;

		@Facebook
		private String networks;

		@Facebook
		private String deny;

		private static final long serialVersionUID = 1L;

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return ReflectionUtils.hashCode(this);
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object that) {
			return ReflectionUtils.equals(this, that);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return ReflectionUtils.toString(this);
		}

		/**
		 * The description of the privacy value.
		 * 
		 * @return The description of the privacy value.
		 */
		public String getValue() {
			return value;
		}

		/**
		 * The privacy description.
		 * 
		 * @return The privacy description.
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * The privacy friends restriction.
		 * 
		 * @return The privacy friends restriction.
		 */
		public String getFriends() {
			return friends;
		}

		/**
		 * The privacy networks restriction.
		 * 
		 * @return The privacy networks restriction.
		 */
		public String getNetworks() {
			return networks;
		}

		/**
		 * The privacy "deny" restriction.
		 * 
		 * @return The privacy "deny" restriction.
		 */
		public String getDeny() {
			return deny;
		}
	}

	/**
	 * Represents the <a
	 * href="http://developers.facebook.com/docs/reference/api/post">Action
	 * Graph API type</a>.
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.5
	 */
	public static class Action implements Serializable {
		@Facebook
		private String name;

		@Facebook
		private String link;

		private static final long serialVersionUID = 1L;

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return ReflectionUtils.hashCode(this);
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object that) {
			return ReflectionUtils.equals(this, that);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return ReflectionUtils.toString(this);
		}

		/**
		 * Gets the name of the action.
		 * 
		 * @return Gets the name of the action.
		 */
		public String getName() {
			return name;
		}

		/**
		 * The link for the action.
		 * 
		 * @return The link for the action.
		 */
		public String getLink() {
			return link;
		}
	}

	/**
	 * An object containing the ID and name of the user who posted the message.
	 * 
	 * @return An object containing the ID and name of the user who posted the
	 *         message.
	 */
	public CategorizedFacebookType getFrom() {
		return from;
	}

	/**
	 * The message.
	 * 
	 * @return The message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * If available, a link to the picture included with this post.
	 * 
	 * @return If available, a link to the picture included with this post.
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * The link attached to this post.
	 * 
	 * @return The link attached to this post.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * The caption of the link (appears beneath the link name).
	 * 
	 * @return The caption of the link (appears beneath the link name).
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * A description of the link (appears beneath the link caption).
	 * 
	 * @return A description of the link (appears beneath the link caption).
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * If available, the source link attached to this post (for example, a flash
	 * or video file).
	 * 
	 * @return If available, the source link attached to this post (for example,
	 *         a flash or video file).
	 */
	public String getSource() {
		return source;
	}

	/**
	 * A link to an icon representing the type of this post.
	 * 
	 * @return A link to an icon representing the type of this post.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * A string indicating which application was used to create this post.
	 * 
	 * @return A string indicating which application was used to create this
	 *         post.
	 */
	public String getAttribution() {
		return attribution;
	}

	/**
	 * The privacy settings for this post.
	 * 
	 * @return The privacy settings for this post.
	 */
	public Privacy getPrivacy() {
		return privacy;
	}

	/**
	 * The type of post this is, for example {@code "link"}.
	 * 
	 * @return The type of post this is, for example {@code "link"}.
	 */
	public String getType() {
		return type;
	}

	/**
	 * The application used to create this post.
	 * 
	 * @return The application used to create this post.
	 */
	public NamedFacebookType getApplication() {
		return application;
	}

	/**
	 * The number of likes on this post.
	 * 
	 * @return The number of likes on this post.
	 */
	public Long getLikesCount() {
		if (getLikes() != null)
			return getLikes().getCount();

		return likesCount;
	}

	/**
	 * The likes on this post.
	 * <p>
	 * Sometimes this can be {@code null} - check {@link #getLikesCount()}
	 * instead in that case.
	 * 
	 * @return The likes on this post.
	 */
	public Likes getLikes() {
		return likes;
	}

	/**
	 * The time the post was initially published.
	 * 
	 * @return The time the post was initially published.
	 */
	public Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}

	/**
	 * The time of the last comment on this post.
	 * 
	 * @return The time of the last comment on this post.
	 */
	public Date getUpdatedTime() {
		return toDateFromLongFormat(updatedTime);
	}

	/**
	 * The Facebook object id for an uploaded photo or video.
	 * 
	 * @return The Facebook object id for an uploaded photo or video.
	 * @since 1.6.5
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * The comments for this post.
	 * 
	 * @return The comments for this post.
	 */
	public Comments getComments() {
		return comments;
	}

	/**
	 * The place where this post occurred.
	 * 
	 * @return The place where this post occurred.
	 * @since 1.6.8
	 */
	public PostPlace getPlace() {
		return place;
	}

	/**
	 * A list of the profiles mentioned or targeted in this post.
	 * 
	 * @return A list of the profiles mentioned or targeted in this post.
	 */
	public List<NamedFacebookType> getTo() {
		return unmodifiableList(to);
	}

	/**
	 * A list of available action names and links (including commenting, liking,
	 * and an optional app-specified action).
	 * 
	 * @return A list of available action names and links (including commenting,
	 *         liking, and an optional app-specified action).
	 */
	public List<Action> getActions() {
		return unmodifiableList(actions);
	}

	/**
	 * A list of properties for this post.
	 * <p>
	 * This field is undocumented.
	 * 
	 * @return A list of properties for this post.
	 */
	public List<Property> getProperties() {
		return unmodifiableList(properties);
	}

	public Post() {
		facebookClient = new DefaultFacebookClient(AccessToken.getAccessToken());
	}

	public Post createInstance(String id) {
		this.id = id;
		return facebookClient.fetchObject(id, Post.class);
	}

	public Connection<Comment> comments() {
		return facebookClient.fetchConnection(id + "/comments", Comment.class);
	}
	
	public Connection<NamedFacebookType> likePeople() {
		return facebookClient.fetchConnection(id + "/likes", NamedFacebookType.class);
	}
	
	public FacebookType publishComment(String comment) {
		ArrayList<Parameter> parameterList = new ArrayList<Parameter>();
		if(!comment.equals("")){
			parameterList.add(Parameter.with(Arguments.COMMENTS.MESSAGE, comment));
		}
		
		return facebookClient.publish(id + "/comments", FacebookType.class, parameterList);
	}
	
}