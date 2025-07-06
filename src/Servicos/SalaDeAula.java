package Servicos;

import javax.swing.*;
import java.util.Random;

public class SalaDeAula extends EspacosFisicos {
    protected SalaDeAula(String nome, int capacidade, String localizacao, String equipamentosDisponiveis,
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
        String strGiz = JOptionPane.showInputDialog(null, getEquipamentosDisponiveis() + "Quantos gizes vai precisar?");
        giz = giz - Integer.parseInt(strGiz);
    }

    public String getEquipamentosDisponiveis() {
        StringBuilder equipamentos = new StringBuilder();
        equipamentos.append("Equipamentos Disponíveis:\n").append("Giz: ").append(giz).append("\n").append("Canetão: ")
                .append(canetao).append("\n");
        if(this.equipamentosDisponiveis.contains("Computador")){
            equipamentos.append("Computadores Funcionando: ").append(computadores).append("\n");
        }
        return equipamentos.toString();
    }

}
