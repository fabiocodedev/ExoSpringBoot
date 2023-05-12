package fr.spear.revision.Bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message="Il manque le nom...")
	@Column(name="nom", length=50)
	private String nom;
	@NotBlank(message="Il manque le prenom...")
	@Column(name="prenom", length=50)
	private String prenom;
	@Email
	@NotBlank(message="L''email ne dois pas être null et dois être unique !")
	@Column(name="email", length=50, nullable=false, unique=true)
	private String email;
	@NotBlank(message="Il manque le mot de passe...")
	@Column(name="password")
	private String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
