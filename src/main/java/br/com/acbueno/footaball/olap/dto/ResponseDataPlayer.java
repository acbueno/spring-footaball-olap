package br.com.acbueno.footaball.olap.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDataPlayer {

  private PlayerInputDTO player;

  @JsonProperty("statistics")
  private List<StatisticsInputDTO> statistics;

}
