package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.Video;


/**
 * User object connection type
 * Page object connection type
 * @author JM
 *
 */
public class Videos {
	
	List<Video> data = new ArrayList<Video>();

	public final List<Video> getData() {
		return data;
	}

}
