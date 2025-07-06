package Servicos;
import javax.swing.*;
import java.util.ArrayList;

public class Laboratorio extends EspacosFisicos {
    public Laboratorio(String nome, int capacidade, String localizacao, String equipamentosDisponiveis, String disponibilidades) {
        super(nome,capacidade, localizacao, equipamentosDisponiveis, disponibilidades);
    }
    private StringBuilder equipamentos = new StringBuilder();
    private ArrayList<String> equipamentosV = new ArrayList<>();
    public String getEquipamentosDisponiveis() {
        return equipamentos.toString();
    }
    public void adicionarEquipamento() {
            String adicionado = JOptionPane.showInputDialog("Digite o equipamento a ser adicionado: ");
            if (adicionado != null && !adicionado.trim().isEmpty()) {
                if (!equipamentosV.contains(adicionado)) {
                    if (equipamentosV.isEmpty()) {
                        equipamentos.append("Equipamentos: \n");
                    }
                    equipamentos.append(adicionado).append("\n");
                    equipamentosV.add(adicionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Este equipamento já existe!");
                }
            }
    }

    public void removerEquipamento() {
        if (equipamentosV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há equipamentos para remover!");
            return;
        }

        String lista = "Equipamentos disponíveis:\n";
        for (String eq : equipamentosV) {
            lista += "- " + eq + "\n";
        }

        String removido = JOptionPane.showInputDialog(lista + "Digite o equipamento que deseja remover: ");

        if (removido != null && equipamentosV.remove(removido)) {
            // Reconstroi a lista de equipamentos
            equipamentos = new StringBuilder("Equipamentos: \n");
            for (String eq : equipamentosV) {
                equipamentos.append(eq).append("\n");
            }
            JOptionPane.showMessageDialog(null, "Equipamento removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Equipamento não encontrado!");
        }
    }
}
