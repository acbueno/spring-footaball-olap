package br.com.acbueno.footaball.olap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogador {

  private Integer id;

  private String nome;

  private String posicao;

  private String time;

}
