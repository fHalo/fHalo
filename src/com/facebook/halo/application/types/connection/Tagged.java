package com.facebook.halo.application.types.connection;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facebook.halo.application.types.Post.Action;
import com.facebook.halo.application.types.Post.Property;
import com.facebook.halo.application.types.infra.NamedFacebookType;

/**
 * User Object connection type
 * Page Object connection type
 * @author immk
 *
 */
public class Tagged extends NamedFacebookType{
		private NamedFacebookType from;
		private List<NamedFacebookType> to = new ArrayList<NamedFacebookType>();
		private String picture;
		private String link;
		private String caption;
		private String description;
		private List<Property> properties= new ArrayList<Property>();
		private String icon;
		private List<Action> action = new ArrayList<Action>();
		private NamedFacebookType application;
		private String createTime;
		private String updatedTime;

		public final NamedFacebookType getFrom() {
			return from;
		}
		public final List<NamedFacebookType> getTo() {
			return to;
		}
		public final String getPicture() {
			return picture;
		}
		public final String getLink() {
			return link;
		}
		public final String getCaption() {
			return caption;
		}
		public final String getDescription() {
			return description;
		}
		public final List<Property> getProperties() {
			return properties;
		}
		public final String getIcon() {
			return icon;
		}
		public final List<Action> getAction() {
			return action;
		}
		public final NamedFacebookType getApplication() {
			return application;
		}
		public final Date getCreateTime() {
			return toDateFromLongFormat(createTime);
		}
		public final Date getUpdatedTime() {
			return toDateFromLongFormat(updatedTime);
		}
}
