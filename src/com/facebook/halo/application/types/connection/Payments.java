package com.facebook.halo.application.types.connection;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.Order;

/**
 * User Object connection type
 * @author JM
 *
 */
public class Payments {
	List<Order> data = new ArrayList<Order>();

	public final List<Order> getData() {
		return data;
	}	
}
