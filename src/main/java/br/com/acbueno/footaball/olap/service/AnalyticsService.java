package br.com.acbueno.footaball.olap.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.footaball.olap.dto.GoalStatsOutPutDTO;
import br.com.acbueno.footaball.olap.dto.GoalkeeperStatsOutputDTO;
import br.com.acbueno.footaball.olap.repository.FootballRepository;

@Service
public class AnalyticsService {

  @Autowired
  private FootballRepository repository;

  public List<GoalStatsOutPutDTO> getTopSocers() {
    return repository.getTopScorers();
  }

  public List<GoalkeeperStatsOutputDTO> getTopGoalkeepers() {
    return repository.getTopGoalkeepers();
  }
}
