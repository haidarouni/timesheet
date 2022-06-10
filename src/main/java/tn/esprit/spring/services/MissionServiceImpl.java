package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;

import java.util.List;

@Service
public class MissionServiceImpl implements IMissionService {

    private static final Logger log = LogManager.getLogger(MissionServiceImpl.class);

    @Autowired
    MissionRepository missionRepository;

    @Override
    public int addMission(Mission mission) {
       int id = missionRepository.save(mission).getId();
       log.info("Mission Added Successfully");
       log.info(mission.toString());
       return id;
    }

    @Override
    public void deleteMissionById(int idMission) {
        missionRepository.deleteById(idMission);
    }

    @Override
    public void deleteMission(Mission mission) {
        missionRepository.delete(mission);
    }

    @Override
    public List<Mission> getAllMissions() {
        return (List<Mission>) missionRepository.findAll();
    }

    @Override
    public Mission getMissionById(int idMission) {
        Mission found = missionRepository.findById(idMission).orElse(null);
        if (found == null) {
            log.error("No mission Found with ID : " +idMission);
        }
        return found;
    }
}
