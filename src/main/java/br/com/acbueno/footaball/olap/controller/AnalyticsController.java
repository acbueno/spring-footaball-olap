package br.com.acbueno.footaball.olap.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.acbueno.footaball.olap.dto.GoalStatsOutPutDTO;
import br.com.acbueno.footaball.olap.dto.GoalkeeperStatsOutputDTO;
import br.com.acbueno.footaball.olap.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

  @Autowired
  private AnalyticsService analyticsService;

  @GetMapping("/top/goalkeepers")
  public ResponseEntity<List<GoalkeeperStatsOutputDTO>> getTopGoalKeeeper() {
    return ResponseEntity.ok(analyticsService.getTopGoalkeepers());
  }

  @GetMapping("/top/socorers")
  public ResponseEntity<List<GoalStatsOutPutDTO>> getTopSocorers() {
    return ResponseEntity.ok(analyticsService.getTopSocers());
  }

}
