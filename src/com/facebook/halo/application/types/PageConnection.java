/*
 * Copyright (c) 2010-2011 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.facebook.halo.application.types;

import java.util.Date;

import static com.facebook.halo.framework.formatter.DateUtils.toDateFromLongFormat;

import com.facebook.halo.application.types.infra.CategorizedFacebookType;
import com.facebook.halo.framework.annotation.Facebook;

/**
 * Represents a Connection to a <a
 * href="http://developers.facebook.com/docs/reference/api/page">Page Graph API
 * type</a>, e.g. the Pages returned from {@code me/music}.
 * 
 * @author Patrick Alberts
 * @since 1.6.3
 */
public class PageConnection extends CategorizedFacebookType {
  @Facebook("created_time")
  private String createdTime;

  private static final long serialVersionUID = 1L;
  
  /**
   * The time the connection was initially created.
   * 
   * @return The time the connection was initially created.
   */
  public Date getCreatedTime() {
    return toDateFromLongFormat(createdTime);
  }
}