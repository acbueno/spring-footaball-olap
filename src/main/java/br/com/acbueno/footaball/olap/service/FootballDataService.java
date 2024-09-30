package br.com.acbueno.footaball.olap.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.acbueno.footaball.olap.dto.FoulsInputDTO;
import br.com.acbueno.footaball.olap.dto.GamesInputDTO;
import br.com.acbueno.footaball.olap.dto.GoalsIputDTO;
import br.com.acbueno.footaball.olap.dto.PlayerApiRoot;
import br.com.acbueno.footaball.olap.dto.PlayerInputDTO;
import br.com.acbueno.footaball.olap.dto.ResponseDataPlayer;
import br.com.acbueno.footaball.olap.dto.ResponseDataTeam;
import br.com.acbueno.footaball.olap.dto.StatisticsInputDTO;
import br.com.acbueno.footaball.olap.dto.TeamApiRoot;
import br.com.acbueno.footaball.olap.dto.TeamInputDTO;
import br.com.acbueno.footaball.olap.model.Jogador;
import br.com.acbueno.footaball.olap.model.Time;
import br.com.acbueno.footaball.olap.repository.FootballRepository;

@Service
public class FootballDataService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private FootballRepository footaballRepository;

  @Value("${api.football.url}")
  private String apiUrl;

  @Value("${api.football.key}")
  private String apiKey;

  private static final String API_PLAYER_STATS = "/players?season=2022&league=71";

  private static final String API_TEAM_STATS = "/teams?season=2022&league=71";

  public void fetchAndStoreData() throws JsonProcessingException {
    HttpEntity<String> entity = setHeaders();
    ResponseEntity<TeamApiRoot> responseTeam = getReponseApi(entity);
    ResponseEntity<PlayerApiRoot> responsePlayer = reponsePlayer(entity);
    PlayerApiRoot apiResponsePlayer = responsePlayer.getBody();
    mounDataAndSavePlayer(apiResponsePlayer);
    TeamApiRoot apiReponseTeam = responseTeam.getBody();
    mountDataAndSaveTeam(apiReponseTeam);
  }

  private void mountDataAndSaveTeam(TeamApiRoot apiReponseTeam) {
    for (ResponseDataTeam teamItem : apiReponseTeam.getResponse()) {
      Time time = new Time();
      time.setId(teamItem.getTeam().getId());
      time.setName(teamItem.getTeam().getName());
      footaballRepository.saveTime(time);
    }
  }

  private void mounDataAndSavePlayer(PlayerApiRoot apiResponsePlayer) {
    for (ResponseDataPlayer responseData : apiResponsePlayer.getResponse()) {
      PlayerInputDTO playerDTO = responseData.getPlayer();
      Jogador jogador = new Jogador();
      jogador.setId(playerDTO.getId());
      jogador.setNome(playerDTO.getName());
      savePlayerStats(responseData, jogador);
    }
  }

  private void savePlayerStats(ResponseDataPlayer responseData, Jogador jogador) {
    for (StatisticsInputDTO statisticsDTO : responseData.getStatistics()) {
      TeamInputDTO teamDTO = statisticsDTO.getTeam();
      GamesInputDTO gamesDTO = statisticsDTO.getGames();
      jogador.setTime(teamDTO.getName());
      jogador.setPosicao(gamesDTO.getPosition());
      footaballRepository.savePlayerData(jogador);
      //@formatter:off
      Integer goals = Optional.ofNullable(statisticsDTO.getGoals())
          .map(GoalsIputDTO::getTotal)
          .orElse(0);
      //@formattter:on
      LocalDate today = LocalDate.now();
      Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
      footaballRepository.saveGoalData(jogador.getId(), goals, date);
      Integer concededGoal = Optional.ofNullable(statisticsDTO.getGoals())
          .map(GoalsIputDTO::getConceded)
          .orElse(0);
      Integer games = Optional.ofNullable(gamesDTO.getNumber()).orElse(0);
      footaballRepository.saveDefesasData(jogador.getId(), concededGoal, games, date);
      Integer fouls = Optional.ofNullable(statisticsDTO.getFouls())
          .map(FoulsInputDTO::getCommitted)
          .orElse(0);
      footaballRepository.saveFaltasData(jogador.getId(), fouls, date);
    }
  }

  private ResponseEntity<PlayerApiRoot> reponsePlayer(HttpEntity<String> entity) {
    ResponseEntity<PlayerApiRoot> responsePlayer = restTemplate.exchange(
        apiUrl + API_PLAYER_STATS, 
        HttpMethod.GET, entity, 
        PlayerApiRoot.class);
    return responsePlayer;
  }

  private ResponseEntity<TeamApiRoot> getReponseApi(HttpEntity<String> entity) {
    ResponseEntity<TeamApiRoot> responseTeam = restTemplate.exchange(
        apiUrl + API_TEAM_STATS, 
        HttpMethod.GET, entity, 
        TeamApiRoot.class);
    return responseTeam;
  }

  private HttpEntity<String> setHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-rapidapi-hos", "v3.football.api-sports.io");
    headers.set("x-rapidapi-key", apiKey);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    return entity;
  }

}
