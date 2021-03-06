/*
 * Copyright 2015 Coursera Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.coursera.courier.android;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.coursera.courier.lang.DocCommentStyle;
import org.coursera.courier.lang.DocEscaping;

public class JavadocEscaping {
  /**
   * Returns a javadoc comment, as a string of java source, for the given documentation string
   * and deprecated property.
   *
   * @param doc provides the javadoc for the symbol, if any.  May be null.
   * @param deprecatedProp provides the deprecated Pegasus schema property, if any.  May be a
   *                       Boolean, a String, or null.
   * @return an escaped javadoc string.
   */
  public static String stringToJavadoc(String doc, Object deprecatedProp) {
    boolean emptyDoc = (doc == null || doc.trim().isEmpty());
    boolean emptyDeprecated = (deprecatedProp == null || !(deprecatedProp instanceof String));

    if (emptyDoc && emptyDeprecated) return "";

    String javadocBody = emptyDoc ? "" : (doc + "\n");
    String deprecated = (emptyDeprecated) ? "" : ("@deprecated " + deprecatedProp + "\n");

    String text = javadocBody + deprecated;
    return DocEscaping.stringToDocComment(text, DocCommentStyle.ASTRISK_MARGIN);
  }
}
