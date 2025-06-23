import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Entidades.*;

public class Main {
    public static String nome, email, telefone, matricula;
    public static String senha;
    public static String tempSenha;
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    public static int continuar = 1;
    public static String tempLogin;
    public static Usuarios usuarioLogado = null;

    public static void main(String[] args) {

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
                        mostrarEspacosFisicos();
                        break;
                    case 1:
                        cadastro();
                        break;
                }
            }
        }while (continuar == 1);

    }

    //LOGIN OU CADASTRO
    private static int loginCadastro() {
        Object[] opcoes001 = {"LOGIN", "CADASTRO", "CANCELAR"};
        return JOptionPane.showOptionDialog(null,
                "",
                "LOGIN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes001,
                opcoes001[0]
        );
    }

    //CADASTRO
    private static void cadastro() {
        int parar = 0;
        nome = JOptionPane.showInputDialog("NOME COMPLETO");
        if (Usuarios.validaNull(nome)) {
            return;
        }
        while (Usuarios.validaEmpty(nome)) {
            nome = JOptionPane.showInputDialog("NOME INVÁLIDO. DIGITE CORRETAMENTE:");
        }
        telefone = JOptionPane.showInputDialog("TELEFONE");
        if (Usuarios.validaNull(telefone)) {
            return;
        }
        while (!Usuarios.validaTelefone(telefone)) {
            telefone = JOptionPane.showInputDialog("TELEFONE INVÁLIDO. DIGITE CORRETAMENTE: ");
        }
        matricula = JOptionPane.showInputDialog("MATRÍCULA");
        if (Usuarios.validaNull(matricula)) {
            return;
        }
        while (!Usuarios.validaMatricula(matricula)) {
            matricula = JOptionPane.showInputDialog("MATRÍCULA INVÁLIDA. DIGITE CORRETAMENTE: ");
        }
        senha = JOptionPane.showInputDialog("DIGITE SUA SENHA");
        if (Usuarios.validaNull(senha)) {
            return;
        }
        tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
        if (Usuarios.validaNull(tempSenha)) {
            return;
        }
        while (!senha.equals(tempSenha) || Usuarios.validaEmpty(senha)) {
            senha = JOptionPane.showInputDialog("DIGITE NOVAMENTE SUA SENHA");
            tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
        }
        do {
            Object[] opcoes = {"ALUNO", "PROFESSOR", "SERVIDOR", "CANCELAR"};
            boolean emailValido = false;
            int relacao = JOptionPane.showOptionDialog(null,
                    "INFORME O TIPO DE RELAÇÃO COM A INSTITUIÇÃO",
                    "RELAÇÃO COM A INSTITUIÇÃO",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
            if (relacao == 3 || relacao == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.",
                        " ",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                switch (relacao) {
                    case 0:
                        String curso = JOptionPane.showInputDialog("CURSO: ");
                        if (Usuarios.validaNull(curso)) {
                            return;
                        }
                        while (Usuarios.validaEmpty(curso)) {
                            curso = JOptionPane.showInputDialog("DIGITE UM CURSO VÁLIDO: ");
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
                        if (Usuarios.validaNull(cargoAcademico)) {
                            return;
                        }
                        while (Usuarios.validaEmpty(cargoAcademico)) {
                            cargoAcademico = JOptionPane.showInputDialog("DIGITE UM CARGO ACADEMICO VÁLIDO: ");
                        }
                        String cargoMinistrado = JOptionPane.showInputDialog("CARGO MINISTRADO: ");
                        if (Usuarios.validaNull(cargoMinistrado)) {
                            return;
                        }
                        while (Usuarios.validaEmpty(cargoMinistrado)) {
                            cargoMinistrado = JOptionPane.showInputDialog("DIGITE UM CARGO MINISTRADO VÁLIDO: ");
                        }
                        do {
                            email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                            Professores professorTemp = new Professores(nome, email, telefone, senha, matricula, cargoAcademico, cargoMinistrado);
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
                        if (Usuarios.validaNull(cargoExercido)) {
                            return;
                        }
                        while (Usuarios.validaEmpty(cargoExercido)) {
                            cargoExercido = JOptionPane.showInputDialog("DIGITE UM CARGO EXERCÍDO VÁLIDO: ");
                        }
                        String departamento = JOptionPane.showInputDialog("DEPARTAMENTO: ");
                        if (Usuarios.validaNull(departamento)) {
                            return;
                        }
                        while (Usuarios.validaEmpty(departamento)) {
                            departamento = JOptionPane.showInputDialog("DIGITE UM DEPARTAMENTO VÁLIDO: ");
                        }
                        do {
                            email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                            Servidores servidorTemp = new Servidores(nome, email, telefone, senha, matricula, cargoExercido, departamento);
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

    //VALIDA LOGIN
    private static void login() {
        boolean logado = false;
        while (!logado) {
            tempLogin = JOptionPane.showInputDialog("MATRÍCULA: ");
            if (Usuarios.validaNull(tempLogin)) {
                return;
            }
            tempSenha = JOptionPane.showInputDialog("SENHA: ");
            if (Usuarios.validaNull(tempSenha)) {
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
        Object[] opcoes1 = {"Laboratório", "Sala de aula", "Auditório"};
        int escolhaEspacoFisico = JOptionPane.showOptionDialog(
                null,
                "QUAL ESPAÇO VOCÊ DESEJA SELECIONAR",
                "Espaços disponíveis",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes1,
                opcoes1[0]
        );

        mostrarGradeHoraria();

    }

    public static void mostrarGradeHoraria() {
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        String[] horas = {"08h", "10h", "14h", "16h"};

        JFrame frame = new JFrame("Hórarios disponíveis");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(horas.length + 1, dias.length + 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font fonte = new Font("Segoe UI", Font.PLAIN, 14);

        painel.add(new JLabel("")); // Canto superior vazio

        // Cabeçalhos dos dias
        for (String dia : dias) {
            JLabel labelDia = new JLabel(dia, SwingConstants.CENTER);
            labelDia.setFont(new Font("Segoe UI", Font.BOLD, 14));
            painel.add(labelDia);
        }

        // Linhas de horários + botões
        for (String hora : horas) {
            JLabel labelHora = new JLabel(hora, SwingConstants.CENTER);
            labelHora.setFont(new Font("Segoe UI", Font.BOLD, 14));
            painel.add(labelHora);

            for (String dia : dias) {
                JButton botao = new JButton("Livre");
                botao.setFont(fonte);
                botao.setBackground(new Color(200, 230, 250));
                botao.setFocusPainted(false);

                // Evento ao clicar
                botao.addActionListener(e -> {
                    String matricula = JOptionPane.showInputDialog(frame, "Digite sua matrícula:");
                    if (matricula != null && !matricula.isBlank()) {
                        botao.setText("Alocado");
                        botao.setBackground(new Color(180, 220, 180));
                        botao.setEnabled(false);
                        JOptionPane.showMessageDialog(null,
                                "Você foi alocado para " + dia + " às " + hora +
                                        "\nMatrícula: " + matricula,
                                "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                painel.add(botao);
            }
        }

        frame.add(painel);
        frame.setVisible(true);
    }


}




