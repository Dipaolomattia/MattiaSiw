package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.uniroma3.model.Giocatore;
import java.util.*;
import it.uniroma3.repository.GiocatoreRepository;

@Service

public class GiocatoreService {

	@Autowired GiocatoreRepository giocatorerepository;



	public Giocatore findgiocatoreById(Long id) {
    	return giocatorerepository.findGiocatoreById(id);
	}

	public void save(Giocatore recensione) {
		giocatorerepository.save(recensione);
	}
	public Iterable<Giocatore>GetAllGiocatori(){
		return giocatorerepository.findAll();
	}
	public List<Giocatore>findgiocatoriByPresId(Long id) {
		return giocatorerepository.findGiocatoriByPresidenteid(id);
	}
	public List<Giocatore>findgiocatoriSvincolati() {
		return giocatorerepository.findGiocatoriSvincolati();
	}
	public List<Giocatore>findgiocatoriBySquadraID(Long id) {
		return giocatorerepository.findGiocatoriBySquadraid(id);
	}
	
	public List<Giocatore>findGiocatoriBynome(String nome){
		return giocatorerepository.findGiocatoreByNome(nome);
	}
	
	
	


 public void deleteById(Long id) {
	 giocatorerepository.deleteById(id);

 }
		

}
