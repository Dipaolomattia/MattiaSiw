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
	
	
//	
//	@Autowired 
//	private CredentialsValidator credentialsValidator;
//	
	
	
//	@GetMapping(value = "/registerAdmin") 
//	public String showRegisterFormAdmin (Model model) {
//		model.addAttribute("user", new User());
//		model.addAttribute("credentials", new Credentials());
//		model.addAttribute("role",Credentials.ADMIN_ROLE);
//		return "registerAdmin.html";
//	}
	
	
//	@PostMapping(value = { "/registerAdmin" })
//    public String registerAdmin(
//    						    @ModelAttribute("user") User user, 
//                 BindingResult userBindingResult, 
//                 @ModelAttribute("credentials") Credentials credentials,
//                 BindingResult credentialsBindingResult,
//                 Model model) {
//    	
//		this.credentialsValidator.validate(credentials, credentialsBindingResult);
//		this.userValidator.validate(user, userBindingResult);
//		
//		// se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
//        if(!credentialsBindingResult.hasErrors() && !userBindingResult.hasErrors()) {
//        	userService.saveUser(user);
//        	credentials.setUser(user);
//        	credentialsService.saveCredentialsAdmin(credentials);
//            model.addAttribute("user", user);           
//            System.out.println("L'utente registrato e': "+credentials.getRole());
//            
//            return "formLogin.html";
//        }
//        return "registerAdmin.html";
//    }
	
	/*Aggiungi gioco da Admin*/
//	@GetMapping("/NewGiocoAdmin")
//    public String addGiochiAdmin( @ModelAttribute("gioco") Gioco gioco, 
//    		Model model) {    	
//    	model.addAttribute("gioco", new Gioco());
//    	return "admin/formNewGiocoAdmin.html";
//    }
	
//	@PostMapping("/NewgiochiAdmin")
//	public String newGiocoAdmin(
//			@ModelAttribute("gioco")Gioco gioco, Model model,@RequestParam("image") MultipartFile imageFile)throws IOException  {
//		
//		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
//		User user = credentials.getUser();
//	    gioco.setUserg(user);
//	    gioco.getUserg().setId(user.getId());
//		user.getGiochi().add(gioco);
//		Image ImmagineSalvata= imageservice.saveImage(imageFile);
//	//	ImmagineSalvata.setGioco(gioco);
//		
//		gioco.setImmagine(ImmagineSalvata);
//		giocoService.save(gioco);
//		
//		model.addAttribute("giochi", this.giocoService.findAll());
////		return "giochi.html";
//		return "redirect:giochi";
//		//return "redirect:Ricetta/"+ricetta.getId();
//	}
//	
	/*Elenco degli utenti che puoi eliminare uno o aggiungere*/
//    @GetMapping("/UtentiAdmin")
//	public String getUtentiAdmin(Model model) {
//    		model.addAttribute("credentials",new Credentials());
//			model.addAttribute("utenti", userService.findAll());
//			return "admin/utentiAdmin.html";
//		}
	
//	
//    /*Form per aggiungere uno user*/
//    @GetMapping(value = "/formAddUtente") 
//	public String addUtente(Model model) {
//		model.addAttribute("user", new User());
//		model.addAttribute("credentials", new Credentials());
//		return "admin/formAddUtente.html";
//}
    	    
//    @PostMapping(value = { "/addUtente" })
//    public String addNewUtente(@ModelAttribute("user") User user,
//                 BindingResult userBindingResult,
//                 @ModelAttribute("credentials") Credentials credentials,
//                 BindingResult credentialsBindingResult,
//                 Model model) {
//		
//		this.credentialsValidator.validate(credentials, credentialsBindingResult);
//		this.userValidator.validate(user, userBindingResult);
//		
//		// se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
//        if(!credentialsBindingResult.hasErrors() && !userBindingResult.hasErrors()) {
//            userService.saveUser(user);
//            
//            credentials.setUser(user);
//            credentialsService.saveCredentials(credentials);
//            model.addAttribute("user", user);
//                     	                   
//            return "redirect:/UtentiAdmin";
//        }
//        return "admin/formAddUtente.html";
//    }
//    
//    /*Tasto per eliminare un Utente*/
//    @GetMapping("/CancellaUtente/{idUs}")
//    public String cancellaCuoco(@ModelAttribute("utente")User utente,
//    	@PathVariable("idUs") Long idUs, Model model) {  
//    	userService.deleteById(idUs);
//    	credentialsService.deleteById(idUs);
//    	return "redirect:/Ricette";
//    	return "redirect:/UtentiAdmin";
//    }
    
//	/*Elenco giochi che l'admin può  modificare */
//    @GetMapping("/giochiAdmin")
//    public String GetAllGiochiAdmin(Model model) {
//    	model.addAttribute("giochi",this.giocoService.findAll());
//    		return "admin/giochiAdmin.html";
//    	}
//	
    
//    /*Cancella gioco dall'elenco dell'admin*/
//	 @GetMapping("admin/CancellaGiochiAdmin/{id}")
//	    public String cancellaGioco(@ModelAttribute("gioco")Gioco gioco,
//	    	@PathVariable("id") Long id, Model model) {  
//		 	giocoService.deleteById(id); 
//		 	
//	    	return "redirect:/giochiAdmin";
//	    }   
//    
    
//    /*Form per aggiungere recensioni da Admin in qualsiasi gioco*/
//	 @GetMapping("admin/NewRecensioniAdmin/{id}")
//	 @Transactional
//	    public String addRecensioniAdmin(@ModelAttribute("gioco")Gioco gioco,@ModelAttribute("user") User user,
//	    		@PathVariable("id") Long id, Model model) {    	
//	    	    model.addAttribute("recensione", new Recensione());
////	    	    model.addAttribute("gioco", giocoService.findById(id));
////	    	    model.addAttribute("id",id);
//	    	    model.addAttribute(user);
//	    	return "admin/formNewRecensioniAdmin.html";
//	    }
//	    
//	    @PostMapping("/recensioniNuoveAdmin")
//	    @Transactional
//		public String newRecensioniAdmin(@ModelAttribute("recensione")Recensione recensione,
//				@RequestParam("id")Long id, @RequestParam("rating") Integer rating, @RequestParam("data")Date data,
//				/*@ModelAttribute("gioco")Gioco gioco,*/ Model model) {
//	    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());	    	
//			Gioco giocos = giocoService.findById(id);
//			User user = credentials.getUser();
//			
////			Gioco giocos= (Gioco)session.getAttribute("giocoMem");
//	    	recensione.setId(null);
//			System.out.println("l'ID della recensione è "+recensione.getId());
//			
//	    	recensione.setGioco(giocos);
//	    	recensione.setNumeroStelle(rating);
//			recensione.setData(data);
//			recensione.setUser(user);
//			
//			this.recensioneService.save(recensione);
//			giocos.getRecensioni().add(recensione);
//			user.getRecensioni().add(recensione);
//			giocoService.save(giocos);
////			giocos.getRecensioni().add(recensione);
//			
////			giocoService.save(giocos);
//			
////			giocos.getRecensioni().add(recensione);
////			giocoService.save(giocos);
//	    	
//		    
////		    model.addAttribute("recensioni", recensioneService.TrovaRecensioniId(id));
//			model.addAttribute("recensioni", this.recensioneService.FindRecensioniByUserId(user.getId()));
//
////		    return "redirect:/gioco/" + gioco.getId()+"/recensioni";
////		    return "recensioni.html";
//			return "redirect:/gioco/"+giocos.getId()+"/recensioni";
//		}
//	    
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
