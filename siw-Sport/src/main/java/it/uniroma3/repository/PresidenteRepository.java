package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Presidente;


public interface PresidenteRepository extends CrudRepository<Presidente, Long> {

	  public Presidente findUserById(Long Id);
	  public Boolean existsByNomeAndCognome(String nome, String cognome);
	  
      @Query(value = " Select * from presidente where squadra_id isnull ", nativeQuery = true)
      public Iterable<Presidente> FindPresidentiSenzaSquadra();
      
      
      @Query(value = " SELECT pr.id,pr.data_nascita,pr.luogo,pr.nome,pr.cognome,pr.codice_fiscale,pr.squadra_id,pr.credentials_id FROM presidente pr join credentials cr on pr.credentials_id=cr.id where cr.username=:UsernameUser", nativeQuery = true)
      public Presidente FindPresidenteSn(@Param("UsernameUser") String username);
      
}
