package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import oshi.SystemInfo;

import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Looca looca = new Looca();

        Captura captura = new Captura(new ServicoGrupo(), new ProcessoGrupo(), new Rede(new SystemInfo()));
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        conexaoBanco.getJdbcTemplate();

        captura.nomeRede();
        String macAddress = captura.macAddress();
        Integer totalServicos = captura.totalDeServicosAtivos();
        Integer totalProcessos = captura.totalDeProcessos();
        Integer totalThreads = captura.totalDeThreads();
        Double transferenciaEntrada = captura.transferenciaEntradaKbps();
        Double transferenciaSaida = captura.transferenciaSaidaKbps();
        LocalDateTime dataHora = captura.dataHora();


        System.out.println(captura.exibirInformacoesDeRede());

        conexaoBanco.inserirCapturaJava( macAddress,totalServicos, totalProcessos, totalThreads,transferenciaEntrada,transferenciaSaida, dataHora);

    }
}
