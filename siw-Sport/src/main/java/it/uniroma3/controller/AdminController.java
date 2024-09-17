package it.uniroma3.controller;

import java.io.IOException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




import it.uniroma3.controller.validator.CredentialsValidator;
import it.uniroma3.service.AdminService;
import it.uniroma3.service.CredentialsService;

import it.uniroma3.service.PresidenteService;

import it.uniroma3.service.SquadraService;
import it.uniroma3.controller.validator.*;
import it.uniroma3.model.Admin;
import it.uniroma3.model.Credentials;
import it.uniroma3.model.Presidente;
import it.uniroma3.model.Squadra;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {
	
	@Autowired
	 SquadraService squadraService;
	
     @Autowired
	 PresidenteService presidenteService;
	
	@Autowired
	CredentialsService credentialsService;
	@Autowired
    private AdminService adminService;
    @Autowired
    private CredentialsValidator validator;
	
	

	    /*get alla pagina per cancellare*/
	    @GetMapping("/DeleteSquadreAdmin")
	    @Transactional
		public String findAllrecensioniAdmin(Model model) {
		         model.addAttribute("DeleteSquadre", squadraService.findAll());
				return "DeleteSquadre.html";
			}
	    
	/*cancella recensioni dal sistema */
	    @GetMapping("/admin/CancellaSquadra{id}")
	    public String cancellaSquadraAdmin(@PathVariable("id")Long Id) {  
	    	squadraService.deleteById(Id);
	    	return "redirect:/Squadre";
	    	
	    }
	    
	    @GetMapping("/aggiungiSquadra/{id}")
		public String AddGioco(Model model,@PathVariable("id") Long id) {
			Squadra nuovasquadra= new Squadra();
			Presidente nuovopresidente= presidenteService.findById(id);
		    model.addAttribute("Presidente",nuovopresidente);
			model.addAttribute("squadra",nuovasquadra);
			return "formNewSquadra.html";
		}
		
		@PostMapping("/addSquadra")
		public String NewSquadra(
			@ModelAttribute("squadra")Squadra squadra,@RequestParam("presidenteId")Long id,Model model) {
	        Presidente pres= presidenteService.findById(id);
	           pres.setSquadra(squadra);
		      this.squadraService.save(squadra);
			model.addAttribute("squadre", this.squadraService.findAll());
			return "redirect:/Squadre";
		}
		@PostMapping(value = { "/registerAdmin"} )

		public String registerAdmin(@Valid @ModelAttribute("Admin") Admin admin, BindingResult userBindingResult, 
				BindingResult credentialsBindingResult,Model model,@Valid @ModelAttribute("credentials") Credentials credentials) {
			admin.setCredentials(credentials);
			 validator.validate(admin.getCredentials(), credentialsBindingResult);
			
			  if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
				  admin.setCredentials(credentials);
				    credentialsService.saveCredentialsAdmin(credentials);
	        	    adminService.saveUser(admin);
	        	   
	        	 return "registrationSuccessfulAdmin";
	        	}
			  
			  return "formRegisterAdmin";
		}
		

}
