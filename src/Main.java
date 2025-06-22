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
        telefone = JOptionPane.showInputDialog("TELEFONE");
        while(!Usuarios.validaTelefone(telefone)){
            telefone = JOptionPane.showInputDialog("TELEFONE INVÁLIDO. DIGITE CORRETAMENTE: ");
        }
        matricula = JOptionPane.showInputDialog("MATRÍCULA");
        while(!Usuarios.validaMatricula(matricula)){
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
            boolean emailValido = false;
            switch (relacao){
                case "1":
                    String curso = JOptionPane.showInputDialog("CURSO: ");
                    do{
                        email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                        Alunos alunoTemp = new Alunos(nome, email, telefone, senha, matricula, curso);
                        if(alunoTemp.validaEmail(matricula,email)){
                            listaUsuarios.add(alunoTemp);
                            emailValido = true;
                        }else{
                            JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO PARA ALUNO.");
                        }
                    }while(!emailValido);
                break;
                case "2":
                    do{
                        String cargoAcademico = JOptionPane.showInputDialog("CARGO ACADEMICO: ");
                        String cargoMinistrado = JOptionPane.showInputDialog("CARGO MINISTRADO: ");
                        email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                        Professores professorTemp = new Professores(nome, email, telefone, senha, matricula, cargoAcademico, cargoMinistrado);
                        if(professorTemp.validaEmail(matricula,email)){
                            listaUsuarios.add(professorTemp);
                            emailValido = true;
                        }else{
                            JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO.");
                        }
                    }while(!emailValido);
                break;
                case "3":
                    do{
                        String cargoExercido = JOptionPane.showInputDialog("CARGO EXERCIDO: ");
                        String departamento = JOptionPane.showInputDialog("DEPARTAMENTO: ");
                        email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                        Servidores servidorTemp = new Servidores(nome, email, telefone, senha, matricula, cargoExercido, departamento);
                        if(servidorTemp.validaEmail(matricula,email)){
                            listaUsuarios.add(servidorTemp);
                            emailValido = true;
                        }else{
                            JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO.");
                        }
                    }while(!emailValido);
                break;
                default:
                    JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
                    parar = 1;
                    break;
            }
        }while(parar == 1);
        for(Usuarios usuario : listaUsuarios){
            JOptionPane.showMessageDialog(null, usuario.toString());
        }
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
}