package tn.esprit.spring.test;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IMissionService;
import tn.esprit.spring.services.ITimesheetService;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {

	private static final Logger log = LogManager.getLogger(TimesheetTest.class);

	@Autowired
	ITimesheetService timesheetService;
	@Autowired
	IEmployeService employeService;
	@Autowired
    IMissionService missionService;

	Timesheet timesheet;
	Mission mission;
	Employe employe;

	@Before
	public void initializeInstance() {
		log.info("Starting Tests on Timesheet");

		Timesheet timesheet = new Timesheet();
		timesheet.setValide(true);

		Employe employe = new Employe();
		employe.setNom("Rania Ben Khalil");
		employe.setActif(true);
		Mission mission = new Mission();

		mission.setName("Test");
		mission.setDescription("Junit testing");

		this.timesheet = timesheet;
		this.mission = mission;
		this.employe = employe;

	}

	@Test
	public void addTimesheetTest() {
		log.info("Starting Tests on Timesheet");
		log.info("Persisting Instances");

		employeService.addOrUpdateEmploye(employe);
		missionService.addMission(mission);

		log.info("Persisting the newly created timesheet");
		TimesheetPK pk = timesheetService.ajouterTimesheet(mission.getId(), employe.getId(), new Date(), new Date());
		if (pk!=null) {
			log.info("Timesheet persisté avec succès");
			assertTrue(true);
		}
		else {
			log.error("Persistence problem for timesheet");
			fail();
		}
	}

	@Test
	public void testFindMissionsByEmploye() {
		log.info("Testing findAllMissionByEmployeJPQL Function");
		log.info("Fetching Missions for Employee " +employe.getNom()+ " Having ID = "+employe.getId());
		List<Mission> missions = timesheetService.findAllMissionByEmployeJPQL(employe.getId());
		if (missions.isEmpty()) {
			log.info("Employee has no missions");
		}
		else {
			log.info("Employee "+employe.getNom()+" is currently affected to these missions : ");
			missions.forEach( mission1 -> log.info(mission.getName()));
			assertTrue(true);
		}
	}
}
