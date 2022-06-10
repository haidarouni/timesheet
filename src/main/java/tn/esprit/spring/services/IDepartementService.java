package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	
	
	public List<Departement> getAllDepartements();
	public Departement getById(int id);
	public Departement save(Departement depart);
	public void delete(int id);


	
	
	

	
}
