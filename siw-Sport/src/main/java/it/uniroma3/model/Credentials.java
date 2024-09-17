package it.uniroma3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Credentials {

	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String username;
	private String password;
	
	private String role;
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String Role) {
		this.role = Role;
	}

	
     
	@OneToOne(mappedBy="credentials",cascade = CascadeType.ALL)
	private Presidente presidente;
	
	@OneToOne(mappedBy="credentialsAdm",cascade = CascadeType.ALL)
	private Admin admin;
	
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getUsername() {
		return username;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Presidente getPresidente() {
		return presidente;
	}

	public void setPresidente(Presidente Presidente) {
		this.presidente = Presidente;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
