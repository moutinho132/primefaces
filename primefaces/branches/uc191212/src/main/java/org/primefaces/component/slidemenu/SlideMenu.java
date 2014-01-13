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
package org.primefaces.component.slidemenu;

import org.primefaces.component.menu.AbstractMenu;
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
	@ResourceDependency(library="primefaces", name="jquery/jquery-plugins.js"),
	@ResourceDependency(library="primefaces", name="primefaces.js")
})
public class SlideMenu extends AbstractMenu implements org.primefaces.component.api.Widget,org.primefaces.component.menu.OverlayMenu {


	public static final String COMPONENT_TYPE = "org.primefaces.component.SlideMenu";
	public static final String COMPONENT_FAMILY = "org.primefaces.component";
	private static final String DEFAULT_RENDERER = "org.primefaces.component.SlideMenuRenderer";
	private static final String OPTIMIZED_PACKAGE = "org.primefaces.component.";

	protected enum PropertyKeys {

		widgetVar
		,model
		,style
		,styleClass
		,backLabel
		,trigger
		,my
		,at
		,overlay
		,triggerEvent;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
}
	}

	public SlideMenu() {
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

	public org.primefaces.model.MenuModel getModel() {
		return (org.primefaces.model.MenuModel) getStateHelper().eval(PropertyKeys.model, null);
	}
	public void setModel(org.primefaces.model.MenuModel _model) {
		getStateHelper().put(PropertyKeys.model, _model);
		handleAttribute("model", _model);
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

	public java.lang.String getBackLabel() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.backLabel, "Back");
	}
	public void setBackLabel(java.lang.String _backLabel) {
		getStateHelper().put(PropertyKeys.backLabel, _backLabel);
		handleAttribute("backLabel", _backLabel);
	}

	public java.lang.String getTrigger() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.trigger, null);
	}
	public void setTrigger(java.lang.String _trigger) {
		getStateHelper().put(PropertyKeys.trigger, _trigger);
		handleAttribute("trigger", _trigger);
	}

	public java.lang.String getMy() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.my, null);
	}
	public void setMy(java.lang.String _my) {
		getStateHelper().put(PropertyKeys.my, _my);
		handleAttribute("my", _my);
	}

	public java.lang.String getAt() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.at, null);
	}
	public void setAt(java.lang.String _at) {
		getStateHelper().put(PropertyKeys.at, _at);
		handleAttribute("at", _at);
	}

	public boolean isOverlay() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.overlay, false);
	}
	public void setOverlay(boolean _overlay) {
		getStateHelper().put(PropertyKeys.overlay, _overlay);
		handleAttribute("overlay", _overlay);
	}

	public java.lang.String getTriggerEvent() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.triggerEvent, "click");
	}
	public void setTriggerEvent(java.lang.String _triggerEvent) {
		getStateHelper().put(PropertyKeys.triggerEvent, _triggerEvent);
		handleAttribute("triggerEvent", _triggerEvent);
	}


    public static final String STATIC_CONTAINER_CLASS = "ui-menu ui-slidemenu ui-widget ui-widget-content ui-corner-all ui-helper-clearfix";
    public static final String DYNAMIC_CONTAINER_CLASS = "ui-menu ui-slidemenu ui-menu-dynamic ui-widget ui-widget-content ui-corner-all ui-helper-clearfix ui-shadow";
    public static final String WRAPPER_CLASS = "ui-slidemenu-wrapper";
    public static final String CONTENT_CLASS = "ui-slidemenu-content";
    public static final String BACKWARD_CLASS = "ui-slidemenu-backward ui-widget-header ui-corner-all ui-helper-clearfix";
    public static final String BACKWARD_ICON_CLASS = "ui-icon ui-icon-triangle-1-w";

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