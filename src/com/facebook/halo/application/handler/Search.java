package com.facebook.halo.application.handler;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.Checkin.Place;
import com.facebook.halo.framework.common.AccessToken;
import com.facebook.halo.framework.common.Parameter;
import com.facebook.halo.framework.core.Connection;
import com.facebook.halo.framework.core.DefaultFacebookClient;
import com.facebook.halo.framework.core.FacebookClient;

/**
 * Facebook Graph Api Search
 * @author immk
 */
public class Search {
	
	FacebookClient facebookClient;
	
	public Search(){
		facebookClient = new DefaultFacebookClient(AccessToken.getAccessToken());
	}
	
/*	public <T> List<T> search(FQL fql, Class<T> objectType) {
		int count = 0;
		String query = "SELECT ";
		for(String field : fql.getFields()){
			
			if(count == fql.getFields().length-1) 
				query += field;
			else
				query += field + ", ";
			count++;
		}
		
		query += " FROM " + fql.getTable();
		count =0;
		for(String where : fql.getConditions()){
			if(count == 0)
				query += " WHERE ";
			
			if(count == fql.getConditions().length-1)
				query += where;
			else
				query += where +" AND ";
			count++;
		}
		
		return facebookClient.executeQuery(query, objectType);

//			String table
//			 = "CONTACT_TAGS";
//			String[] columns = {"_id"};
//			String where = "TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
//			String[] args = {"tagname", "tagname", "tagname", "tagname", "tagname"};
//			
//			SQLiteDatabase db = [yourDatabaseHelper].getReadableDatabase();
//			Cursor cursor = db.query(table, columns, where, args, null, null, null);

	}*/
	
	/**
	 * @param query       search keyword
	 * @param objectType  return object
	 * @return List<T>
	 */
	public <T> List<T> search(String query, Class<T> objectType) {
		return facebookClient.executeQuery(query, objectType);
	}
	
	/**
	 * @param fields      select fields
	 * @param query       search keyword
	 * @param type        table name
	 * @param objectType  return object
	 * @return Connection<T>
	 */
	public <T> Connection<T> search (String fields, String query, String type, Class<T> objectType){
		
		ArrayList<Parameter> parameterList = new ArrayList<Parameter>();
		if(fields != null)
			parameterList.add(Parameter.with("fields", fields));
		
		if(query != null)
			parameterList.add(Parameter.with("q", query));
		
		if(type != null)
			parameterList.add(Parameter.with("type", type));
		
		
		
		return facebookClient.fetchConnection("search", objectType, parameterList);
	} 
		
	/**
	 * @param fields	select fields
	 * @param query		search keyword
	 * @param latitude	latitude
	 * @param longitude	longitude
	 * @param distance	distance
	 * @return Connection<Place>
	 */
	public Connection<Place> searchPlace (String fields, String query, String latitude, String longitude, int distance){
		
//		Places: https://graph.facebook.com/search?q=coffee&type=place&center=37.76,122.427&distance=1000
		
		ArrayList<Parameter> parameterList = new ArrayList<Parameter>();			
		parameterList.add(Parameter.with("type", "place"));
		if(fields != null)
			parameterList.add(Parameter.with("fields", fields));
		
		if(query != null)
			parameterList.add(Parameter.with("q", query));
		
		if(latitude != null && longitude != null)
			parameterList.add(Parameter.with("center", latitude + ",-" + longitude));

		if(distance != 0)
			parameterList.add(Parameter.with("distance", distance));
		
		return facebookClient.fetchConnection("search", Place.class, parameterList);
	} 
}
