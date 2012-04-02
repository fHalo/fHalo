package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * Page object connection type
 * @author smjxx2000
 */
public class Tabs {

	List<TabsInfo> data = new ArrayList<Tabs.TabsInfo>();
	
	
	/**
	 * Page object connection TabsInfo type
	 * @author smjxx2000
	 */
	public class TabsInfo extends NamedFacebookType{
		String link;
		NamedFacebookType application;
		String customName;
		boolean isPermanent;
		int position;
		boolean isNonConnectionLandingTab;
		
	}
}
