package it.uniroma3.controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.repository.SquadraRepository;

import java.util.*;

import it.uniroma3.model.Credentials;

import it.uniroma3.model.Presidente;
import it.uniroma3.model.Squadra;

import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.GiocatoreService;
import it.uniroma3.service.PresidenteService;
import it.uniroma3.service.SquadraService;
import jakarta.servlet.http.HttpSession;

@Controller
public class SquadraController {
	
	@Autowired SquadraService squadraService;

	@Autowired SquadraRepository squadraRepository;

	@Autowired GiocatoreService giocatoreservice;
	@Autowired PresidenteService presidenteService;
	
	@Autowired CredentialsService credentialsService;

//	@Autowired RecensioneService recensioneService;
//
//	
//	@Autowired ImageService imageservice;
	//,HttpSession session
	

	
	@GetMapping("/squadra/{id}")
	public String getsquadra(@PathVariable("id") Long id, Model model) {
		Squadra squadra = this.squadraService.findById(id);
		
		model.addAttribute("squadra", squadra);
		model.addAttribute("Giocatori",this.giocatoreservice.findgiocatoriBySquadraID(id));
		
		return "squadra.html";
	}
	
	
//	/*vedere le recensioni del gioco meglio ancora non usato*/
//	@GetMapping("/giocoStile/{id}")
//	public String getGiocoStile(@PathVariable("id") Long id, Model model,HttpSession session) {
//		Squadra gioco = this.squadraService.findById(id);
//		model.addAttribute("gioco", gioco);
//		model.addAttribute("recensioni", recensioneService.FindRecensioniByGiocoId(id));
//		return "giocoStile.html";
//	}
//	
	
	
	@GetMapping("/Squadre")
	public String showSquadre(Model model) {
		model.addAttribute("squadre", this.squadraService.findAll());
		return "squadre.html"; //da chi viene generata la risposta
	}

	@GetMapping("/foundSquadra")
	public String foundSquadra(Model model) {
				return "formSearchSquadra.html";
	}
	
	
   @PostMapping("/searchSquadra")
	public String searchSquadra(Model model,@RequestParam("nome")String nome) {
		model.addAttribute("squadra",squadraService.FindSquadrabyNome(nome));
		return "squadra.html";
	}

	@GetMapping("/gioco/user/{id}")
	public String GetgiocoByUsergId(Model model,@PathVariable("id") Long id){
//		model.addAttribute("giochi",squadraService.findByUserId(id));
		return "squadre.html";
	}
	

	
	
	
}
