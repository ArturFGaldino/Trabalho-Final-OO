package Servicos;

import java.util.Map;

public class Auditorio extends EspacosFisicos{
    public Auditorio(String nome, int capacidade, String localizacao, String equipamentosDisponiveis, String disponibilidades) {
        super(nome, capacidade, localizacao, equipamentosDisponiveis, disponibilidades);
    }
    public String montaReservas(Auditorio aud){
        StringBuilder sb = new StringBuilder();
        sb.append(aud.getNome()).append(": \n");
        Map<String, String> esp = reservasPorEspaco.get(aud.getNome());
        for (Map.Entry<String, String> map:  esp.entrySet()){
            sb.append(map.getKey()).append(map.getValue());
        }
        return sb.toString();
    }
}
