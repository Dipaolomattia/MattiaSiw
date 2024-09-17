package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Admin;
import it.uniroma3.model.Presidente;
import it.uniroma3.repository.AdminRepository;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class AdminService {

    @Autowired
    protected AdminRepository adminRepository;

    
    
    
    public Admin findById(Long id) {
		return adminRepository.findById(id).get();
	}
    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public Admin getUser(Long id) {
        Optional<Admin> result = this.adminRepository.findById(id);
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
    public Admin saveUser(Admin user) {
        return this.adminRepository.save(user);
    }
    

    
    public Iterable<Admin>findAll(){
		return adminRepository.findAll();	
	}
    
    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Admin> getAllUsers() {
        List<Admin> result = new ArrayList<>();
        Iterable<Admin> iterable = this.adminRepository.findAll();
        for(Admin user : iterable)
            result.add(user);
        return result;
    }
   
    public void deleteById(Long id) {
    	adminRepository.deleteById(id);
    }
}
