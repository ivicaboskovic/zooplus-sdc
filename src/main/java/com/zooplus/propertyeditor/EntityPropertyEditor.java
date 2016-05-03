
package com.zooplus.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.zooplus.persistence.model.BaseEntity;
import com.zooplus.service.GenericService;


public class EntityPropertyEditor extends PropertyEditorSupport {

	@SuppressWarnings("rawtypes")
	private Class entityType;

	// service for entity loading
	private GenericService genericService;

	/**
	 * @param entityType
	 */
	@SuppressWarnings("rawtypes")
	public EntityPropertyEditor(Class entityType, GenericService genericService) {
		this.entityType = entityType;
		this.genericService = genericService;
	}

	/**
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String getAsText() {
		Object value = getValue();

		if (value instanceof BaseEntity) {
			return String.valueOf(((BaseEntity) value).getId());
		}

		return null;
	}

	/**
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text) && BaseEntity.class.isAssignableFrom(entityType)) {
			Long id = Long.valueOf(text);

			setValue(genericService.findById(entityType, id));
		}
		else {
			setValue(null);
		}
	}
}