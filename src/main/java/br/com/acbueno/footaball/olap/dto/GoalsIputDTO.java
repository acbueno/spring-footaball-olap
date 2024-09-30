package br.com.acbueno.footaball.olap.dto;

import lombok.Data;

@Data
public class GoalsIputDTO {

  private Integer total; // Pode ser null
  private Integer saves;
  private Integer conceded;

}
