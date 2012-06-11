package com.facebook.halo.application.types;

import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.core.Connection;

public interface PostInterface {
	
	/**
	 * post instance return
	 * @param id
	 * @return post instance
	 */
	public Post createInstance(String id);
	
	/**
	 * get post's comment connection
	 * @return post's comment
	 */
	public Connection<Comment> comments();
	
	/**
	 * get post's like connection
	 * @return post's likes
	 */
	public Connection<NamedFacebookType> likePeople();
	
	/**
	 * publish reply to the feed
	 * @return comment id
	 * @param comment text
	 */
	public FacebookType publishComment(String comment); 
}
