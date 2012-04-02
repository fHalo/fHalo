package com.facebook.halo.application.types;

import com.facebook.halo.application.types.infra.CategorizedFacebookType;
import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * QuestionOption object type
 * @author smjxx2000
 *
 */
public class QuestionOption extends NamedFacebookType{
	
	private NamedFacebookType from;
	private int votes;
	private CategorizedFacebookType object;
	private String createTime;
	
	public NamedFacebookType getFrom() {
		return from;
	}
	public int getVotes() {
		return votes;
	}
	public CategorizedFacebookType getObject() {
		return object;
	}
	public String getCreateTime() {
		return createTime;
	}
	
	
}