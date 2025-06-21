package Servicos;

public abstract class EspacosFisicos {
    public static int capacidade;
    public static String localizacao, equipamentosDisponiveis, disponibilidades;

    EspacosFisicos(int capacidade, String localizacao, String equipamentosDisponiveis, String disponibilidades) {
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.equipamentosDisponiveis = equipamentosDisponiveis;
        this.disponibilidades = disponibilidades;
    }


    // Gets
    public int getCapacidade(){
        return  capacidade;
    }

    public static String getLocalizacao() {
        return localizacao;
    }

    public static String getEquipamentosDisponiveis() {
        return equipamentosDisponiveis;
    }

    public static String getDisponibilidades() {
        return disponibilidades;
    }
    
}
