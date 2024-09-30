package br.com.acbueno.footaball.olap.model;

import java.util.Date;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@DenyAll
@NoArgsConstructor
@AllArgsConstructor
public class FatoDefesas {

  private Integer jogadorId;

  private Integer golsSofridos;

  private Integer jogos;

  private Date date;

}
