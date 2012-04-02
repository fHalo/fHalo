package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User Object Connection type
 * @author immk
 *
 */
public class Poke{
	List<PokeInfo> data = new ArrayList<PokeInfo>();
	
	
	public final List<PokeInfo> getData() {
		return data;
	}
	
	/**
	 * User Object connection PokeInfo type
	 * @author immk
	 *
	 */


	public static class PokeInfo {
		private NamedFacebookType to;
		private NamedFacebookType from;
		private String createdTime;
		private String type;
		
		public final NamedFacebookType getTo() {
			return to;
		}
		public final NamedFacebookType getFrom() {
			return from;
		}
		public final Date getCreatedTime() {
			return toDateFromLongFormat(createdTime);
		}
		public final String getType() {
			return type;
		}		
	}
}
