package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.Date;
import com.facebook.halo.application.types.infra.CategorizedFacebookType;
import com.facebook.halo.framework.core.Connection;

/**
 * User Object connection type - for LikePage in User Profile
 * @author JM
 *
 */
public class Favorite {
	private Connection<LikesInfo> activities;
	private Connection<LikesInfo> books;
	private Connection<LikesInfo> games;
	private Connection<LikesInfo> interests;
	private Connection<LikesInfo> movies;
	private Connection<LikesInfo> music;
	private Connection<LikesInfo> television;
	
	public Connection<LikesInfo> getActivities() {
		return activities;
	}

	public Connection<LikesInfo> getBooks() {
		return books;
	}

	public Connection<LikesInfo> getGames() {
		return games;
	}

	public Connection<LikesInfo> getInterests() {
		return interests;
	}

	public Connection<LikesInfo> getMovies() {
		return movies;
	}


	public Connection<LikesInfo> getMusic() {
		return music;
	}

	public Connection<LikesInfo> getTelevision() {
		return television;
	}
	
	/**
	 * User Object connection LikesInfo type
	 * @author JM
	 *
	 */
	public static class LikesInfo extends CategorizedFacebookType{
		private String createdTime;

		public Date getCreatedTime() {
			return toDateFromLongFormat(createdTime);
		}

	}
}
