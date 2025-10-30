package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Captura {

    private final ServicoGrupo grupoDeServicos;
    private final ProcessoGrupo grupoDeProcessos;
    private final Rede rede;

    public Captura(ServicoGrupo grupoDeServicos, ProcessoGrupo grupoDeProcessos, Rede rede) {
        this.grupoDeServicos = grupoDeServicos;
        this.grupoDeProcessos = grupoDeProcessos;
        this.rede = new Looca().getRede();
    }

    public ServicoGrupo getGrupoDeServicos() {
        return grupoDeServicos;
    }

    public ProcessoGrupo getGrupoDeProcessos() {
        return grupoDeProcessos;
    }

    public Rede getRede() {
        return rede;
    }

    public String nomeRede(){
        List<RedeInterface> interfaces = rede.getGrupoDeInterfaces().getInterfaces();
        RedeInterface infRede = interfaces.get(7);
        String nome = infRede.getNome();
        return nome;
    }


    public String macAddress(){
        List<RedeInterface> interfaces = exibirInformacoesDeRede();
        RedeInterface infRede = interfaces.getLast();
        String macAddress = infRede.getEnderecoMac();
        return macAddress.replaceAll(":", "");
    }
    public Integer totalDeServicosAtivos(){

        Integer servicos = getGrupoDeServicos().getTotalServicosAtivos();
        return servicos;
    }

    public Integer totalDeProcessos(){

        Integer processos = getGrupoDeProcessos().getTotalProcessos();
        return processos;
    }

    public Integer totalDeThreads(){

        Integer threads = getGrupoDeProcessos().getTotalThreads();
        return threads;
    }

    public Double bytesEnviados(){
        List<RedeInterface> interfaces = rede.getGrupoDeInterfaces().getInterfaces();
        RedeInterface infRede = interfaces.get(7);
        Long bytesEnviados = infRede.getBytesEnviados();
        return bytesEnviados * 8/1000.;
    }

    public Double bytesRecebidos(){
        List<RedeInterface> interfaces = rede.getGrupoDeInterfaces().getInterfaces();
        RedeInterface infRede = interfaces.get(7);
        Long bytesRecebidos = infRede.getBytesRecebidos();
        return bytesRecebidos * 8/1000.;
    }

    public LocalDateTime dataHora(){
        LocalDateTime dataHora = LocalDateTime.now();
        return dataHora;
    }

    public List<RedeInterface> exibirInformacoesDeRede(){
        List<RedeInterface> interfaces = rede.getGrupoDeInterfaces().getInterfaces();
        List<RedeInterface> novaLista = new ArrayList<>();
        for (int i = 0; i < interfaces.size(); i++) {
            if (!interfaces.get(i).getEnderecoIpv4().isEmpty() && !interfaces.get(i).getEnderecoIpv6().isEmpty()){
                novaLista.add(interfaces.get(i));
            }
        }
        return novaLista;
    }

    public Double transferenciaEntradaKbps(){
        List<RedeInterface> lista = exibirInformacoesDeRede();
        Double taxaRecebidos = 0.00;
        for (RedeInterface infRede : lista) {
            Long startBytesRecebidos = infRede.getBytesRecebidos();

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            Long endBytesRecebidos = infRede.getBytesRecebidos();

            Long diferencaRecebidos = endBytesRecebidos - startBytesRecebidos;

            taxaRecebidos += (diferencaRecebidos * 8)/1000.;
        }
        return taxaRecebidos;
    }

    public Double transferenciaSaidaKbps(){
        List<RedeInterface> lista = exibirInformacoesDeRede();
        Double taxaEnviados = 0.;
        for (RedeInterface infRede : lista) {
            Long startBytesEnviados = infRede.getBytesEnviados();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            Long endBytesEnviados = infRede.getBytesEnviados();

            Long diferencaEnviados = endBytesEnviados - startBytesEnviados;

            taxaEnviados += (diferencaEnviados * 8)/1000.;
        }
        return taxaEnviados;
    }

//    public static void main(String[] args) {
//
//        Looca looca = new Looca();
//
//        System.out.println(looca.getRede().getGrupoDeInterfaces().getInterfaces());
//
//    }

}
