package com.facebook.halo.application.types;

import com.facebook.halo.application.types.connection.Albums;
import com.facebook.halo.application.types.connection.Checkins;
import com.facebook.halo.application.types.connection.Comments;
import com.facebook.halo.application.types.connection.Family;
import com.facebook.halo.application.types.connection.Feed;
import com.facebook.halo.application.types.connection.Friends;
import com.facebook.halo.application.types.connection.Links;
import com.facebook.halo.application.types.connection.Photos;
import com.facebook.halo.application.types.connection.Picture;
import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.framework.core.Connection;

public interface UserInterface {
	
	/**
	 * User instance return  
	 * @param id
	 * @return User instance
	 */
	public User createInstance(String id);

	public Connection<Friends> friends();

	public Connection<Family> family();

	public Connection<Feed> feeds();

	public Connection<Album> albums();
	
	public Connection<Comment> comments(String objectId);
	
	public String picture();
	/**
	 * Picture URL
	 * @param profileId
	 * @param feed
	 * @return
	 */
	public FacebookType publishFeed(String profileId, Feed feed);
	
	public FacebookType publishComments(String objectId, Comments comments);
	
	public boolean publishUndoLikes(String objectId);
	
	public boolean publishLikes(String objectId);
	/**
	 * The parameter link must be required (URL)
	 * picture URL
	 * @param links
	 * @return
	 */
	public FacebookType publishLinks(Links links);
	/**
	 * Valid ID for album owner	
	 * The parameter name must be required
	 * @param profileId
	 * @param albums
	 * @return
	 */
	
	public FacebookType publishAlbums(Albums albums);
	
	/**
	 * Binary attachment data cannot be null.
	 * @param albumId
	 * @param photos
	 * @return
	 */
	public FacebookType publishPhotos(String albumId, Photos photos);
	
	/**
	 * The parameter place must be required
	 * The parameter coordinate must be required
	 * @param profileId
	 * @param checkins
	 * @return
	 */
	public FacebookType publishCheckins(Checkins checkins);
	/**
	 * delete method
	 * @param objectId
	 * @return
	 */
	public boolean delete(String objectId);

}
