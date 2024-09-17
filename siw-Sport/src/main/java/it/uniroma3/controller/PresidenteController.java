package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.security.Principal;

import it.uniroma3.controller.validator.CredentialsValidator;
import it.uniroma3.controller.validator.PresidenteValidator;
import it.uniroma3.model.*;
import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.GiocatoreService;
import it.uniroma3.service.PresidenteService;
import it.uniroma3.service.SquadraService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller

public class PresidenteController {
	@Autowired PresidenteService userService;
	@Autowired GiocatoreService giocatoreService;
	@Autowired SquadraService squadraService;
	@Autowired CredentialsService credentialsService;
	@Autowired PresidenteService presidenteService;
	@Autowired
    private CredentialsValidator validator;
    @Autowired
    private PresidenteValidator presidentevalidator;
    
	
	 @GetMapping("/Utente/{id}")
		public String getUtente(@PathVariable("id")Long id ,Model model) {
			Presidente utente= this.userService.findById(id);
		    model.addAttribute("Utente",utente);
			return "utente.html";
		}
	 
	 @GetMapping("/Utenti")
	 public String getUtenti(Model model) {
		 model.addAttribute("utenti", this.userService.findAll());
		 return "utenti.html";
	 }
	 @GetMapping("/UtentiSenzaSquadra")
	 public String getUtentiSenzaSquadra(Model model) {
		 model.addAttribute("utentiS", this.userService.FindPresidentiSenzaSquad());
		 return "utenti.html";
	 }
	 
	 @GetMapping("/aggiungiGiocatore")
		public String AddGiocatore(Model model) {
			Giocatore nuovogiocatore= new Giocatore();
		    model.addAttribute("Giocatore",nuovogiocatore);
			return "formNewGiocatore.html";
		}
	 
	 
		@PostMapping("/addGiocatore")
		public String NewGiocatore(
			@ModelAttribute("Giocatore")Giocatore giocatore,Model model) {
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
	        Presidente p= presidenteService.getPresidenteByuser(credentials.getUsername());
			giocatore.setSquadra(p.getSquadra());
	        giocatoreService.save(giocatore);

			return "redirect:/giocatori";
		}
		
		@GetMapping("/DeleteGiocatorePresidente/{id}")
	    @Transactional
		public String findGiocatoriPresidente(Model model,@PathVariable("id")Long id) {
			model.addAttribute("DeleteGiocatori",giocatoreService.findgiocatoriByPresId(id));
				return "DeleteGiocatori.html";
			}
		
		  @GetMapping("/presidente/CancellaGiocatore{id}")
		    public String cancellaGiocatore(@PathVariable("id")Long Id) {  
			  giocatoreService.deleteById(Id);
		    	return "redirect:/giocatori";
		    }
		  
		  @GetMapping("/GiocatoriSvincolati")
		    @Transactional
			public String findGiocatoriSvincolati(Model model) {
                    model.addAttribute("Svincolati",giocatoreService.findgiocatoriSvincolati());
					return "GiocatoriSvincolati.html";
				}
		  @GetMapping("/presidente/AggiungiGiocatore{id}")
		    public String AggiungiGiocatoreSv(@PathVariable("id")Long Id) {  
			  Giocatore giocatore=giocatoreService.findgiocatoreById(Id);
			  giocatoreService.deleteById(Id); 
			  UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		      Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		      Presidente p= presidenteService.getPresidenteByuser(credentials.getUsername());
		      giocatore.setSquadra(p.getSquadra());
		      giocatoreService.save(giocatore);
		      
		      return "redirect:/GiocatoriSvincolati";
		    }
		  
		  @PostMapping(value = { "/register" })
		    public String registerUser(@Valid@ModelAttribute("presidente") Presidente presidente,
		                 BindingResult userBindingResult, @Valid @ModelAttribute("credentials") Credentials credentials,
		                 BindingResult credentialsBindingResult,
		                 Model model ){
				 presidente.setCredentials(credentials);
				 validator.validate(presidente.getCredentials(), credentialsBindingResult);
				 presidentevalidator.validate(presidente, credentialsBindingResult);
		        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
		        	    credentials.setPresidente(presidente);
		        		presidente.setCredentials(credentials);
		        		userService.saveUser(presidente);
		        	    model.addAttribute("presidente", presidente);
		        		
		        	
		            credentialsService.saveCredentials(credentials);
		         
		            return "registrationSuccessful";
		        }
		        
		        return "formRegisterPresidente";
		    }
		   
		  
		   
		  
		
	
	 
}



