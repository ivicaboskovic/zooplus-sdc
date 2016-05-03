package com.zooplus.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "CURRENCY", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name", name = Currency.CURRENCY_CODE_UC)
})
@NamedQueries({ 
	@NamedQuery(name = Currency.FIND_CURRENCIES, query = "SELECT c FROM Currency c order by c.name ASC"),

})
public class Currency extends BaseEntity<Long> {
			
	public static final String CURRENCY_CODE_UC = "CURRENCY_CODE_UC";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1437000511403313604L;
	
			
	public static final String FIND_CURRENCIES = "FIND_CURRENCIES";
	
	@Column(length = 3)
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
