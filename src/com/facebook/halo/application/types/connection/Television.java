package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.infra.CategorizedFacebookType;

/**
 * User Object Connection type
 * @author immk
 *
 */
public class Television {
	
	List<TelevisionInfo> data = new ArrayList<TelevisionInfo>();
	
	/**
	 * User Object connection TelevisionInfo type
	 * @author immk
	 *
	 */
	
	public class TelevisionInfo extends CategorizedFacebookType{
		
		String createTime;
		
		public Date getCreateTime() {
			return toDateFromLongFormat(createTime);
		}
		
	}

}
