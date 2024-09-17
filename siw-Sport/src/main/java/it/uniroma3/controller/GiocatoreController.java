package it.uniroma3.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.model.Credentials;
import it.uniroma3.model.Giocatore;
import it.uniroma3.model.Presidente;
import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.GiocatoreService;
import it.uniroma3.model.Squadra;

import jakarta.servlet.http.HttpSession;


@Controller
public class GiocatoreController {
	
	@Autowired CredentialsService credentialsService;
	
	@Autowired GiocatoreService giocatoreService;

    @GetMapping("/giocatori")
	public String GetAllGiocatori(Model model) {
		model.addAttribute("Giocatori",this.giocatoreService.GetAllGiocatori());
		return "giocatori.html";
		
	}

	@GetMapping("/giocatore/{id}")
	public String GetGiocatore(@PathVariable("id") Long id, Model model) {
     Giocatore giocatore = giocatoreService.findgiocatoreById(id);
 	model.addAttribute("Giocatore",giocatore);
		return "giocatore.html";
		
	}
	
	@GetMapping("/giocatori/squadra/{id}")
	public String FindRecensioniByUsergId(Model model,@PathVariable("id") Long id){
		model.addAttribute("recensioni",giocatoreService.findgiocatoriBySquadraID(id));
		return "giocatori.html";
	
	}
	
	@GetMapping("/foundGiocatore")
	public String foundGiocatore(Model model) {
				return "formSearchGiocatore.html";
	}
	
	
   @PostMapping("/searchGiocatore")
	public String searchGiocatore(Model model,@RequestParam("nome")String nome) {
		model.addAttribute("giocatore", giocatoreService.findGiocatoriBynome(nome));
		return "squadra.html";
	}

}










//	@GetMapping("/gioco/{id}/recensioni")
//	@Transactional
//	public String getRecensioniPerGioco(@PathVariable("id") Long id, Model model) {
////		model.addAttribute("recensioni", this.giocatoreService.FindRecensioniByGiocoId(id));
//		return "recensioni.html";
//	}
//	
//
//	
//	}
//	
//	@GetMapping("/aggiungiRecensione")
//	@Transactional
//	public String AddRecensione(Model model,@ModelAttribute("presidente") Presidente presidente) {
//		Giocatore r=new Giocatore();
//		model.addAttribute("giocatore",r);
//		model.addAttribute(presidente);
//		return "user/formNewRecensione.html";
//		
//	}
//	
//	@PostMapping("/addRecensione")
//	@Transactional
//		public String newRecensione(
//				@ModelAttribute("recensione")Giocatore giocatore, Model model,HttpSession session,
//			     @RequestParam("rating") Integer rating, @RequestParam("data")Date data) {
//	    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
//			Presidente presidente = credentials.getPresidente();
//		    Squadra squadra= (Squadra)session.getAttribute("giocoMem");
//			if (rating == null) {//da finire errore
//		        // Aggiungi un messaggio di errore al modello
//		        model.addAttribute("error", "Devi selezionare un punteggio per la recensione.");
//		        // Ritorna alla pagina di inserimento recensione
//		        return "recensioneForm"; // Assicurati che questo sia il nome corretto del tuo template di inserimento
//		    }
////			recensione.setGioco(gioco);
////			recensione.setNumeroStelle(rating);
////			recensione.setData(data);
////			recensione.setUser(user);
////			
//		    this.giocatoreService.save(giocatore);
//		    //user.getRecensioni().add(recensione);
////			model.addAttribute("recensioni", this.giocatoreService.FindRecensioniByUserId(user.getId()));
////			return "redirect:/recensioni";
//			return "redirect:/gioco/"+squadra.getId()+"/recensioni";
//		}

//	@GetMapping("/User/CancellaRecensione/{id}")
//	@Transactional
//    public String cancellaRecensione(Model model,@PathVariable("id") Long id) {  
//		giocatoreService.deleteById(id);
//        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
//		Presidente user = credentials.getPresidente();
////	   	model.addAttribute("recensioni", giocatoreService.FindRecensioniByUserId(user.getId()));
//		return "user/DeleteRecensioni.html";
//    }