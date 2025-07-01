package Servicos;

public class Laboratorio extends EspacosFisicos {

    private static String nome;

    public Laboratorio(int capacidade, String localizacao, String equipamentosDisponiveis, String disponibilidades,
            String nome) {
        super(capacidade, localizacao, equipamentosDisponiveis, disponibilidades);
        this.nome = nome;
    }
}
