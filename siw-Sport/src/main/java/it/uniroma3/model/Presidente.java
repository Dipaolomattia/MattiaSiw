package it.uniroma3.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Presidente {
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private Date DataNascita;
	 private String luogo;
	 private String nome;
	 private String cognome;
	 private String CodiceFiscale;
	 
	@OneToOne (fetch=FetchType.EAGER)
	@JoinColumn(name = "squadra_id", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Squadra squadra;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Credentials credentials;

	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public Squadra getSquadra() {
		return squadra;
	}
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
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
	public String getLuogo() {
			return luogo;
    }
		public void setLuogo(String luogo) {
			this.luogo = luogo;
		}
		 public String getCodiceFiscale() {
				return CodiceFiscale;
		}
			public void setCodiceFiscale(String codiceFiscale) {
				CodiceFiscale = codiceFiscale;
			}
	
	 @Override
		public int hashCode() {
			return Objects.hash(cognome, DataNascita, luogo, id, nome,CodiceFiscale);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Presidente other = (Presidente) obj;
			return Objects.equals(cognome, other.cognome) && Objects.equals(DataNascita, other.DataNascita)
			&& Objects.equals(luogo, other.luogo)
			&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome) &&Objects.equals(CodiceFiscale, other.CodiceFiscale) ;
		}
   

}
