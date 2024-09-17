package it.uniroma3.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Credentials;

import it.uniroma3.model.Presidente;
import it.uniroma3.repository.PresidenteRepository;


@Component
public class PresidenteValidator implements Validator{

	@Autowired
	private PresidenteRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Presidente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Presidente presidente = (Presidente)target;
		if(presidente.getNome()!=null && presidente.getCognome()!=null && 
				userRepository.existsByNomeAndCognome(presidente.getNome(), presidente.getCognome())) {
			errors.reject("user.duplicate");
		}
		
	}

}
