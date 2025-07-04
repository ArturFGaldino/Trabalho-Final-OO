package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Servicos.EspacosFisicos;

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
        return informacoes;
    }

    public static boolean validaTelefone(String telefone) {
        if (telefone.length() != 11) {
            return false;
        }
        for (int i = 0; i < telefone.length(); i++) {
            if (!Character.isDigit(telefone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validaMatricula(String matricula) {
        if (matricula.length() != 9) {
            return false;
        }
        for (int i = 0; i < matricula.length(); i++) {
            if (!Character.isDigit(matricula.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean validaEmail(String matricula, String email) {
        return email.contains(matricula) && email.contains("@unb.br");
    }

    public String exibirReservasUsuario(Usuarios usuarioLogado, String nome,
            Map<String, ArrayList<String>> reservasPorUsuario) {
        if (reservasPorUsuario.containsKey(usuarioLogado.getMatricula())) {
            StringBuilder reservas = new StringBuilder("SUAS RESERVAS:\n\n" + nome + "\n");
            for (String hr : reservasPorUsuario.get(usuarioLogado.getMatricula())) {
                reservas.append("- ").append(hr).append("\n");
            }
            reservas.insert(0, "Resumo de reservas para matrícula: " + usuarioLogado.getMatricula() + "\n\n");
            return reservas.toString();
        } else {
            return "NENHUMA RESERVA ESCOLHIDA";
        }
    }
}
