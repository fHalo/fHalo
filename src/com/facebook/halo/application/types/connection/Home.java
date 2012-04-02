package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.Post;

/**
 * User Object Connection type
 * @author JM
 *
 */
public class Home {
	List<Post> data = new ArrayList<Post>();

	public final List<Post> getData() {
		return data;
	}
}
