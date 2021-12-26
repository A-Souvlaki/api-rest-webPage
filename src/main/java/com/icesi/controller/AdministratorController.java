package com.icesi.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icesi.model.Address;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentity;
import com.icesi.model.Customer;
import com.icesi.model.Person;
import com.icesi.model.Stateprovince;
import com.icesi.model.Store;

@Controller
public class AdministratorController {
		
	DelegatedAdmin da;

	@Autowired
	public AdministratorController(DelegatedAdmin da) {
		this.da = da;
	}
	
	@GetMapping("/admin/persons/")
    public String indexPerson(Model model) {
		model.addAttribute("persons", da.getAllPersons());
        return "admin/indexPerson";
    }
		
	@GetMapping("/admin/persons/add")
	public String addPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("businessentities", da.getAllEntities());
		return "admin/addPerson";
	}
	
	@PostMapping("/admin/persons/add")
	public String savePerson(@Validated(BasicInfo.class) Person person, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", da.getAllEntities());
			return "admin/addPerson";
		}
		if (!action.equals("Cancelar")) {
			
			da.createPerson(person);
		}
		return "redirect:/admin/persons/";
	}
	
	@GetMapping("/admin/persons/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id,Model model) {
		Person person = da.getPerson(id);
		if (person == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("person", person);
		model.addAttribute("businessentities",da.getAllEntities());
		return "admin/updatePerson";
	}
	
	
	@PostMapping("/admin/persons/edit/{id}")
	public String updatePerson(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Person person, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("persons", da.getAllPersons());
			
			return "admin/indexPerson";
		}
		if (action != null && !action.equals("Cancel")) {
			person.setPersonid(id);
			da.updatePerson(id, person);
			model.addAttribute("persons", da.getAllPersons());
		}
		return "redirect:/admin/persons/";
	}
	
	//-------------------------------------------------
	
	@GetMapping("/admin/address/")
    public String indexState(Model model) {
		model.addAttribute("stateprovinces", da.getAllStates());
        return "admin/indexState";
    }
	
	@GetMapping("/admin/address/addState")
	public String addState(Model model) {
		model.addAttribute("stateprovince", new Stateprovince());
		return "admin/addState";
	}
	
	@PostMapping("/admin/address/addState")
	public String saveState(@Validated(BasicInfo.class) Stateprovince sp, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovinces", da.getAllStates());
			return "admin/addState";
		}
		if (!action.equals("Cancelar")) {
			da.createState(sp);
		}	
		return "redirect:/admin/address/";
	}
	
	@GetMapping("/admin/address/editState/{id}")
	public String showUpdateForm2(@PathVariable("id") Integer id, Model model) {
		Stateprovince sp = da.getState(id);
		if (sp == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("stateprovince", sp);
		return "admin/updateState";
	}
	
	@PostMapping("/admin/address/editState/{id}")
	public String updateState(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Stateprovince sp, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stateprovinces", da.getAllStates());
			
			return "admin/indexState";
		}
		if (action != null && !action.equals("Cancel")) {
			da.updateState(id, sp);
			model.addAttribute("stateprovinces", da.getAllStates());
		}
		return "redirect:/admin/address/";
	}
	
	//-------------------------------------------------
	
	
	@GetMapping("/admin/states/")
    public String indexAddress(Model model) {
		model.addAttribute("addresses", da.getAllAddresses());
        return "admin/indexAddress";
    }
		
	@GetMapping("/admin/states/addAddress")
	public String addAddress(Model model) {
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", da.getAllStates());
		return "admin/addAddress";
	}
	
	@PostMapping("/admin/states/addAddress")
	public String saveAddress(@Validated(BasicInfo.class) Address address, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", da.getAllAddresses());
			return "admin/addAddress";
		}
		if (!action.equals("Cancelar")) {
			da.createAddresses(address);
		}	
		return "redirect:/admin/states/";
	}
	
	@GetMapping("/admin/states/editAddress/{id}")
	public String showUpdateForm3(@PathVariable("id") Integer id, Model model) {
		Address ad = da.getAddress(id);
		model.addAttribute("address", ad);
		model.addAttribute("stateprovinces", da.getAllStates());
		return "admin/updateAddress";
	}
	
	@PostMapping("/admin/states/editAddress/{id}")
	public String updateAddress(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Address ad, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", da.getAllAddresses());
			
			return "admin/indexAddress";
		}
		if (action != null && !action.equals("Cancel")) {
			ad.setAddressid(id);
			da.updateAddresses(id, ad);
			model.addAttribute("addresses", da.getAllAddresses());
		}
		return "redirect:/admin/states/";
	}
	
	
	//-------------------------------------------------
	
	@GetMapping("/admin/entity/")
    public String indexEntity(Model model) {
		model.addAttribute("businessentities", da.getAllEntities());
        return "admin/indexEntity";
    }
		
	@GetMapping("/admin/entity/addEntity")
	public String addEntity(Model model) {
		model.addAttribute("businessentity", new Businessentity());
		return "admin/addEntity";
	}
	
	
	@PostMapping("/admin/entity/addEntity")
	public String saveEntity(@Validated(BasicInfo.class) Businessentity be, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", da.getAllEntities());
			return "admin/addEntity";
		}
		if (!action.equals("Cancelar")) {
			da.createEntity(be);
		}	
		return "redirect:/admin/entity/";
	}
	
	@GetMapping("/admin/entity/editEntity/{id}")
	public String showUpdateForm4(@PathVariable("id") Integer id, Model model) {
		
		if (Optional.of(da.getEntity(id)).isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("businessentity", da.getEntity(id));
		return "admin/updateEntity";
	}
	
	@PostMapping("/admin/entity/editEntity/{id}")
	public String updateEntity(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Businessentity be, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
		
			
			return "admin/updateEntity";
		}
		if (action != null && !action.equals("Cancel")) {
			da.updateEntity(id, be);
			model.addAttribute("businessentities", da.getAllEntities());
		}
		return "redirect:/admin/entity/";
	}
	
	
	//----------------------------------------------------
	
	@GetMapping("/admin/entity/persons/{id}")
    public String indexPerson(@PathVariable("id") long id,Model model) {
		model.addAttribute("persons", da.getAllPersonsByID(id));
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/address/addresses/{id}")
    public String indexAddress(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("addresses", da.getAllAddressesByID(id));
        return "admin/indexAddress";
    }
	
	@GetMapping("/admin/store/customers/{id}")
    public String indexCustomer(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("customers", da.getAllCustomersByID(id));
        return "admin/indexCustomer";
    }
	
	@GetMapping("/admin/persons/customers/{id}")
    public String indexCustomerP(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("customers", da.getAllCustomersByPerson(id));
        return "admin/indexCustomer";
    }
	
	//------------------------------------------------------
	
	
	@GetMapping("/admin/searches/")
    public String searches() {
        return "admin/indexSearch";
    }
	
	@GetMapping("/admin/searches/perid")
    public String searches1() {
        return "search/searchPerid";
    }
	
	@PostMapping("/admin/searches/perid")
    public String searches1p(@RequestParam("search") long id, Model model) {
		
		model.addAttribute("persons", da.getPerson(id));
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/searches/pertit")
    public String searches2() {
        return "search/searchPertit";
    }
	
	@PostMapping("/admin/searches/pertit")
    public String searches2p(@RequestParam("search") String title, Model model) {
		
		model.addAttribute("persons", da.getAllPersonsByTitle(title));
        return "admin/indexPerson";
    }
	
	
	@GetMapping("/admin/searches/staid")
    public String searches3(Model model) {
		
        return "search/searchStaid";
    }
	
	@PostMapping("/admin/searches/staid")
    public String searches3p(@RequestParam("search") Integer id, Model model) {
		
		model.addAttribute("addresses", da.getAddress(id));
        return "admin/indexAddress";
    }
	
	@GetMapping("/admin/searches/stadate")
    public String searches4(Model model) {
		
        return "search/searchStadate";
    }
	
	@PostMapping("/admin/searches/stadate")
    public String searches4p(@RequestParam("search") String date, Model model) {
		model.addAttribute("addresses", da.getAllAddressesByDate(date));
        return "admin/indexAddress";
    }
	
	@GetMapping("/admin/searches/perinter")
    public String searches5(Model model) {
		
        return "search/searchInter";
    }
	
	@PostMapping("/admin/searches/perinter")
    public String searches5p(@RequestParam("search") String date1,@RequestParam("search1") String date2, Model model) {
		model.addAttribute("persons", da.getAllPersonsByInterval(date1, date2));
        return "admin/indexPerson";
    }
    
	
	//------------------------------------------------------
	
	@GetMapping("/admin/store/")
    public String indexStore(Model model) {
		model.addAttribute("stores", da.getAllStores());
        return "admin/indexStore";
    }
	
	@GetMapping("/admin/store/addStore")
	public String addStore(Model model) {
		model.addAttribute("store", new Store());
		return "admin/addStore";
	}
	
	@GetMapping("/admin/store/delStore/{id}")
	public String delStore(@PathVariable("id") Integer id, Model model) {
		da.deleteStore(id);
		model.addAttribute("stores", da.getAllStores());
		return "admin/indexStore";
	}
	
	
	@PostMapping("/admin/store/addStore")
	public String saveStore(@Validated(BasicInfo.class) Store st, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stores", da.getAllStores());
			return "admin/indexStore";
		}
		if (!action.equals("Cancelar")) {
			da.createStore(st);
		}	
		return "redirect:/admin/store/";
	}
	
	@GetMapping("/admin/store/editStore/{id}")
	public String showUpdateForm5(@PathVariable("id") Integer id, Model model) {
		
		if (Optional.of(da.getStore(id)).isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		
		model.addAttribute("store", da.getStore(id));
		return "admin/updateStore";
	}
	
	@PostMapping("/admin/store/editStore/{id}")
	public String updateStore(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Store st, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
		
			
			return "admin/updateStore";
		}
		if (action != null && !action.equals("Cancel")) {
			da.updateStore(id, st);
			model.addAttribute("stores", da.getAllStores());
		}
		return "redirect:/admin/store/";
	}
	
	//------------------------------------------------------
	
	@GetMapping("/admin/customer/")
    public String indexCustomer(Model model) {
		model.addAttribute("customers", da.getAllCustomers());
        return "admin/indexCustomer";
    }
	
	@GetMapping("/admin/customer/delCustomer/{id}")
	public String delCustomer(@PathVariable("id") Integer id, Model model) {
		da.deleteCustomer(id);
		model.addAttribute("customers", da.getAllCustomers());
		return "admin/indexCustomer";
	}
	
	@GetMapping("/admin/customer/addCustomer")
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("stores", da.getAllStores());
		model.addAttribute("persons", da.getAllPersons());
		return "admin/addCustomer";
	}
	
	@PostMapping("/admin/customer/addCustomer")
	public String saveCustomer(@Validated(BasicInfo.class) Customer ct, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("stores", da.getAllStores());
			model.addAttribute("persons", da.getAllPersons());
			return "admin/addCustomer";
		}
		if (!action.equals("Cancelar")) {
			da.createCustomer(ct);
		}	
		return "redirect:/admin/customer/";
	}
	
	@GetMapping("/admin/customer/editCustomer/{id}")
	public String showUpdateForm6(@PathVariable("id") Integer id, Model model) {
		Customer ct = da.getCustomer(id);
		model.addAttribute("customer", ct);
		model.addAttribute("stores", da.getAllStores());
		model.addAttribute("persons", da.getAllPersons());
		return "admin/updateCustomer";
	}
	
	@PostMapping("/admin/customer/editCustomer/{id}")
	public String updateCustomer(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) Customer ct, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("customers", da.getAllCustomers());		
			return "admin/indexCustomer";
		}
		if (action != null && !action.equals("Cancel")) {
			ct.setCustomerid(id);
			da.updateCustomer(id, ct);
			model.addAttribute("customers", da.getAllCustomers());
		}
		return "redirect:/admin/customer/";
	}
}
