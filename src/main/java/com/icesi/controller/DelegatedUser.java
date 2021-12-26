package com.icesi.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.icesi.model.Addresstype;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentityaddress;
import com.icesi.model.Personphone;
import com.icesi.model.Phonenumbertype;

@Component
public class DelegatedUser {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//BUSINESS ENTITY ADDRESS
	//-------------------------------------------------------------------------------------------
	
	public Businessentityaddress getEntity(Integer id){
		String url = "http://localhost:8080/baddresses/"+id;
		Businessentityaddress bea = restTemplate.getForObject(url, Businessentityaddress.class);
		return bea;
	}
	
	public Iterable<Businessentityaddress> getAllEntities(){
		String url = "http://localhost:8080/baddresses";
		Businessentityaddress[] bea = restTemplate.getForObject(url, Businessentityaddress[].class);
		return Arrays.asList(bea);
	}
	
	public String createEntityAddress(Businessentityaddress bea) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Businessentityaddress> entity = new HttpEntity<Businessentityaddress>(bea,headers);
	      
	    return restTemplate.exchange(
	         "http://localhost:8080/baddresses", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateEntity(Integer id, @Validated(BasicInfo.class) Businessentityaddress bea) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Businessentityaddress> entity = new HttpEntity<Businessentityaddress>(bea,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/baddresses/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//AUX TYPES
	//-------------------------------------------------------------------------------------------
	
	
	public Iterable<Phonenumbertype> getAllPhoneTypes(){
		String url = "http://localhost:8080/phonetypes";
		Phonenumbertype[] pnt = restTemplate.getForObject(url, Phonenumbertype[].class);
		return Arrays.asList(pnt);
	}
	
	public Iterable<Addresstype> getAllAddressTypes(){
		String url = "http://localhost:8080/addresstypes";
		Addresstype[] adt = restTemplate.getForObject(url, Addresstype[].class);
		return Arrays.asList(adt);
	}
	
	
	//PERSON PHONE
	//-------------------------------------------------------------------------------------------
	
	public Personphone getPhone(Integer id){
		String url = "http://localhost:8080/phones/"+id;
		Personphone pp = restTemplate.getForObject(url, Personphone.class);
		return pp;
	}
	
	public Iterable<Personphone> getAllPhones(){
		String url = "http://localhost:8080/phones";
		Personphone[] pp = restTemplate.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}
	
	public Iterable<Personphone> getAllPhonesByPref(String pref){
		String url = "http://localhost:8080/phones/pref="+pref;
		Personphone[] pp = restTemplate.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}
	
	public Iterable<Personphone> getAllPhonesByTypes(){
		String url = "http://localhost:8080/phones/list";
		Personphone[] pp = restTemplate.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}
	
	public String createPhone(Personphone pp) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Personphone> entity = new HttpEntity<Personphone>(pp,headers);
	      
	    return restTemplate.exchange(
	         "http://localhost:8080/phones", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public Iterable<Personphone> getAllPhonesByTypes(String type){
		String url = "http://localhost:8080/phones/type="+type;
		Personphone[] pp = restTemplate.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}
	
	public String updatePhone(Integer id, @Validated(BasicInfo.class) Personphone pp) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Personphone> entity = new HttpEntity<Personphone>(pp,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/phones/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
}
