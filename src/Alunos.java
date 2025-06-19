public class Alunos extends Usuarios {

    private final String curso;
    private String semestreMatriculado;

    public Alunos(String nomeCompleto, String email, String telefone, String senha, String matricula, String curso) {
        super(nomeCompleto, email, telefone, senha, matricula);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public String getSemestreMatriculado() {
        semestreMatriculado = matricula.substring(2, 3);
        if (Integer.parseInt(semestreMatriculado) == 1) {
            semestreMatriculado = "1";
        } else if (Integer.parseInt(semestreMatriculado) == 2) {
            semestreMatriculado = "2";
        } else {
            semestreMatriculado = "matricula invalida";
        }
        return semestreMatriculado;
    }

    @Override
    public String toString() {
        String informacoes = "";
        informacoes += "NOME: " + nomeCompleto + "\n";
        informacoes += "MATRÍCULA: " + matricula + "\n";
        informacoes += "EMAIL: " + email + "\n";
        informacoes += "TELEFONE: " + telefone + "\n";
        informacoes += "ANO/SEMESTRE DE INGRESSO: " + getAnoDeIngresso() +"/" + semestreMatriculado + "\n";
        informacoes += "CURSO: " + curso + "\n";
        return informacoes;
    }

}
