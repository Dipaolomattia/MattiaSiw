package it.uniroma3.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Admin;
import it.uniroma3.model.Presidente;


public interface AdminRepository extends CrudRepository<Admin, Long> {

	  public Presidente findUserById(Long Id);
	   
	   public Boolean existsByNomeAndCognome(String nome, String cognome);
	  
      @Query(value = "SELECT data_nascita,userid as id,codice_fiscale,cognome,nomeuser as nome,luogo FROM (SELECT *,u,u.id as userid,u.nome as nomeuser FROM users u join credentials cr on u.id=cr.id where cr.role='DEFAULT') LEFT JOIN squadra s on userid=s.user_id where s.user_id isnull\r\n"
      		+ " ", nativeQuery = true)
      public Iterable<Presidente> FindPresidentiSenzaSquadra();
      
      
}
