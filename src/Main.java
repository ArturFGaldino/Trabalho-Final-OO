import javax.swing.*;
import java.util.ArrayList;
import Entidades.*;
import Servicos.*;

public class Main {
    public static String nome, email, telefone, matricula;
    public static String senha;
    public static String tempSenha;
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    public static int continuar = 1;
    public static int sair;
    public static String tempLogin;
    public static Usuarios usuarioLogado = null;
    public static Usuarios artur = new Alunos("Artur Fernandes Galdino", "241010923@aluno.unb.br", "61998658594", "1",
            "241010923", "Engenharia de Software");
    public static Usuarios pedro = new Alunos(
            "Pedro Augusto Macedo Del Castilo",
            "241025354@aluno.unb.br",
            "61082859745",
            "1",
            "241025354",
            "Engenharia de Software");
    public static String reservas = "";
    public static Laboratorio lab1 = new Laboratorio("Lab1", 100, "UED", "Projetor", "Disponível");
    public static Laboratorio lab2 = new Laboratorio("Lab2", 100, "UED", "Projetor", "Disponível");
    public static Auditorio MOCAP = new Auditorio("MOCAP", 100, "Bloco A", "Projetor", "Disponível");
    public static Auditorio auditorio = new Auditorio("auditorio", 100, "Bloco A", "Projetor", "Disponível");
    public static SalaDeAula salaI1 = new SalaDeAula("I1", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaI2 = new SalaDeAula("I2", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaI3 = new SalaDeAula("I3", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaI4 = new SalaDeAula("I4", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaI5 = new SalaDeAula("I5", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaS1 = new SalaDeAula("S1", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaS2 = new SalaDeAula("S2", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaS3 = new SalaDeAula("S3", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaS4 = new SalaDeAula("S4", 10, "UAC", "Projetor", "Disponível");
    public static SalaDeAula salaS5 = new SalaDeAula("S5", 10, "UAC", "Projetor", "Disponível");

    public static void main(String[] args) {
        listaUsuarios.add(artur);
        listaUsuarios.add(pedro);
        do {
            int opcao02 = loginCadastro();
            if (opcao02 == 2 || opcao02 == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null,
                        "Operação cancelada pelo usuário.",
                        " ",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                switch (opcao02) {
                    case 0:
                        login();
                        do {
                            mostrarEspacosFisicos();
                            sair = sair();
                        } while (sair == 0);
                        reservas = "";
                        usuarioLogado = null;
                        break;
                    case 1:
                        cadastro();
                        break;
                }
            }
        } while (continuar == 1);

    }

    private static int sair() {
        Object[] opcoes001 = { "CONTINUAR", "SAIR" };
        return JOptionPane.showOptionDialog(null,
                "",
                "SAIR",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes001,
                opcoes001[0]);
    }

    // LOGIN OU CADASTRO
    private static int loginCadastro() {
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

    // CADASTRO
    private static void cadastro() {
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

    // VALIDA LOGIN
    private static void login() {
        boolean logado = false;
        while (!logado) {
            tempLogin = JOptionPane.showInputDialog("MATRÍCULA: ");
            if (tempLogin == null) {
                return;
            }
            tempSenha = JOptionPane.showInputDialog("SENHA: ");
            if (tempSenha == null) {
                return;
            }
            for (Usuarios usuario : listaUsuarios) {
                if (usuario.getMatricula().equals(tempLogin) && usuario.getSenha().equals(tempSenha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo, " + usuario.getNomeCompleto());
                    usuarioLogado = usuario;
                    logado = true;
                    break;
                }
            }
            if (!logado) {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos.");
            }
        }
    }

    public static void mostrarEspacosFisicos() {
        Object[] opcoes1 = { "Laboratório", "Sala de aula", "Auditório" };
        int escolhaEspacoFisico = JOptionPane.showOptionDialog(
                null,
                "QUAL ESPAÇO VOCÊ DESEJA SELECIONAR",
                "Espaços disponíveis",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes1,
                opcoes1[0]);

        switch (escolhaEspacoFisico) {
            case 0:
                Object[] opcoesLab = { "Lab1", "Lab2" };
                int escolherLaboratorio = JOptionPane.showOptionDialog(
                        null,
                        "Escolha um laboratório",
                        "Laboratórios disponíveis",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesLab,
                        opcoesLab[0]);
                switch (escolherLaboratorio) {
                    case 0:
                        lab1.mostrarGradeHoraria(usuarioLogado);
                        reservas = "";
                        reservas += lab1.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 1:
                        reservas = "";
                        lab2.mostrarGradeHoraria(usuarioLogado);
                        reservas += lab2.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                Object[] opcoesSala = { "I1", "I2", "I3", "I4", "I5", "S1", "S2", "S3",
                        "S4", "S5" };
                int escolherSala = JOptionPane.showOptionDialog(
                        null,
                        "Escolha uma sala",
                        "Salas disponíveis",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesSala,
                        opcoesSala[0]);
                switch (escolherSala) {
                    case 0:
                        reservas = "";
                        salaI1.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI1.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 1:
                        reservas = "";
                        salaI2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI2.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaI2.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 2:
                        reservas = "";
                        salaI3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI3.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaI3.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 3:
                        reservas = "";
                        salaI4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI4.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaI4.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 4:
                        reservas = "";
                        salaI5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI5.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaI5.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 5:
                        reservas = "";
                        salaS1.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS1.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaS1.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 6:
                        reservas = "";
                        salaS2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS2.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaS2.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 7:
                        reservas = "";
                        salaS3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS3.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaS3.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 8:
                        reservas = "";
                        salaS4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS4.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaS4.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 9:
                        reservas = "";
                        salaS5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS5.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        salaS5.mostrarGradeHoraria(usuarioLogado);
                        break;
                    default:
                        break;
                }

                break;
            case 2:
                Object[] opcoesAudi = { "Auditório principal", "Mocap" };
                int escolherAudi = JOptionPane.showOptionDialog(
                        null,
                        "Escolha um auditório",
                        "Auditórios disponíveis",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesAudi,
                        opcoesAudi[0]);
                switch (escolherAudi) {
                    case 0:
                        reservas = "";
                        auditorio.mostrarGradeHoraria(usuarioLogado);
                        reservas += auditorio.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        auditorio.mostrarGradeHoraria(usuarioLogado);
                        break;
                    case 1:
                        reservas = "";
                        MOCAP.mostrarGradeHoraria(usuarioLogado);
                        reservas += MOCAP.exibirReservas(usuarioLogado.getMatricula(), reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        MOCAP.mostrarGradeHoraria(usuarioLogado);
                    default:
                        break;
                }

                break;
        }
    }
}
