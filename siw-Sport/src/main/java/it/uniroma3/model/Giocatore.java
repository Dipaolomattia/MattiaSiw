package it.uniroma3.model;

import java.sql.Date;
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
import jakarta.persistence.ManyToOne;


@Entity
public class Giocatore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	private Date DataNascita;
	private Date Datatesseramento;
	 private String nome;
	 private String Cognome;
	 private String Ruolo;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "squadra_id", nullable = true)
	 @OnDelete(action = OnDeleteAction.SET_NULL)  // Imposta a NULL la chiave esterna
	 private Squadra squadra;
	 
//	 @ManyToOne
//	 private User user;		//autore della recensione

//	@ManyToOne
//	 private Gioco gioco;
//	
//	 
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public Gioco getGioco() {
//		return gioco;
//	}
//	public void setGioco(Gioco gioco) {
//		this.gioco = gioco;
//	}

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
	public Date getDatatesseramento() {
		return Datatesseramento;
	}
	public void setDatatesseramento(Date datatesseramento) {
		Datatesseramento = datatesseramento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getRuolo() {
		return Ruolo;
	}
	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
	 @Override
		public int hashCode() {
			return Objects.hash(Cognome, DataNascita, Datatesseramento, Ruolo, id, nome);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Giocatore other = (Giocatore) obj;
			return Objects.equals(Cognome, other.Cognome) && Objects.equals(DataNascita, other.DataNascita)
					&& Objects.equals(Datatesseramento, other.Datatesseramento) && Objects.equals(Ruolo, other.Ruolo)
					&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
		}
   

}
