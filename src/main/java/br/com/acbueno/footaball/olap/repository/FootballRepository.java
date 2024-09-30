package br.com.acbueno.footaball.olap.repository;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import br.com.acbueno.footaball.olap.dto.GoalStatsOutPutDTO;
import br.com.acbueno.footaball.olap.dto.GoalkeeperStatsOutputDTO;
import br.com.acbueno.footaball.olap.model.Jogador;
import br.com.acbueno.footaball.olap.model.Time;

@Repository
public class FootballRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void savePlayerData(Jogador jogador) {
    String sql = "INSERT INTO jogador (id, nome, posicao, time) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, jogador.getId(), jogador.getNome(), jogador.getPosicao(),
        jogador.getTime());
  }

  public void saveGoalData(Integer jogadorId, Integer goals, Date data) {
    String sql = "INSERT INTO fato_gols (jogador_id, gols, data) VALUES (?,?,?)";
    jdbcTemplate.update(sql, jogadorId, goals, data);
  }

  public void saveDefesasData(Integer jogadorId, Integer golsSofridos, Integer jogos, Date data) {
    String sql =
        "INSERT INTO fato_defesas (jogador_id, gols_sofridos, jogos, data) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, jogadorId, golsSofridos, jogos, data);
  }

  public void saveFaltasData(Integer jogadorId, Integer faltas, Date data) {
    String sql = "INSERT INTO fato_faltas (jogador_id, faltas, data) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, jogadorId, faltas, data);
  }

  public void saveTime(Time time) {
    String sql = "INSERT INTO time(id, nome) VALUES (?, ?)";
    jdbcTemplate.update(sql, time.getId(), time.getName());
  }



  // Artilheiros (jogadores com mais gols)
  public List<GoalStatsOutPutDTO> getTopScorers() {
    String sql =
        "SELECT jogador_id, SUM(gols) as total_gols FROM fato_gols GROUP BY jogador_id ORDER BY total_gols DESC LIMIT 10";
    return jdbcTemplate.query(sql,
        (rs, rowNum) -> new GoalStatsOutPutDTO(rs.getInt("jogador_id"), rs.getInt("total_gols")));
  }

  // Goleiros menos vazados
  public List<GoalkeeperStatsOutputDTO> getTopGoalkeepers() {
    String sql =
        "SELECT jogador_id, SUM(gols_sofridos) as total_gols_sofridos, SUM(jogos) as total_jogos FROM fato_defesas GROUP BY jogador_id ORDER BY total_gols_sofridos ASC LIMIT 10";
    return jdbcTemplate.query(sql, (rs, rowNum) -> new GoalkeeperStatsOutputDTO(rs.getInt("jogador_id"),
        rs.getInt("total_gols_sofridos"), rs.getInt("total_jogos")));
  }

}
