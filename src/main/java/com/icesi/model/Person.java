package com.icesi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * The persistent class for the person database table.
 *
 */
@Entity
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERSON_PERSONID_GENERATOR", allocationSize = 1, sequenceName = "PERSON_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_PERSONID_GENERATOR")
	private long personid;
	
	@NotBlank(groups = editInfo.class)
	private String additionalcontactinfo;
	@NotBlank(groups = editInfo.class)
	private String demographics;
	@NotNull(groups = editInfo.class)
	private Integer emailpromotion;
	
	@Size(min = 3, max = 20, groups = BasicInfo.class)
	private String firstname;
	@Size(min = 3, max = 20, groups = BasicInfo.class)
	private String lastname;
	@NotBlank(groups = editInfo.class)
	private String middlename;
	
	@NotNull(groups = BasicInfo.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;
	@NotBlank(groups = editInfo.class)
	private String namestyle;
	@NotBlank(groups = editInfo.class)
	private String persontype;
	@NotNull(groups = editInfo.class)
	private Integer rowguid;
	@NotBlank(groups = editInfo.class)
	private String suffix;
	@NotBlank(groups = editInfo.class)
	private String title;
	
	private Integer counter = 0;
	
	private int counter1 = 0;
	
	private int counter2 = 0;
	
	
	// bi-directional many-to-one association to Businessentitycontact
	@OneToMany(mappedBy = "person")
	private List<Businessentitycontact> businessentitycontacts;

	// bi-directional many-to-one association to Emailaddress
	@OneToMany(mappedBy = "person")
	private List<Emailaddress> emailaddresses;
	/*
	// bi-directional one-to-one association to Password
	@OneToOne(mappedBy = "person")
	private Password password;
	*/
	// bi-directional one-to-one association to Businessentity
	@OneToOne
	//@NotNull(groups = BasicInfo.class)
	private Businessentity businessentity;
	
	// bi-directional many-to-one association to Personphone
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<Personphone> personphones;

	public Person() {
		
	}

	public Businessentitycontact addBusinessentitycontact(Businessentitycontact businessentitycontact) {
		getBusinessentitycontacts().add(businessentitycontact);
		businessentitycontact.setPerson(this);

		return businessentitycontact;
	}

	public Emailaddress addEmailaddress(Emailaddress emailaddress) {
		getEmailaddresses().add(emailaddress);
		emailaddress.setPerson(this);

		return emailaddress;
	}

	public Personphone addPersonphone(Personphone personphone) {
		getPersonphones().add(personphone);
		personphone.setPerson(this);

		return personphone;
	}

	public String getAdditionalcontactinfo() {
		return this.additionalcontactinfo;
	}

	public Businessentity getBusinessentity() {
		return this.businessentity;
	}

	public List<Businessentitycontact> getBusinessentitycontacts() {
		return this.businessentitycontacts;
	}

	public long getPersonid() {
		return this.personid;
	}

	public String getDemographics() {
		return this.demographics;
	}

	public List<Emailaddress> getEmailaddresses() {
		return this.emailaddresses;
	}

	public Integer getEmailpromotion() {
		return this.emailpromotion;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public String getNamestyle() {
		return this.namestyle;
	}
	/*
	public Password getPassword() {
		return this.password;
	}
	*/
	public List<Personphone> getPersonphones() {
		return this.personphones;
	}

	public String getPersontype() {
		return this.persontype;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public String getTitle() {
		return this.title;
	}

	public Businessentitycontact removeBusinessentitycontact(Businessentitycontact businessentitycontact) {
		getBusinessentitycontacts().remove(businessentitycontact);
		businessentitycontact.setPerson(null);

		return businessentitycontact;
	}

	public Emailaddress removeEmailaddress(Emailaddress emailaddress) {
		getEmailaddresses().remove(emailaddress);
		emailaddress.setPerson(null);

		return emailaddress;
	}

	public Personphone removePersonphone(Personphone personphone) {
		getPersonphones().remove(personphone);
		personphone.setPerson(null);

		return personphone;
	}

	public void setAdditionalcontactinfo(String additionalcontactinfo) {
		this.additionalcontactinfo = additionalcontactinfo;
	}

	public void setBusinessentity(Businessentity businessentity) {
		this.businessentity = businessentity;
	}

	public void setBusinessentitycontacts(List<Businessentitycontact> businessentitycontacts) {
		this.businessentitycontacts = businessentitycontacts;
	}

	public void setPersonid(long businessentityid) {
		this.personid = businessentityid;
	}

	public void setDemographics(String demographics) {
		this.demographics = demographics;
	}

	public void setEmailaddresses(List<Emailaddress> emailaddresses) {
		this.emailaddresses = emailaddresses;
	}

	public void setEmailpromotion(Integer emailpromotion) {
		this.emailpromotion = emailpromotion;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setNamestyle(String namestyle) {
		this.namestyle = namestyle;
	}
	/*
	public void setPassword(Password password) {
		this.password = password;
	}
	*/
	public void setPersonphones(List<Personphone> personphones) {
		this.personphones = personphones;
	}

	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	public void incrementCounter() {
		this.counter = this.counter+1;
	}
	
	public int getCounter1() {
		return counter1;
	}

	public void setCounter1(int counter1) {
		this.counter1 = counter1;
	}

	public int getCounter2() {
		return counter2;
	}

	public void setCounter2(int counter2) {
		this.counter2 = counter2;
	}
	
	public void increment1() {
		this.counter1 = this.counter1+1;
	}
	
	public void increment2() {
		this.counter2 = this.counter2+1;
	}

}