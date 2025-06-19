public class Professores extends Usuarios {
    private final String cargoAcademico;
    private final String cargoMinistrado;

    public Professores(String nomeCompleto, String email, String telefone, String senha, String matricula,
            String cargoAcademico, String cargoMinistrado) {
        super(nomeCompleto, email, telefone, senha, matricula);
        this.cargoAcademico = cargoAcademico;
        this.cargoMinistrado = cargoMinistrado;
    }

    public String getCargoAcademico() {
        return cargoAcademico;
    }

    public String getCargoMinistrado() {
        return cargoMinistrado;
    }

    @Override
    public String toString() {
        String informacoes = "";
        informacoes += "NOME: " + nomeCompleto + "\n";
        informacoes += "MATRÍCULA: " + matricula + "\n";
        informacoes += "EMAIL: " + email + "\n";
        informacoes += "TELEFONE: " + telefone + "\n";
        informacoes += "ANO DE INGRESSO: " + getAnoDeIngresso() + "\n";
        informacoes += "CARGO ACADEMICO: " + cargoAcademico + "\n";
        informacoes += "CARGO MINISTRADO: " + cargoMinistrado + "\n";
        return informacoes;
    }
}
