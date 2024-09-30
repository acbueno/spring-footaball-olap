package br.com.acbueno.footaball.olap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalkeeperStatsOutputDTO {

  private Integer id;

  private Integer concedGoals;

  private Integer gameTotal;

}
