package Entidades;
import java.time.LocalDate;

public class Alunos extends Usuarios {

    private final String curso;

    public Alunos(String nomeCompleto, String email, String telefone, String senha, String matricula, String curso) {
        super(nomeCompleto, email, telefone, senha, matricula);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public String getSemestreMatriculado() {
        String semestreMatriculado = matricula.substring(2, 3);
        if (Integer.parseInt(semestreMatriculado) == 1) {
            semestreMatriculado = "1 semestre";
        } else if (Integer.parseInt(semestreMatriculado) == 2) {
            semestreMatriculado = "2 semestre";
        } else {
            semestreMatriculado = "matricula invalida";
        }
        return semestreMatriculado;
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

    @Override
    public String toString() {
        String informacoes = "";
        informacoes += "NOME: " + nomeCompleto + "\n";
        informacoes += "MATRÍCULA: " + matricula + "\n";
        informacoes += "EMAIL: " + email + "\n";
        informacoes += "TELEFONE: " + telefone + "\n";
        informacoes += "ANO DE INGRESSO: " + getAnoDeIngresso() + "\n";
        informacoes += "CURSO: " + curso + "\n";
        return informacoes;
    }

    @Override
    public boolean validaEmail(String matricula, String email){
        return email.contains(matricula) && email.contains("@aluno.unb.br");
    }
}
