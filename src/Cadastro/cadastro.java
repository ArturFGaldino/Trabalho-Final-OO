package Cadastro;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entidades.*;

public class cadastro {

    public static int loginCadastro(){
        
        Object[] opcoes001 = { "LOGIN", "CADASTRO", "CANCELAR" };
        return JOptionPane.showOptionDialog(null,
                "",
                "LOGIN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes001,
                opcoes001[0]);
    
    }
    public static void cadastroUsuario(ArrayList<Usuarios> listaUsuarios){
        String nome, telefone, matricula, senha, tempSenha, email;


        int parar = 0;
            nome = JOptionPane.showInputDialog("NOME COMPLETO");
            if (nome == null) {
                return;
            }
            while (nome.isEmpty()) {
                nome = JOptionPane.showInputDialog("NOME INVÁLIDO. DIGITE CORRETAMENTE:");
                if (nome == null) {
                    return;
                }
            }
            telefone = JOptionPane.showInputDialog("TELEFONE");
            if (telefone == null) {
                return;
            }
            while (!Usuarios.validaTelefone(telefone)) {
                telefone = JOptionPane.showInputDialog("TELEFONE INVÁLIDO. DIGITE CORRETAMENTE: ");
                if (telefone == null) {
                    return;
                }
            }
            matricula = JOptionPane.showInputDialog("MATRÍCULA");
            if (matricula == null) {
                return;
            }
            while (!Usuarios.validaMatricula(matricula)) {
                matricula = JOptionPane.showInputDialog("MATRÍCULA INVÁLIDA. DIGITE CORRETAMENTE: ");
                if (matricula == null) {
                    return;
                }
            }
            senha = JOptionPane.showInputDialog("DIGITE SUA SENHA");
            if (senha == null) {
                return;
            }
            tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
            if (tempSenha == null) {
                return;
            }
            while (!senha.equals(tempSenha) || senha.isEmpty()) {
                senha = JOptionPane.showInputDialog("DIGITE NOVAMENTE SUA SENHA");
                if (senha == null) {
                    return;
                }
                tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
                if (tempSenha == null) {
                    return;
                }
            }
            do {
                Object[] opcoes = { "ALUNO", "PROFESSOR", "SERVIDOR", "CANCELAR" };
                boolean emailValido = false;
                int relacao = JOptionPane.showOptionDialog(null,
                        "INFORME O TIPO DE RELAÇÃO COM A INSTITUIÇÃO",
                        "RELAÇÃO COM A INSTITUIÇÃO",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);
                if (relacao == 3 || relacao == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.",
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    switch (relacao) {
                        case 0:
                            String curso = JOptionPane.showInputDialog("CURSO: ");
                            if (curso == null) {
                                return;
                            }
                            while (curso.isEmpty()) {
                                curso = JOptionPane.showInputDialog("DIGITE UM CURSO VÁLIDO: ");
                                if (curso == null) {
                                    return;
                                }
                            }
                            do {
                                email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                                Alunos alunoTemp = new Alunos(nome, email, telefone, senha, matricula, curso);
                                if (alunoTemp.validaEmail(matricula, email)) {
                                    listaUsuarios.add(alunoTemp);
                                    emailValido = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO PARA ALUNO.");
                                }
                            } while (!emailValido);
                            break;
                        case 1:
                            String cargoAcademico = JOptionPane.showInputDialog("CARGO ACADEMICO: ");
                            if (cargoAcademico == null) {
                                return;
                            }
                            while (cargoAcademico.isEmpty()) {
                                cargoAcademico = JOptionPane.showInputDialog("DIGITE UM CARGO ACADEMICO VÁLIDO: ");
                                if (cargoAcademico == null) {
                                    return;
                                }
                            }
                            String cargoMinistrado = JOptionPane.showInputDialog("CARGO MINISTRADO: ");
                            if (cargoMinistrado == null) {
                                return;
                            }
                            while (cargoMinistrado.isEmpty()) {
                                cargoMinistrado = JOptionPane.showInputDialog("DIGITE UM CARGO MINISTRADO VÁLIDO: ");
                                if (cargoMinistrado == null) {
                                    return;
                                }
                            }
                            do {
                                email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                                Professores professorTemp = new Professores(nome, email, telefone, senha, matricula,
                                        cargoAcademico, cargoMinistrado);
                                if (professorTemp.validaEmail(matricula, email)) {
                                    listaUsuarios.add(professorTemp);
                                    emailValido = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO.");
                                }
                            } while (!emailValido);
                            break;
                        case 2:
                            String cargoExercido = JOptionPane.showInputDialog("CARGO EXERCIDO: ");
                            if (cargoExercido == null) {
                                return;
                            }
                            while (cargoExercido.isEmpty()) {
                                cargoExercido = JOptionPane.showInputDialog("DIGITE UM CARGO EXERCÍDO VÁLIDO: ");
                                if (cargoExercido == null) {
                                    return;
                                }
                            }
                            String departamento = JOptionPane.showInputDialog("DEPARTAMENTO: ");
                            if (departamento == null) {
                                return;
                            }
                            while (departamento.isEmpty()) {
                                departamento = JOptionPane.showInputDialog("DIGITE UM DEPARTAMENTO VÁLIDO: ");
                                if (departamento == null) {
                                    return;
                                }
                            }
                            do {
                                email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                                Servidores servidorTemp = new Servidores(nome, email, telefone, senha, matricula,
                                        cargoExercido, departamento);
                                if (servidorTemp.validaEmail(matricula, email)) {
                                    listaUsuarios.add(servidorTemp);
                                    emailValido = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO.");
                                }
                            } while (!emailValido);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
                            parar = 1;
                            break;
                    }
                }
            } while (parar == 1);
    }
        
}
