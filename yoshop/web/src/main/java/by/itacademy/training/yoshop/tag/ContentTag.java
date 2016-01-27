package by.itacademy.training.yoshop.tag;

/*
 * Copyright 2008 Claudius Hauptmann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ContentTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String placeholder;

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	@Override
	public int doAfterBody() throws JspException {
		// Store the parameter value into the page context
		String bodyContent = getBodyContent().getString();
		getParams().put(placeholder, bodyContent);

		return SKIP_BODY;
	}

	@Override
	public void release() {
		placeholder = null;
	}

	@SuppressWarnings("unchecked")
	public Dictionary<String, String> getParams() {
		Dictionary<String, String> params = (Dictionary<String, String>) pageContext.getAttribute("jsp-decorator",
				PageContext.REQUEST_SCOPE);
		if (params == null) {
			params = new Hashtable<String, String>();
			pageContext.setAttribute("jsp-decorator", params, PageContext.REQUEST_SCOPE);
		}
		return params;
	}
}
