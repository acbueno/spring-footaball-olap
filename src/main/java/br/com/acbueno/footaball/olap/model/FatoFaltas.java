package br.com.acbueno.footaball.olap.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatoFaltas {

  private Integer jogadorId;

  private Integer faltas;

  private Date data;

}
