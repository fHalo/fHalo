package com.facebook.halo.application.example;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import com.facebook.halo.application.handler.Delete;
import com.facebook.halo.application.handler.Search;
import com.facebook.halo.application.types.Album;
import com.facebook.halo.application.types.Checkin.Place;
import com.facebook.halo.application.types.Comment;
import com.facebook.halo.application.types.Event;
import com.facebook.halo.application.types.Page;
import com.facebook.halo.application.types.Post;
import com.facebook.halo.application.types.Post.Comments;
import com.facebook.halo.application.types.Tags;
import com.facebook.halo.application.types.User;
import com.facebook.halo.application.types.connection.Checkins;
import com.facebook.halo.application.types.connection.Feed;
import com.facebook.halo.application.types.connection.Friends;
import com.facebook.halo.application.types.connection.Photos;
import com.facebook.halo.application.types.fqlTable.FriendTable;
import com.facebook.halo.application.types.infra.FacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.core.Connection;


public class Example {
	static User user = new User();
	
	public static void main(String args[]) {
		
		//set accessToken (set your access token)
		AccessToken.setAccessToken("AAACEdEose0cBAP9AJrosWCSphQUoQTfLYgIEZAVIAFi2c5gZA4E5sXkZB1WZB0MUkOCeCMjiIQRa4UUKZCb6r4YD4sI8LUw4dFZA8mpWdLaOePILr7jLDg");
		
		Post p = new Post();
		p = p.createInstance("1233102578_3213209649135");
		Connection<NamedFacebookType> list = p.likes();
		
		for(List<NamedFacebookType> l : list)
			for(NamedFacebookType n : l)
				System.out.println(n.getName());
		
//		Comment c = new Comment();
//		c = c.createInstance("100000103386284_481100088570100_112639959");
//		System.out.println(c.getUserLikes());
		
//		user.publishUndoLikes("100002274717846_312388292180325");
		//create instance of me
//		Post post = new Post();

//		post = post.createInstance("100001345286735_359498797438293");
//		Likes likes = post.getLikes();
//		System.out.println("" + likes.getCount());
		
		//test method
//		getComments();
//		getMyFeeds();
//		getMyFriends();
//		getMyInfo();
//		publishFeed();
//		likeObject();
	}
	/**
	 * get post's comment message
	 */
	public static void getComments() {
		//create instance of me
		Post post = new Post();
		
		post = post.createInstance("100000877824590_400318026674121");
		Comments comments = post.getComments();
//		for(int i = 0; i < comments.getCount(); i++) {
//			System.out.println(""+comments.getData().get(i).getMessage());
//		}
		for(Comment c : comments.getData())
			System.out.println(""+c.getMessage());
	}
		
	/**
	 * like specific object(photo, feed ..)
	 * like feed contains "test" string in my timeline.
	*/
	public static void likeObject() {
		//Get my Feeds and like contains "test" string
		Connection<Feed> feeds = user.feeds();
		for (List<Feed> myFeeds : feeds)
			for (Feed feed : myFeeds) 
				if (feed.getMessage() != null && feed.getMessage().contains("test")) {
					user.publishLikes(feed.getId());
					return;
				}
	}
	
	/**
	 * Get user information(my information)
	*/
	public static void getMyInfo() {
		//Get my Name, Gender and Birthday
		System.out.println("my Name : " + user.getFirstName());
		System.out.println("my Gender : " + user.getGender());
		System.out.println("my Birthday : " + user.getBirthday());
	}
	
	/**
	 * Get user connection
	 * get user's friends
	 */
	public static void getMyFriends() {
		//Get my Friends
		Connection<Friends> friends = user.friends();
		for (List<Friends> myFriends : friends)
			for (Friends users : myFriends)
				System.out.println("My friends: " + users.getName());
	}
	
	/**
	 * Get user connection
	 * get user's timeline feeds
	 */
	public static void getMyFeeds() {
		//Get my Feeds
		Connection<Feed> feeds = user.feeds();
		for (List<Feed> myFeeds : feeds)
			for (Feed feed : myFeeds) 
				if (feed.getMessage() != null)
					System.out.println("my Feeds :" + feed.getMessage());
	}
	
	/**
	 * publish Feed(Write to Mikyung Kang's timeline)
	*/
	public static void publishFeed() {
		//set feed inforamtion
		Feed feed = new Feed();
		feed.setMessage("Message Test");
		feed.setCaption("Caption Test");
		feed.setDescription("Description Test");
		feed.setPicture("http://220.67.124.66/test.jpg");
		
		//set target user Id(MiKyung Kang)
		String targetUserId = null;
		Connection<Friends> friends = user.friends();
		for (List<Friends> myFriends : friends)
			for (Friends users : myFriends)
				if(users.getName().equals("MiKyung Kang")) {
					targetUserId = users.getId();
				}
		
		user.publishFeed(targetUserId, feed);
	}
	
	
	public static void deleteObject() {
		
		Feed feed = new Feed();
		feed.setMessage("Hello World ~ ");
		feed.setLink("http://www.facebook.com");
		feed.setDescription("Test Code");
		FacebookType type = user.publishFeed("me", feed);
		String feedId = type.getId();
		System.out.println("FEED ID : " + feedId);

		Delete delete = new Delete();
		boolean isDelete = delete.delete(feedId);
		System.out.println(isDelete);
	}
	
	public static void deleteObjectByType() {
		
		Feed feed = new Feed();
		feed.setMessage("Hello World ~ ");
		feed.setLink("http://www.facebook.com");
		feed.setDescription("Test Code");
		FacebookType type = user.publishFeed("me", feed);
		String feedId = type.getId();
		System.out.println("FEED ID : " + feedId);
		
		boolean isDelete = user.delete(feedId);
		System.out.println(isDelete);
	}
	
	public static void searchObject() {
		

		Search search = new Search();
		Connection<Event> events = search.search("name, location","conference", "event", Event.class);
		
		for (List<Event> eventList : events)
			for (Event event : eventList) 
				System.out.println("Event Name : " + event.getName() + " / Location : " + event.getLocation());
	}
	
	public static void searchPlaceObject() {
		
//		Places: https://graph.facebook.com/search?q=coffee&type=place&center=37.76,122.427&distance=1000
		Search search = new Search();			
		Connection<Place> places = search.searchPlace(null, "coffee", "37.76", "122.427", 1000);
		for (List<Place> placeList : places)
			for (Place place : placeList) 
				System.out.println("Place Name : " + place.getName() + " / Location : " + place.getLocation().getCountry() +", " + place.getLocation().getCity() + " / " );

	}
	
	public static void searchObjectByFql() {
		
		String query = "SELECT uid2 FROM friend WHERE uid1 = me()";
		Search search = new Search();
		List<FriendTable> users = search.search(query, FriendTable.class);

		for (FriendTable friend : users) {
			String friendId = friend.getUid2();
			
			User friendUser = new User();
			friendUser = friendUser.createInstance(friendId);
			System.out.println("Freind ID : " + friendId);
			System.out.println("Friend Name : " + friendUser.getName() + " / Gender : " + friendUser.getGender() + " / Locale : " + friendUser.getLocale());
		}
	}

	
	public static void uploadPhoto() {
		
		String albumId = null ;
		Connection<Album> albums = user.albums();
		for(List<Album> albumList : albums)
			for(Album album : albumList){
				
				System.out.println("Album ID : " + album.getId());
				if(album.getName().equals("유리방")) {
					albumId = album.getId();
					break;
				}
			}
				
		System.out.println("Select Album : " + albumId);
		
		Photos photos = new Photos();
		photos.setMessage("Upload Photo Test");
		photos.setSource("C:\\FacebookHalo\\sample_photo.JPG");
		photos.setFileName("Sample Photo");
		FacebookType type = user.publishPhotos(albumId, photos);
		System.out.println("Photos : " + type.getId());
	}
	
	public static void checkIn(){
		
		Checkins checkins= new Checkins();
		checkins.setMessage("Where R U ?");
		List<String> tags = new ArrayList<String>();
		tags.add("100002274717846");	// Friend1 Id
		tags.add("100001066448386");	// Friend2 Id
		checkins.setTags(tags);
		checkins.setCoordinates("36.436406", "128.024883");	// My Position
		checkins.setPlace("228600137193828");				// Place Position
		FacebookType type = user.publishCheckins(checkins);
		System.out.println("Checkins : " + type.getId());
	}
	
	public static void publishPhotoTags(){
		
//		[{"tag_uid":"100002274717846","x":"70.0","y":"70.0","tag_text":"some person"},{"tag_uid":"100001066448386","x":"70.0","y":"70.0","tag_text":"some person"}]
		ArrayList<Tags> tags = new ArrayList<Tags>();
		Tags tag = new Tags();
		tag.setTagUid("100002274717846");
		tag.setX("20");
		tag.setY("30");
		tags.add(tag);
		
		tag = new Tags();
		tag.setTagUid("100001066448386");
		tag.setX("30");
		tag.setY("40");
		tags.add(tag);
		
		boolean aa = user.publishTagsAtPhoto("366801743377453", tags);
		System.out.println(""+aa);
		
	}	
}
