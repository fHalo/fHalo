package com.facebook.halo.application.types;

import java.util.ArrayList;
import java.util.List;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * Question Object Type
 * @author smjxx2000
 *
 */
public class Question extends NamedFacebookType{
	
	private NamedFacebookType from;
	private String question;
	private String createTime;
	private String updateTime;
	List<QuestionOption> options = new ArrayList<QuestionOption>();
	
}