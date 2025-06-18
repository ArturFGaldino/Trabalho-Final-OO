import java.time.LocalDate;

public class Usuarios {
    private String nomeCompleto;
    private String email;
    private String telefone;
    private String senha;
    private String matricula;
    private int anoDeIngresso;

    public Usuarios(String nomeCompleto, String email, String telefone, String senha, String matricula) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnoDeIngresso(){
        String ano = matricula.substring(0,2);
        int anoAtual = LocalDate.now().getYear() % 100;
        if(Integer.parseInt(ano) > anoAtual){
            ano = "19"+ano;
        }else{
            ano="20"+ano;
        }
        anoDeIngresso= Integer.parseInt(ano);
        return anoDeIngresso;
    }
    //SEMESTRE DE INGRESSO SO NA CLASSE DOS ALUNOS

    @Override
    public String toString() {
        String informacoes = "";
        informacoes+= "NOME: " + nomeCompleto + "\n";
        informacoes+= "MATRÍCULA: " + matricula + "\n";
        informacoes+= "EMAIL: " + email + "\n";
        informacoes+= "TELEFONE: " + telefone + "\n";
        informacoes+= "ANO DE INGRESSO: " + getAnoDeIngresso() + "\n";
        return informacoes;
    }
}
