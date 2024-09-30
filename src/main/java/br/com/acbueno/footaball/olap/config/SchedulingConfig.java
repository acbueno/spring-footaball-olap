package br.com.acbueno.footaball.olap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.acbueno.footaball.olap.service.FootballDataService;

@EnableScheduling
@Configuration
public class SchedulingConfig {

  @Autowired
  private FootballDataService footballDataService;

  @EventListener(ApplicationReadyEvent.class)
  public void onStartUp() throws JsonProcessingException {
    footballDataService.fetchAndStoreData();
  }

  // Executa uma vez ao dia
  @Scheduled(cron = "0 0 2 * * *") // Executa às 2:00 AM todos os dias
  public void scheduleDailyDataUpdate() throws JsonProcessingException {
    footballDataService.fetchAndStoreData();
  }

}
