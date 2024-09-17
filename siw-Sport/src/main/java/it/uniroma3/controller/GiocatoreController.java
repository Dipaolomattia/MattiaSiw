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
		model.addAttribute("Giocatori",giocatoreService.findgiocatoriBySquadraID(id));
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
