package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.myfaces.shade.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	
	private static final Logger l = LogManager.getLogger(LoggingAspect.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		
		return employeRepository.getEmployeByEmailAndPassword(login, DigestUtils.md5Hex(password).toUpperCase());
		
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try {
			
		Employe employe = employeRepository.findById(employeId).orElse(null);
		if ( employe != null) {
		employe.setEmail(email);
		employeRepository.save(employe);
		}}
		catch(Exception e) {
			l.error("Erreur lors de la mise à jour du mail");
		}

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		try {
			Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
			Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
              if(depManagedEntity != null) {
			if (depManagedEntity.getEmployes() == null) {

				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			} else {

				depManagedEntity.getEmployes().add(employeManagedEntity);
			}

			deptRepoistory.save(depManagedEntity);
		}} catch (Exception e) {
			l.error("Erreur lors de l'affectation employé departement");
		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{ try {
		Departement dep = deptRepoistory.findById(depId).orElse(null);
  if (dep !=null) {
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}}
	catch(Exception e) {
		l.error("Erreur lors de la désaffectation employé departement");
	}
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		try {
			contratRepoistory.save(contrat);
		}
		    catch (Exception exp) {
		    	l.error("Ajout contrat error",exp.getMessage());
		    }
		        l.info("Contrat Ajouté avec succée");
				return contrat.getReference();
			}


	public void affecterContratAEmploye(int contratId, int employeId) {
		try {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		if (contratManagedEntity != null && employeManagedEntity != null) {
		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		}}
		catch (Exception e)
		{
			l.error("Erreur lors de l'affectation employé Contrat");
		}

	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		if (employeManagedEntity != null) {
		return employeManagedEntity.getPrenom();
		}
		else 
			return null;
	}
	 
	public void deleteEmployeById(int employeId)
	{
		Employe employe = employeRepository.findById(employeId).orElse(null);

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		if (employe != null)
		{
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}}

	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		if (contratManagedEntity != null) {
			contratRepoistory.delete(contratManagedEntity);
			} else {
				l.error("Contrat may be NULL");
			}
			}


	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
