package com.bigcorp.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*requete avec GenerationType.SEQUENCE :


INSERT INTO EXAMPLE( NAME) VALUES (example.getName());
requete 1 : 
SELECT SEQ_EXAMPLE.NEXTVAL() FROM DUAL;
stocke le résultat dans une variable prochainId
requete 2
INSERT INTO EXAMPLE(ID, NAME) VALUES (prochainId, example.getName());


requete avec GenerationType.IDENTITY : 
INSERT INTO EXAMPLE( NAME) VALUES (example.getName());*/

@Entity
public class Example {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {	
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Example [id=" + id + ", nom=" + nom + "]";
	}

}
