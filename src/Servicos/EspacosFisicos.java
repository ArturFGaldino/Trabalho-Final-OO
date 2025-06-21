package Servicos;

public abstract class EspacosFisicos {
    public static int capacidade;
    public static String localizacao, tipo, equipamentosDisponiveis, disponibilidades;

    EspacosFisicos(int capacidade, String localizacao, String tipo, String equipamentosDisponiveis, String disponibilidades) {
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.tipo = tipo;
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

    public static String getTipo() {
        return tipo;
    }

    public static String getEquipamentosDisponiveis() {
        return equipamentosDisponiveis;
    }

    public static String getDisponibilidades() {
        return disponibilidades;
    }
    
}
