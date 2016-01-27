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
import javax.servlet.jsp.tagext.TagSupport;

public class PlaceholderTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		Dictionary<String, String> params = getParams();

		String param = (String) params.get(name);

		if (param != null) {
			try {
				pageContext.getOut().write(param);
			} catch (Exception exception) {
				throw new JspException(exception.getMessage());
			}
		}

		return SKIP_BODY;
	}

	@Override
	public void release() {
		name = null;
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
