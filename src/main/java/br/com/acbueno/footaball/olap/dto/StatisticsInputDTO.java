package br.com.acbueno.footaball.olap.dto;

import lombok.Data;

@Data
public class StatisticsInputDTO {

  private TeamInputDTO team;

  private GamesInputDTO games;

  private GoalsIputDTO goals;

  private FoulsInputDTO fouls;

  private PenaltyInputDTO penalty;

}
