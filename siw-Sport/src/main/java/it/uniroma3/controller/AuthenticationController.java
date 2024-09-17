package it.uniroma3.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.controller.validator.CredentialsValidator;
import it.uniroma3.controller.validator.PresidenteValidator;
import it.uniroma3.model.Admin;
import it.uniroma3.model.Credentials;
import it.uniroma3.model.Presidente;
import it.uniroma3.service.AdminService;
import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.PresidenteService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;

    
	
    @GetMapping("/SceltaRegistrazione")
	public String sceltaRegistrazione(Model model) {
		return "SceltaRegistrazione.html";
	}
    
    
	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("presidente", new Presidente());
		model.addAttribute("credentials", new Credentials());
		return "formRegisterPresidente";
		
	}
	@GetMapping(value = "/registerAdmin") 
	public String showRegisterFormAdmin (Model model) {
		model.addAttribute("Admin", new Admin());
		
		model.addAttribute("credentials", new Credentials());
		
		
		return "formRegisterAdmin";
	}
	
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "formLogin";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "index.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    			model.addAttribute("AdminId", credentials.getAdmin().getId());
         		model.addAttribute("Admin", credentials.getAdmin());
				return "indexAdmin.html";
			}
			if (credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
				model.addAttribute("presidenteId", credentials.getPresidente().getId());
				model.addAttribute("presidente", credentials.getPresidente());
				return "indexUser.html";
			}
			
			if(credentials.getRole().equals(null)) {
				return "index.html";
			}
		}
        return "index.html";
	}
		
    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            	model.addAttribute("AdminId", credentials.getAdmin().getId());
         		model.addAttribute("Admin", credentials.getAdmin());
            return "indexAdmin.html";
            
        }
    	
    	if (credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
    		
    			model.addAttribute("presidenteId", credentials.getPresidente().getId());
                model.addAttribute("presidente", credentials.getPresidente());
                return "indexUser.html";
    	
  	     	
   	        }
    	
        return "index.html";
    }

	
	
	
	
	
}