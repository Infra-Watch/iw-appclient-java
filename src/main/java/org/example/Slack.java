package org.example;
import org.springframework.jdbc.core.JdbcTemplate;

public class Slack {

    private final JdbcTemplate jdbc;

    public Slack() {
        this.jdbc = new ConexaoBanco().getJdbcTemplate();
    }

    public String buscarIdCanalSlack(Integer idEmpresa) {
        String sql = "SELECT idSlack FROM empresa WHERE idEmpresa = ?";

        try {
            return jdbc.queryForObject(sql, new Object[]{idEmpresa}, String.class);
        } catch (Exception e) {
            return null;
        }
    }
}



