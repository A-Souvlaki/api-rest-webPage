package com.icesi.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The primary key class for the personphone database table.
 * 
 */
@Entity
@NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
public class Phone implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "PHONE_PHONEID_GENERATOR", allocationSize = 1, sequenceName = "PHONE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONE_PHONEID_GENERATOR")
	private Integer id;
		
	@NotBlank(groups = BasicInfo.class)
	@Size(min = 10, max = 10)
	private String phonenumber;

	public Phone() {
	}
	
	
	public String getPhonenumber() {
		return this.phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}



}