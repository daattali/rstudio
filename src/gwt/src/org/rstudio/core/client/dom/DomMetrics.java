/*
 * DomMetrics.java
 *
 * Copyright (C) 2009-11 by RStudio, Inc.
 *
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.core.client.dom;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import org.rstudio.core.client.Size;

public class DomMetrics
{ 
   public static Size measureHTML(String html, String styleName)
   {
      // create HTML widget which matches the specified style
      HTML measureHTML = new HTML();
      measureHTML.setStyleName(styleName);
      measureHTML.getElement().getStyle().setFloat(Style.Float.LEFT);
      measureHTML.setWordWrap(false);
      
      // add it to the dom (hidden)
      RootPanel.get().add(measureHTML, -2000, -2000);
      
      // insert the text (preformatted) and measure it
      measureHTML.setHTML(html);
      Size textSize = new Size(measureHTML.getOffsetWidth(), 
                               measureHTML.getOffsetHeight());
      RootPanel.get().remove(measureHTML);
      
      // return the size
      return textSize; 
   }
   
   public static Size adjustedElementSize(Size contentSize, 
                                          Size minimumSize,
                                          int contentPad,
                                          int clientMargin)
   {
      // add the padding
      contentSize = new Size(contentSize.width + contentPad,
                             contentSize.height + contentPad);
      
      // enforce the minimum (if specified)
      if (minimumSize != null)
      {
         contentSize = new Size(Math.max(contentSize.width, minimumSize.width),
                                Math.max(contentSize.height, minimumSize.height));
      }
      
      // maximum is client area - (margin * 2)
      Size maximumSize = new Size(Window.getClientWidth() - (clientMargin*2),
                                  Window.getClientHeight() - (clientMargin*2));
      int width = Math.min(contentSize.width, maximumSize.width);
      int height = Math.min(contentSize.height, maximumSize.height);
      return new Size(width, height);
   }
}
