import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import Entidades.*;

public class Main {
    public static String nome, email, telefone, matricula;
    public static String senha;
    public static String tempSenha;
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    public static int continuar = 1;
    public static String tempLogin;
    public static Map<String, ArrayList<String>> horariosReservados = new HashMap<>();
    public static Usuarios usuarioLogado = null;

    // testes
    // public static Usuarios usuarioLogado = new
    // Alunos("Aluno","1","1","1","1","eng");
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
        } while (continuar == 1);

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

        String espacoSelecionado = opcoes1[escolhaEspacoFisico].toString();
        mostrarGradeHoraria(espacoSelecionado);
    }

    public static void mostrarGradeHoraria(String espacoSelecionado) {
        String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta" };
        String[] horas = { "08h", "10h", "12h", "14h", "16h" };

        // Cria um JDialog MODAL (bloqueia interação com outras janelas até fechar)
        JDialog dialog = new JDialog();
        dialog.setModal(true); // Faz com que o código espere aqui até fechar
        dialog.setTitle("Horários disponíveis - " + espacoSelecionado);
        dialog.setSize(700, 450);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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
                JButton botao = getJButton(hora, dia, fonte);
                painel.add(botao);
            }
        }

        // Botão para finalizar reservas
        JButton btnFinalizar = new JButton("Finalizar Reservas");
        btnFinalizar.addActionListener(e -> {
            dialog.dispose(); // Fecha a janela de grade horária
        });

        // Adicionar os componentes à janela
        dialog.add(painel, BorderLayout.CENTER);
        dialog.add(btnFinalizar, BorderLayout.SOUTH);

        dialog.setVisible(true);
        String reservas = exibirReservas();// Chama o exibir reservas mostrando as reservas do usuario
        JOptionPane.showMessageDialog(null, reservas);
    }

    private static JButton getJButton(String hora, String dia, Font fonte) {
        JButton botao = new JButton("Livre");
        botao.setFont(fonte);
        botao.setBackground(new Color(200, 230, 250));
        botao.setFocusPainted(false);

        // Evento ao clicar
        botao.addActionListener(e -> {
            botao.setText("Alocado");
            botao.setBackground(new Color(180, 220, 180));
            botao.setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "Você foi alocado para " + dia + " às " + hora,
                    "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            // Adicionar ao mapa de horários reservados
            String horarioReservado = dia + " às " + hora;
            if (!horariosReservados.containsKey(usuarioLogado.getMatricula())) {
                horariosReservados.put(usuarioLogado.getMatricula(), new ArrayList<>());
            }
            boolean condicao = podeReservarHorario(dia, hora);
            if (condicao == true) {
                horariosReservados.get(usuarioLogado.getMatricula()).add(horarioReservado);
            } else {
                botao.setText("Livre");
                botao.setFont(fonte);
                botao.setBackground(new Color(200, 230, 250));
                botao.setFocusPainted(false);

            }

        });
        return botao;
    }

    private static String exibirReservas() {
        matricula = JOptionPane.showInputDialog(null, "Digite sua matrícula:");
        if (horariosReservados.containsKey(matricula)) {
            StringBuilder reservas = new StringBuilder("SUAS RESERVAS:\n\n");
            for (String hr : horariosReservados.get(matricula)) {
                reservas.append("- ").append(hr).append("\n");
            }
            reservas.insert(0, "Resumo de reservas para matrícula: " + matricula + "\n\n");

            return reservas.toString();
        } else {
            return "NENHUMA RESERVA ESCOLHIDA";
        }
    }

    private static boolean podeReservarHorario(String dia, String hora) {
        // verifica se o usuario é aluno
        if (usuarioLogado instanceof Alunos) {
            List<String> reservas = horariosReservados.get(usuarioLogado.getMatricula());

            if (reservas != null && !reservas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alunos só podem reservar um horário.");
                return false;
            }
            // Verifica se é um horário consecutivo
            int horaInt = Integer.parseInt(hora.replace("h", ""));
            for (Object reserva : reservas != null ? reservas : new ArrayList<>()) {
                if (((String) reserva).contains(dia)) {
                    int horaReservada = Integer.parseInt(((String) reserva).replaceAll("[^0-9]", ""));
                    if (Math.abs(horaReservada - horaInt) == 2) {
                        JOptionPane.showMessageDialog(null, "Alunos não podem reservar horários consecutivos.");
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
