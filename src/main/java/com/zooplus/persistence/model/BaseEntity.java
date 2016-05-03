package com.zooplus.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 
 * Base enitity class with ID. When extending the actual type
 * of ID should be supplied.
 * 
 * @author ivica.boskovic
 *
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		BaseEntity<T> other = (BaseEntity<T>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private T id;
	
	private Date created;
	
	private Date updated;

	

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	/**
	 * Returns true in case Hibernate has already persisted this entity
	 * an MySQL has provided identity value for {@#id} field, false
	 * otherwise meaning the entity is still transient.
	 * 
	 * @return 
	 */
	public boolean isPersistent() {
		return (id != null) ? true : false;
	}
	
	@PrePersist
	protected void onCreate() {
		created = new Date();
		updated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}
		
}
