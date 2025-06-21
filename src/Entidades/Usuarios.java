package Entidades;

public class Usuarios {
    protected final String nomeCompleto;
    protected final String email;
    protected final String telefone;
    protected String senha;
    protected final String matricula;

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

    // SEMESTRE DE INGRESSO SO NA CLASSE DOS ALUNOS

    @Override
    public String toString() {
        String informacoes = "";
        informacoes += "NOME: " + nomeCompleto + "\n";
        informacoes += "MATRÍCULA: " + matricula + "\n";
        informacoes += "EMAIL: " + email + "\n";
        informacoes += "TELEFONE: " + telefone + "\n";
        return informacoes;
    }
}
