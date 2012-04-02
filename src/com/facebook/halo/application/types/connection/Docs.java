package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.Date;

import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * Group object connection type
 * @author JM
 *
 */
public class Docs{
	private String id;
	private NamedFacebookType from;
	private String subject;
	private String message;
	private String icon;
	private String updatedTime;
	private String revision;
	private boolean canEdit;
	private boolean canDelete;
	
	public final String getId() {
		return id;
	}
	public final NamedFacebookType getFrom() {
		return from;
	}
	public final String getSubject() {
		return subject;
	}
	public final String getMessage() {
		return message;
	}
	public final String getIcon() {
		return icon;
	}
	public final Date getUpdatedTime() {
		return toDateFromLongFormat(updatedTime);
	}
	public final String getRevision() {
		return revision;
	}
	public final boolean isCanEdit() {
		return canEdit;
	}
	public final boolean isCanDelete() {
		return canDelete;
	}
}
