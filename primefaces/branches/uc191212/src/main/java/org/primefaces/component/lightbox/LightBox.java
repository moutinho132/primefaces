/*
 * Generated, Do Not Modify
 */
/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.component.lightbox;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.component.UINamingContainer;
import javax.el.ValueExpression;
import javax.el.MethodExpression;
import javax.faces.render.Renderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import java.util.List;
import java.util.ArrayList;

@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="primefaces.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="primefaces.js")
})
public class LightBox extends UIComponentBase implements org.primefaces.component.api.Widget {


	public static final String COMPONENT_TYPE = "org.primefaces.component.LightBox";
	public static final String COMPONENT_FAMILY = "org.primefaces.component";
	private static final String DEFAULT_RENDERER = "org.primefaces.component.LightBoxRenderer";
	private static final String OPTIMIZED_PACKAGE = "org.primefaces.component.";

	protected enum PropertyKeys {

		widgetVar
		,style
		,styleClass
		,width
		,height
		,iframe
		,iframeTitle
		,visible
		,onShow
		,onHide;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
}
	}

	public LightBox() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public java.lang.String getWidgetVar() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}
	public void setWidgetVar(java.lang.String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
		handleAttribute("widgetVar", _widgetVar);
	}

	public java.lang.String getStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
	}
	public void setStyle(java.lang.String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
		handleAttribute("style", _style);
	}

	public java.lang.String getStyleClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
	}
	public void setStyleClass(java.lang.String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
		handleAttribute("styleClass", _styleClass);
	}

	public java.lang.String getWidth() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.width, null);
	}
	public void setWidth(java.lang.String _width) {
		getStateHelper().put(PropertyKeys.width, _width);
		handleAttribute("width", _width);
	}

	public java.lang.String getHeight() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.height, null);
	}
	public void setHeight(java.lang.String _height) {
		getStateHelper().put(PropertyKeys.height, _height);
		handleAttribute("height", _height);
	}

	public boolean isIframe() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.iframe, false);
	}
	public void setIframe(boolean _iframe) {
		getStateHelper().put(PropertyKeys.iframe, _iframe);
		handleAttribute("iframe", _iframe);
	}

	public java.lang.String getIframeTitle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.iframeTitle, null);
	}
	public void setIframeTitle(java.lang.String _iframeTitle) {
		getStateHelper().put(PropertyKeys.iframeTitle, _iframeTitle);
		handleAttribute("iframeTitle", _iframeTitle);
	}

	public boolean isVisible() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.visible, false);
	}
	public void setVisible(boolean _visible) {
		getStateHelper().put(PropertyKeys.visible, _visible);
		handleAttribute("visible", _visible);
	}

	public java.lang.String getOnShow() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onShow, null);
	}
	public void setOnShow(java.lang.String _onShow) {
		getStateHelper().put(PropertyKeys.onShow, _onShow);
		handleAttribute("onShow", _onShow);
	}

	public java.lang.String getOnHide() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onHide, null);
	}
	public void setOnHide(java.lang.String _onHide) {
		getStateHelper().put(PropertyKeys.onHide, _onHide);
		handleAttribute("onHide", _onHide);
	}


	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	public String resolveWidgetVar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	public void handleAttribute(String name, Object value) {
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if(setAttributes == null) {
			String cname = this.getClass().getName();
			if(cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}
		if(setAttributes != null) {
			if(value == null) {
				ValueExpression ve = getValueExpression(name);
				if(ve == null) {
					setAttributes.remove(name);
				} else if(!setAttributes.contains(name)) {
					setAttributes.add(name);
				}
			}
		}
	}
}