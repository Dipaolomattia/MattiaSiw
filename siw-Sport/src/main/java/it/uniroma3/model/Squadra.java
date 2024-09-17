package it.uniroma3.model;
import java.util.List;

import java.util.Objects;
import java.util.Optional;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

//vediamo se non lo vede 2

@Entity
public class Squadra {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		 private Long id;
	  
		private String nome;
		 private Integer year;
		 private String indirizzo;
		 
		 @OneToOne(mappedBy = "squadra",fetch=FetchType.EAGER)
		 @JoinColumn(name = "squadra_id", nullable = true)
		 @OnDelete(action = OnDeleteAction.SET_NULL) // Imposta la chiave esterna a NULL quando la squadra viene eliminata
		 private Presidente user;
		
		 @OneToMany(mappedBy = "squadra", orphanRemoval = false)
		 @OnDelete(action = OnDeleteAction.SET_NULL) // Imposta la chiave esterna a NULL per i giocatori
		 private List<Giocatore> giocatori;
		
         public Presidente getUser() {
			return user;
		 }

		public void setUser(Presidente User) {
			this.user = User;
		}
		//		 @OneToMany(mappedBy = "gioco",cascade = CascadeType.ALL)
//		 private List<Recensione>recensioni;
//
//		@ManyToOne
//		 private User userg;
//
//     	@OneToOne
//     	private Image immagine;
//     	
//     	
//     	
//        public Image getImmagine() {
//    			return immagine;
//    		}
//        public void setImmagine(Image immagine){
//        	this.immagine=immagine;
//        }
//
//	    public List<Recensione> getRecensioni() {
//			return recensioni;
//		}
//		public void setRecensioni(List<Recensione> recensioni) {
//			this.recensioni = recensioni;
//			
//		}
//		public User getUserg() {
//			return userg;
//		}
//		public void setUserg(User userg) {
//			this.userg = userg;
//		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getIndirizzo() {
			return indirizzo;
		}
		public void setIndirizzo(String Indirizzo) {
			this.indirizzo = Indirizzo;
		}
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}
	 	public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
	
		
		
	    @Override
		public int hashCode() {
			return Objects.hash(id, indirizzo, nome,year);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Squadra other = (Squadra) obj;
			return  Objects.equals(indirizzo, other.indirizzo) && Objects.equals(year, other.year)
					&& Objects.equals(nome, other.nome);
		}

	}
