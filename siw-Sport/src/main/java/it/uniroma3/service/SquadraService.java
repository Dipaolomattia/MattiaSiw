package it.uniroma3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Squadra;
import it.uniroma3.repository.SquadraRepository;

@Service
public class SquadraService {

	@Autowired		
	private SquadraRepository squadraRepository;
	
//	@Autowired 
//	private ImageRepository imageRepository;
	
	public Squadra findById(Long id) {
		return squadraRepository.findById(id).get();
	}

	public Iterable<Squadra> findAll() {
		return squadraRepository.findAll();
	}
	
	public Squadra save(Squadra m) {
		return squadraRepository.save(m);
	}
//	
//	public List<Squadra> searchByYear(Integer year) {
////		return squadraRepository.findByYear(year);
//		return null;
//		
//	}
//	public Iterable<Squadra>findByUserId(Long id){
//		return squadraRepository.FindGiocoByUserId(id);
//	}
	public Squadra FindSquadraByPresidenteId( Long id) {
		return squadraRepository.FindSquadraByPresidenteId(id);
	}

	public void deleteById(Long id) {
		squadraRepository.deleteById(id);
	}
	public Squadra FindSquadrabyNome(String Nome) {
		return squadraRepository.findByNome(Nome);
	}
	
}
