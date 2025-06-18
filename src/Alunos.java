public class Alunos extends Usuarios {

    private String curso;
    private String semestreMatriculado;

    public Alunos(String nomeCompleto, String email, String telefone, String senha, String matricula, String curso) {
        super(nomeCompleto, email, telefone, senha, matricula);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public String getSemestreMatriculado() {
        semestreMatriculado = matricula.substring(3, 3);
        if (Integer.parseInt(semestreMatriculado) == 1) {
            semestreMatriculado = "1 semestre";
        } else if (Integer.parseInt(semestreMatriculado) == 2) {
            semestreMatriculado = "2 semestre";
        } else {
            semestreMatriculado = "matricula invalida";
        }
        return semestreMatriculado;
    }

}
