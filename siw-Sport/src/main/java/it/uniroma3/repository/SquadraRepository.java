package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Squadra;


public interface SquadraRepository extends CrudRepository<Squadra, Long> {
	
	
	
	@Query(value = "SELECT s FROM squadra s join presidente pr on s.id=pr.squadra_id where pr.id= idUser:", nativeQuery = true)
	public Squadra FindSquadraByPresidenteId(@Param("idUser") Long id);

	public Squadra findByNome(String nome);

		
		
}



//	 public List<Squadra> findByYear(Integer year);
//
//	    public boolean existsByTitleAndYear(String title, Integer year);
//
////	    @Query(value = "SELECT * FROM gioco r WHERE r.userg_id = :idUser", nativeQuery = true)
////	    Iterable<Squadra> FindGiocoByUserId(@Param("idUser") Long id);
////		
//		
	
//		
//		public List<Squadra> findByTitleIgnoreCase(String title);