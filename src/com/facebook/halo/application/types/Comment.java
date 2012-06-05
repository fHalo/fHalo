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

import java.util.Date;

import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.annotation.Facebook;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.core.DefaultFacebookClient;
import com.facebook.halo.framework.core.FacebookClient;

/**
 * Represents the <a
 * href="http://developers.facebook.com/docs/reference/api/event">Comment Graph
 * API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Comment extends FacebookType implements CommentInterface{
	
	private FacebookClient facebookClient;

	@Facebook
	private NamedFacebookType from;

	@Facebook
	protected String message;

	@Facebook("created_time")
	private String createdTime;

	@Facebook
	private Long likes;

	@Facebook("user_likes")
	private boolean userLikes;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public Comment() {
		facebookClient = new DefaultFacebookClient(AccessToken.getAccessToken());
	}
	

	/**
	 * User who posted the comment.
	 * 
	 * @return User who posted the comment.
	 */
	public NamedFacebookType getFrom() {
		return from;
	}

	/**
	 * Text contents of the comment.
	 * 
	 * @return Text contents of the comment.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Date on which the comment was created.
	 * 
	 * @return Date on which the comment was created.
	 */
	public Date getCreatedTime() {
		return toDateFromLongFormat(createdTime);
	}

	/**
	 * The number of likes on this comment.
	 * 
	 * @return The number of likes on this comment.
	 */
	public Long getLikes() {
		return likes;
	}

	/**
	 * Is user like or not
	 * 
	 * @return Is user like or not
	 */
	public boolean getUserLikes() {
		return userLikes;
	}

	/**
	 * Comment instance return
	 * 
	 * @param id
	 * @return User instance
	 */
	@Override
	public Comment createInstance(String id) {
		this.id = id;
		return facebookClient.fetchObject(id, Comment.class);
	}
}