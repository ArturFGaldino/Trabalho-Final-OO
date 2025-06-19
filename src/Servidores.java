import java.time.LocalDate;

public class Servidores extends Usuarios {

    private String cargoExercido;
    private String departamento;

    public Servidores(String nomeCompleto, String email, String telefone, String senha, String matricula,String cargoExercido,String departamento) {
        super(nomeCompleto, email, telefone, senha, matricula);
        this.cargoExercido = cargoExercido;
        this.departamento = departamento;
    }

    public String getCargoExercido() {
        return cargoExercido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getAnoDeIngresso() {
        String ano = matricula.substring(0, 2);
        int anoAtual = LocalDate.now().getYear() % 100;
        if (Integer.parseInt(ano) > anoAtual) {
            ano = "19" + ano;
        } else {
            ano = "20" + ano;
        }
        return Integer.parseInt(ano);
    }

    public String toString() {
        String informacoes = "";
        informacoes += "NOME: " + nomeCompleto + "\n";
        informacoes += "MATRÍCULA: " + matricula + "\n";
        informacoes += "EMAIL: " + email + "\n";
        informacoes += "TELEFONE: " + telefone + "\n";
        informacoes += "ANO DE INGRESSO: " + getAnoDeIngresso() + "\n";
        informacoes += "CARGO EXERCIDO: " + getCargoExercido() + "\n";
        informacoes += "DEPARTAMENTO: " + getDepartamento() + "\n";
        return informacoes;
    }


}