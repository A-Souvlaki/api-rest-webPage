package com.icesi.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the customer database table.
 *
 */
@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CUSTOMER_CUSTOMERID_GENERATOR", allocationSize = 1, sequenceName = "CUSTOMER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_CUSTOMERID_GENERATOR")
	private Integer customerid;

	private Timestamp modifieddate;
	@NotNull
	private Integer personid;

	private Integer rowguid;
	
	// bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name = "storeid")
	private Store store;

	public Customer() {
	}


	public Integer getCustomerid() {
		return this.customerid;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getPersonid() {
		return this.personid;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	

	public Store getStore() {
		return this.store;
	}

	
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}