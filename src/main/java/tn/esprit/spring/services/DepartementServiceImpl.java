package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
@Slf4j
@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository deptRepoistory;


	@Override
	public List<Departement> getAllDepartements() {
		log.info("fetching all Departements");
		return (List<Departement>) deptRepoistory.findAll();
	}
	@Override
	public Departement getById(int id) {
		log.info("fetching Departement of id: "+id);
		return deptRepoistory.findById(id).orElse(null);
	}
	
	@Override
	public Departement save(Departement depart) {
		log.info("saving Departement of with name: "+depart.getName());
		return deptRepoistory.save(depart);
	}
	
	@Override
	public void delete(int id) {
		log.info("removing Departement of id: "+id);
		deptRepoistory.deleteById(id);
	}

}
