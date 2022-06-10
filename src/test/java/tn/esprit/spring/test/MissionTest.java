package tn.esprit.spring.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IMissionService;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MissionTest {

    private static final Logger log = LogManager.getLogger(MissionTest.class);

    @Autowired
    IMissionService missionService;

    Mission mission;


    @Before
    public void prepareMissionInstance() {

        log.info("//////// Running Unitary Tests on Entity Mission /////////////");
        log.info("//////////////Creating Instance of a Mission//////////////////");
        Mission mission = new Mission();
        mission.setName("JUNIT Testing");
        mission.setDescription("This mission includes testing the entity Mission and Deserves 20/20");
        this.mission = mission;
    }


    @Test
    public void missionCreationTest() {
        int id = 0;
        log.info("////////Starting Persistence Tests/////////////////");
        log.info("//////////////Adding Mission to DB/////////////////");

        id = missionService.addMission(this.mission);
        if (log.isDebugEnabled()) {
            log.debug("Mission Created with ID = " + String.valueOf(id));
        }
        assertFalse(id == 0);
    }

    @Test
    public void retrieveMissionWithWrongId() {
        int id=0;
        Mission m = missionService.getMissionById(id);
        assertFalse(m instanceof Mission);
    }


    @Test
    public void missionDeletionTest() {
        log.info("////////Pursuing Persistence Tests/////////////////");
        log.info("////////////Deleting Mission from DB///////////////");
        try {
            missionService.deleteMissionById(mission.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        assertTrue((missionService.getMissionById(mission.getId()) == null));
    }
}
