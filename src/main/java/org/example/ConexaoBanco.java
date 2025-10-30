package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class ConexaoBanco {

    private final JdbcTemplate jdbcTemplate;
    private final BasicDataSource basicDataSource;

    public ConexaoBanco() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://3.85.148.68:3306/infrawatch?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC-3");
        basicDataSource.setUsername("captura_java");
        basicDataSource.setPassword("jarInfrawatch1234");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        this.basicDataSource = basicDataSource;
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void inserirCapturaJava(String macAddress, Integer totalDeServicosAtivos,
                                   Integer totalDeProcessos, Integer totalDeThreads, Double transferenciaEntrada,
                                   Double transferenciaSaida, LocalDateTime dataHora){
        jdbcTemplate.update("CALL inserir_captura_java(?,?,?,?,?,?,?)", macAddress, totalDeServicosAtivos,
                totalDeProcessos, totalDeThreads, transferenciaEntrada, transferenciaSaida, dataHora);
        System.out.println("\nDados inseridos no banco!\n");

    }
}
