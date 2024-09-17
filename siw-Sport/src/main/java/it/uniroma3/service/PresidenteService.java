package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Presidente;
import it.uniroma3.repository.PresidenteRepository;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class PresidenteService {

    @Autowired
    protected PresidenteRepository userRepository;
    
    public Presidente getPresidenteByuser(String username) {
    	return userRepository.FindPresidenteSn(username);
    	
    }  
    
    
    
    
    public Presidente findById(Long id) {
		return userRepository.findById(id).get();
	}
    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public Presidente getUser(Long id) {
        Optional<Presidente> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public Presidente saveUser(Presidente user) {
        return this.userRepository.save(user);
    }
    

    
    public Iterable<Presidente>findAll(){
		return userRepository.findAll();	
	}
    
    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Presidente> getAllUsers() {
        List<Presidente> result = new ArrayList<>();
        Iterable<Presidente> iterable = this.userRepository.findAll();
        for(Presidente user : iterable)
            result.add(user);
        return result;
    }
    public Iterable<Presidente>FindPresidentiSenzaSquad() {
    	
    	return userRepository.FindPresidentiSenzaSquadra();
    }
    
    public void deleteById(Long id) {
    	userRepository.deleteById(id);
    }
}
