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
package org.primefaces.component.export;

import java.io.IOException;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.datatable.DataTable;

public class DataExporter implements ActionListener, StateHolder {

	private ValueExpression target;
	
	private ValueExpression type;
	
	private ValueExpression fileName;
	
	private ValueExpression encoding;
	
	private ValueExpression pageOnly;
    
	private ValueExpression selectionOnly;

	private ValueExpression excludeColumns;
	
	private MethodExpression preProcessor;
	
	private MethodExpression postProcessor;
	
	public DataExporter() {}

	public DataExporter(ValueExpression target, ValueExpression type, ValueExpression fileName, ValueExpression pageOnly, ValueExpression selectionOnly, ValueExpression exludeColumns, ValueExpression encoding, MethodExpression preProcessor, MethodExpression postProcessor) {
		this.target = target;
		this.type = type;
		this.fileName = fileName;
		this.pageOnly = pageOnly;
		this.selectionOnly = selectionOnly;
		this.excludeColumns = exludeColumns;
		this.preProcessor = preProcessor;
		this.postProcessor = postProcessor;
		this.encoding = encoding;
	}

	public void processAction(ActionEvent event){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		
		String tableId = (String) target.getValue(elContext);
		String exportAs = (String) type.getValue(elContext);
		String outputFileName = (String) fileName.getValue(elContext);
	
		String encodingType = "UTF-8";
		if(encoding != null) 
			encodingType = (String) encoding.getValue(elContext);

		int[] excludedColumnIndexes = null;
		if(excludeColumns != null) {
			excludedColumnIndexes = resolveExcludedColumnIndexes((String) excludeColumns.getValue(elContext));
        }
		
		boolean isPageOnly = false;
		if(pageOnly != null) {
			isPageOnly = pageOnly.isLiteralText() ? Boolean.valueOf(pageOnly.getValue(facesContext.getELContext()).toString()) : (Boolean) pageOnly.getValue(facesContext.getELContext());
		}
		
        boolean isSelectionOnly = false;
		if(selectionOnly != null) {
			isSelectionOnly = selectionOnly.isLiteralText() ? Boolean.valueOf(selectionOnly.getValue(facesContext.getELContext()).toString()) : (Boolean) selectionOnly.getValue(facesContext.getELContext());
		}
		
		try {
			Exporter exporter = ExporterFactory.getExporterForType(exportAs);
			UIComponent component = event.getComponent().findComponent(tableId);
			if(component == null)
				throw new FacesException("Cannot find component \"" + tableId + "\" in view.");
			if(!(component instanceof DataTable))
				throw new FacesException("Unsupported datasource target:\"" + component.getClass().getName() + "\", exporter must target a PrimeFaces DataTable.");
			
			DataTable table = (DataTable) component;
			exporter.export(facesContext, table, outputFileName, isPageOnly, isSelectionOnly, excludedColumnIndexes, encodingType, preProcessor, postProcessor);
			
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	private int[] resolveExcludedColumnIndexes(String columnsToExclude) {
        if(columnsToExclude == null || columnsToExclude.equals("")) {
            return null;
        }
        else {
            String[] columnIndexesAsString = columnsToExclude.split(",");
            int[] indexes = new int[columnIndexesAsString.length];

            for(int i=0; i < indexes.length; i++) {
                indexes[i] = Integer.parseInt(columnIndexesAsString[i].trim());
            }

            return indexes;
        }
	}

	public boolean isTransient() {
		return false;
	}

	public void setTransient(boolean value) {
		//NoOp
	}
	
	 public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;

		target = (ValueExpression) values[0];
		type = (ValueExpression) values[1];
		fileName = (ValueExpression) values[2];
		pageOnly = (ValueExpression) values[3];
		selectionOnly = (ValueExpression) values[4];
		excludeColumns = (ValueExpression) values[5];
		preProcessor = (MethodExpression) values[6];
		postProcessor = (MethodExpression) values[7];
		encoding = (ValueExpression) values[8];
	}

	public Object saveState(FacesContext context) {
		Object values[] = new Object[9];

		values[0] = target;
		values[1] = type;
		values[2] = fileName;
		values[3] = pageOnly;
		values[4] = selectionOnly;
		values[5] = excludeColumns;
		values[6] = preProcessor;
		values[7] = postProcessor;
		values[8] = encoding;
		
		return ((Object[]) values);
	}
}