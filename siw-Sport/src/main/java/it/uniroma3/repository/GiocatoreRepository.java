package it.uniroma3.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Giocatore;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GiocatoreRepository extends CrudRepository<Giocatore, Long> {

    public Giocatore findGiocatoreById(Long id);
    
    @Query(value = " SELECT gi.data_nascita,gi.datatesseramento,gi.id,gi.squadra_id,gi.nome,gi.cognome,gi.ruolo FROM presidente pr join squadra sq on pr.squadra_id=sq.id join giocatore gi on sq.id=gi.squadra_id where pr.id=:fid", nativeQuery = true)
    public List<Giocatore> findGiocatoriByPresidenteid(@Param("fid")Long id);
    
    @Query(value = " SELECT gi.data_nascita,gi.datatesseramento,gi.id,gi.squadra_id,gi.nome,gi.cognome,gi.ruolo FROM giocatore gi where gi.squadra_id isnull", nativeQuery = true)
    public List<Giocatore>findGiocatoriSvincolati();
    
    @Query(value = " SELECT gi.data_nascita,gi.datatesseramento,gi.id,gi.squadra_id,gi.nome,gi.cognome,gi.ruolo FROM giocatore gi where gi.squadra_id =:fid", nativeQuery = true)
    public List<Giocatore> findGiocatoriBySquadraid(@Param("fid")Long id);
    
    
    public List<Giocatore> findGiocatoreByNome(@Param("fid")String nome);
    
    

}
