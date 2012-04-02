package com.facebook.halo.application.types;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.Date;

import com.facebook.halo.application.types.infra.NamedFacebookType;
/**
 * Order object type
 * @author JM
 *
 */
public class Order {
	private NamedFacebookType id;
	private String from;
	private int amount;
	private String status;
	private NamedFacebookType application;
	private String country;
	private String refundReasonCode;
	private String createTime;
	private String updatedTime;
	
	public NamedFacebookType getId() {
		return id;
	}
	public String getFrom() {
		return from;
	}
	public int getAmount() {
		return amount;
	}
	public String getStatus() {
		return status;
	}
	public NamedFacebookType getApplication() {
		return application;
	}
	public String getCountry() {
		return country;
	}
	public String getRefundReasonCode() {
		return refundReasonCode;
	}
	public Date getCreateTime() {
		return toDateFromLongFormat(createTime);
	}
	public Date getUpdatedTime() {
		return toDateFromLongFormat(updatedTime);
	}
	
	
}
