public class Servidores extends Usuarios {

    private final String cargoExercido;
    private final String departamento;

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


    public String toString() {
        String informacoes = "";
        informacoes += "NOME: " + nomeCompleto + "\n";
        informacoes += "MATRÍCULA: " + matricula + "\n";
        informacoes += "EMAIL: " + email + "\n";
        informacoes += "TELEFONE: " + telefone + "\n";
        informacoes += "ANO DE INGRESSO: " + getAnoDeIngresso() + "\n";
        informacoes += "CARGO EXERCIDO: " + cargoExercido + "\n";
        informacoes += "DEPARTAMENTO: " + departamento + "\n";
        return informacoes;
    }


}