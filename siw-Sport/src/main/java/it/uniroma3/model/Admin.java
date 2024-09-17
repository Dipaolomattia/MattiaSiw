package it.uniroma3.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Admin {
@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private Date DataNascita;
	 private String nome;
	 private String cognome;
	
	 
	 
	 @OneToOne(cascade = CascadeType.ALL)
		private Credentials credentialsAdm;
	
	public Credentials getCredentials() {
		return credentialsAdm;
	}
	public void setCredentials(Credentials credentialsAdm) {
		this.credentialsAdm = credentialsAdm;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	 public Date getDataNascita() {
		return DataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		DataNascita = dataNascita;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String Nome) {
		this.nome = Nome;
	}
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String Cognome) {
		this.cognome = Cognome;
	}
	
	
	 @Override
		public int hashCode() {
			return Objects.hash(cognome, DataNascita,  id, nome);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Admin other = (Admin) obj;
			return Objects.equals(cognome, other.cognome) && Objects.equals(DataNascita, other.DataNascita)
		
			&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)  ;
		}
   

}
