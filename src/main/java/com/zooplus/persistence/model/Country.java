package com.zooplus.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "COUNTRY", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name", name = Country.COUNTRY_NAME_UC)
})
@NamedQueries({ 
	@NamedQuery(name = Country.FIND_COUNTRIES, query = "SELECT c FROM Country c order by c.name ASC"),

})
public class Country extends BaseEntity<Long> {
			
	public static final String COUNTRY_NAME_UC = "COUNTRY_NAME_UC";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1437000511403313604L;
	
			
	public static final String FIND_COUNTRIES = "FIND_COUNTRIES";
	
	@Column(length = 2)
	@NotEmpty
	private String code;
	
	@Column(length = 255)
	@NotEmpty
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
