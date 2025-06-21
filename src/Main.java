import javax.swing.*;
import java.util.ArrayList;
import Entidades.*;

public class Main {
    public static String nome, email, telefone, matricula;
    public static String senha = "1";
    public static String tempSenha = "0";
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    public static int continuar = 1;
    public static void main(String[] args) {
        do {
            int opcao = loginCadastro();
            switch (opcao) {
                case 1:
                    login();
                    break;
                case 2:
                    cadastro();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida. Digite de novo uma opção válida");
                    break;
            }

        }while(continuar == 1);
    }

    //LOGIN OU CADASTRO
    private static int loginCadastro(){
        String str = JOptionPane.showInputDialog("""
                1-LOGIN
                2-SE CADASTRAR""");
        return Integer.parseInt(str);
    }

    //CADASTRO
    private static void cadastro(){
        int parar = 0;
        nome = JOptionPane.showInputDialog("NOME COMPLETO");
        email = JOptionPane.showInputDialog("EMAIL");
        telefone = JOptionPane.showInputDialog("TELEFONE");
        while(!validartelefone()){
            telefone = JOptionPane.showInputDialog("TELEFONE INVÁLIDO. DIGITE CORRETAMENTE: ");
        }
        matricula = JOptionPane.showInputDialog("MATRÍCULA");
        while(!validaMatricula(matricula)){
            matricula = JOptionPane.showInputDialog("MATRÍCULA INVÁLIDA. DIGITE CORRETAMENTE: ");
        }
        while (!senha.equals(tempSenha)) {
            senha = JOptionPane.showInputDialog("DIGITE SUA SENHA");
            tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
        }
        do{
            String relacao = JOptionPane.showInputDialog("""
                    INFORME O TIPO DE RELAÇÃO COM A INSTITUIÇÃO
                    1-ALUNO
                    2-PROFESSOR
                    3-SERVIDOR""");
            switch (relacao){
                case "1":
                    String curso = JOptionPane.showInputDialog("CURSO: ");
                    listaUsuarios.add(new Alunos(nome, email, telefone, senha, matricula, curso));
                    break;
                case "2":
                    String cargoAcademico = JOptionPane.showInputDialog("CARGO ACADEMICO: ");
                    String cargoMinistrado = JOptionPane.showInputDialog("CARGO MINISTRADO: ");
                    listaUsuarios.add(new Professores(nome, email, telefone, senha, matricula, cargoAcademico, cargoMinistrado));
                    break;
                case "3":
                    String cargoExercido = JOptionPane.showInputDialog("CARGO EXERCIDO: ");
                    String departamento = JOptionPane.showInputDialog("DEPARTAMENTO: ");
                    listaUsuarios.add(new Servidores(nome, email, telefone, senha, matricula, cargoExercido, departamento));
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
                    parar = 1;
                    break;
            }
        }while(parar == 1);
    }

    //VALIDA LOGIN
    private static void login(){
        boolean logado = false;
        while (!logado) {
            String tempLogin = JOptionPane.showInputDialog("MATRÍCULA: ");
            tempSenha = JOptionPane.showInputDialog("SENHA: ");
            for (Usuarios usuario : listaUsuarios) {
                if (usuario.getMatricula().equals(tempLogin) && usuario.getSenha().equals(tempSenha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo, " + usuario.getNomeCompleto());
                    logado = true;
                    break;
                }
            }
            if (!logado) {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos.");
            }
        }
    }

    //VALIDA MATRICULA
    private static boolean validaMatricula(String matricula) {
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
    private static boolean validartelefone() {
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
}