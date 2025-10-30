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

        String macAddress;
        Integer totalServicos;
        Integer totalProcessos;
        Integer totalThreads;
        Double transferenciaEntrada;
        Double transferenciaSaida;
        LocalDateTime dataHora;

        while (true){

            macAddress = captura.macAddress();
            totalServicos = captura.totalDeServicosAtivos();
            totalProcessos = captura.totalDeProcessos();
            totalThreads = captura.totalDeThreads();
            transferenciaEntrada = captura.transferenciaEntradaKbps();
            transferenciaSaida = captura.transferenciaSaidaKbps();
            dataHora = captura.dataHora();

            System.out.println("Mac Address: " + macAddress);
            System.out.println("Total de Serviços ativos: " + totalServicos);
            System.out.println("Total de Processos: " + totalProcessos);
            System.out.println("Total de Threads: " + totalThreads);
            System.out.println("Bytes Recebidos: " + transferenciaEntrada);
            System.out.println("Bytes Enviados: " + transferenciaSaida);
            System.out.println("Data/Hora da captura: " + dataHora);

            conexaoBanco.inserirCapturaJava( macAddress,totalServicos, totalProcessos,
                    totalThreads,transferenciaEntrada,transferenciaSaida, dataHora);


        }
    }
}
