package com.facebook.halo.application.types.connection;

import java.util.List;


/**
 * User object connection type
 * Page object connection type
 * @author smjxx2000
 *
 */
public class Checkins {
	
	private Coordinate coordinates;
	private String place;
	private String message;
	private List<String> tags;

	public Coordinate getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setCoordinates(String latitude, String longitude) {
		this.coordinates = new Coordinate(latitude, longitude);
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public class Coordinate {
		private String latitude;
		private String longitude;
		
		public Coordinate(String latitude, String longitude) {
			setLatitude(latitude);
			setLongitude(longitude);
		}		
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
	} 
}
