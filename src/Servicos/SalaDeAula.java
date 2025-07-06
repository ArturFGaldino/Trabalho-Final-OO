package Servicos;

import javax.swing.*;
import java.util.Map;
import java.util.Random;

public class SalaDeAula extends EspacosFisicos {
    public SalaDeAula(String nome, int capacidade, String localizacao, String equipamentosDisponiveis,
            String disponibilidades) {
        super(nome, capacidade, localizacao, equipamentosDisponiveis, disponibilidades);
    }
    private int giz = 100;
    private final int canetao = 5;
    private int computadores = 60;
    private int aux=0;

    public void quantidadeEquipamentos(){
        Random aleatorio = new Random();
        if(aux != 1){
            int computadoresQuebrados = aleatorio.nextInt(16);
            computadores = computadores - computadoresQuebrados;
            aux+=1;
        }
        String strGiz = JOptionPane.showInputDialog(null, getEquipamentos() + "Quantos gizes vai precisar?");
        giz = giz - Integer.parseInt(strGiz);
    }

    public String getEquipamentos() {
        StringBuilder equipamentos = new StringBuilder();
        equipamentos.append("Equipamentos Disponíveis:\n").append("Giz: ").append(giz).append("\n").append("Canetão: ")
                .append(canetao).append("\n");
        if(getEquipamentosDisponiveis().contains("Computador")){
            equipamentos.append("Computadores Funcionando: ").append(computadores).append("\n");
        }
        return equipamentos.toString();
    }
    public String montaReservas(SalaDeAula sala){
        StringBuilder sb = new StringBuilder();
        sb.append(sala.getNome()).append(": \n");
        Map<String, String> esp = reservasPorEspaco.get(sala.getNome());
        for (Map.Entry<String, String> map:  esp.entrySet()){
            sb.append(map.getKey()).append(map.getValue());
        }
        return sb.toString();
    }
}
