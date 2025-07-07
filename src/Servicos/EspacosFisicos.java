package Servicos;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import Entidades.Usuarios;
import Excecoes.DiasExcedidosException;
import Entidades.Alunos;
import Excecoes.JaReservouException;

public abstract class EspacosFisicos {
    protected final int capacidade;
    protected final String localizacao, equipamentosDisponiveis, disponibilidades;
    protected final String nome;
    protected final static Map<String, String> reservasPorHorario = new HashMap<>();
    protected final static Map<String, ArrayList<String>> reservasPorUsuario = new HashMap<>();
    protected static final Map<String, Map<String, String>> reservasPorEspaco = new HashMap<>();

    protected EspacosFisicos(String nome, int capacidade, String localizacao, String equipamentosDisponiveis,
            String disponibilidades) {
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.equipamentosDisponiveis = equipamentosDisponiveis;
        this.disponibilidades = disponibilidades;
        this.nome = nome;
    }

    // Gets
    public int getCapacidade() {
        return capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getEquipamentosDisponiveis() {
        return equipamentosDisponiveis;
    }

    public String getDisponibilidades() {
        return disponibilidades;
    }

    public String getNome() {
        return nome;
    }

    public void mostrarGradeHoraria(Usuarios usuarioLogado) {
        // Criação do Map dos horarios
        String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta" };
        String[] horas = { "08h", "10h", "12h", "14h", "16h" };

        if (!reservasPorEspaco.containsKey(this.nome)) {
            reservasPorEspaco.put(this.nome, new HashMap<>());
        }
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Horários disponíveis - " + getNome());
        dialog.setSize(700, 450);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(horas.length + 1, dias.length + 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font fonte = new Font("Segoe UI", Font.PLAIN, 14);

        painel.add(new JLabel(""));

        for (String dia : dias) {
            JLabel labelDia = new JLabel(dia, SwingConstants.CENTER);
            labelDia.setFont(new Font("Segoe UI", Font.BOLD, 14));
            painel.add(labelDia);
        }

        for (String hora : horas) {
            JLabel labelHora = new JLabel(hora, SwingConstants.CENTER);
            labelHora.setFont(new Font("Segoe UI", Font.BOLD, 14));
            painel.add(labelHora);

            for (String dia : dias) {
                JButton botao = botao(hora, dia, fonte, usuarioLogado);
                painel.add(botao);
            }
        }
        // Chama o nmetodo para fazer o botao
        JButton btnFinalizar = new JButton("Finalizar Reservas");
        btnFinalizar.addActionListener(e -> dialog.dispose());

        dialog.add(painel, BorderLayout.CENTER);
        dialog.add(btnFinalizar, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JButton botao(String hora, String dia, Font fonte, Usuarios usuarioLogado) {
        JButton botao = new JButton("Livre");
        botao.setFont(fonte);
        botao.setBackground(new Color(200, 230, 250));
        botao.setFocusPainted(false);
        String horarioKey = dia + " às " + hora;
        // Verifica se o horário já está reservado
        if (reservasPorEspaco.get(this.nome).containsKey(horarioKey)) {
            String matriculaReserva = reservasPorEspaco.get(this.nome).get(horarioKey);
            if (matriculaReserva.equals(usuarioLogado.getMatricula())) {
                botao.setText("Minha Reserva");
                botao.setBackground(new Color(180, 220, 180));
            } else {
                botao.setText("Reservado");
                botao.setBackground(new Color(220, 180, 180));
                botao.setEnabled(false); // Desabilita o botão se já estiver reservado
            }
        }
        botao.addActionListener(e -> {
            if (botao.getText().equals("Livre")) {
                botao.setText("Minha Reserva");
                botao.setBackground(new Color(180, 220, 180));

                if (!reservasPorUsuario.containsKey(usuarioLogado.getMatricula())) {
                    reservasPorUsuario.put(usuarioLogado.getMatricula(), new ArrayList<>());
                }
                boolean condicao = podeReservarHorario(dia, usuarioLogado);// Verifica se é aluno
                boolean condicao2 = podeReservarHorario(dia, hora, usuarioLogado);
                // verfica se tem alguma reserva nesse horario
                if (condicao && condicao2) {
                    reservasPorEspaco.get(this.nome).put(horarioKey, usuarioLogado.getMatricula());
                    reservasPorUsuario.get(usuarioLogado.getMatricula()).add(horarioKey);
                    JOptionPane.showMessageDialog(null,
                            "Você foi alocado para " + dia + " às " + hora,
                            "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    botao.setText("Livre");
                    botao.setBackground(new Color(200, 230, 250));
                }
                
            } else if (botao.getText().equals("Minha Reserva")) {
                Object[] opcoes = { "Sim", "Não" };
                int opcao = JOptionPane.showOptionDialog(null,
                        "Deseja cancelar sua reserva?",
                        "Cancelar Reserva",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);
                if (opcao == 0) {
                    botao.setText("Livre");
                    botao.setBackground(new Color(200, 230, 250));
                    reservasPorUsuario.get(usuarioLogado.getMatricula()).remove(horarioKey);
                    reservasPorHorario.remove(horarioKey);
                    reservasPorEspaco.get(this.nome).remove(horarioKey);
                }
            }
        });
        return botao;
    }

    protected boolean podeReservarHorario(String dia, Usuarios usuarioLogado) {
        if (usuarioLogado instanceof Alunos) {
            List<String> reservas = reservasPorUsuario.get(usuarioLogado.getMatricula());

            if (reservas != null && !reservas.isEmpty()) {
                List<String> diasSemana = Arrays.asList("Segunda", "Terça", "Quarta", "Quinta", "Sexta");
                int diaAtualIndex = diasSemana.indexOf(dia);
                for (String reserva : reservas) {
                    for (String diaReservado : diasSemana) {
                        if (reserva.contains(diaReservado)) {
                            int indexReservado = diasSemana.indexOf(diaReservado);
                            if (Math.abs(diaAtualIndex - indexReservado) == 1) {
                                try {
                                    throw new DiasExcedidosException("Alunos não podem reservar em dias consecutivos.");
                                } catch (DiasExcedidosException erro) {
                                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    protected boolean podeReservarHorario(String dia, String hora, Usuarios usuarioLogado) {
        if (usuarioLogado instanceof Alunos) {
            List<String> reservas = reservasPorUsuario.get(usuarioLogado.getMatricula());

            if (reservas != null && !reservas.isEmpty()) {
                List<String> diasSemana = Arrays.asList("Segunda", "Terça", "Quarta", "Quinta", "Sexta");
                List<String> horarios = Arrays.asList("08h", "10h", "12h", "14h", "16h");
                int diaAtualIndex = diasSemana.indexOf(dia);
                int horaAtualIndex = horarios.indexOf(hora);
                for (String reserva : reservas) {
                    for (String diaReservado : diasSemana) {
                        if (reserva.contains(diaReservado)) {
                            int indexReservado = diasSemana.indexOf(diaReservado);
                            if (Math.abs(diaAtualIndex - indexReservado) == 0) {
                                for (String horaReservada : horarios) {
                                    if (reserva.contains(horaReservada)) {
                                        int indexReservadoHorario = horarios.indexOf(horaReservada);
                                        if (Math.abs(horaAtualIndex - indexReservadoHorario) == 0) {
                                            try {
                                                throw new JaReservouException(
                                                        "Você já tem uma reserva para esse horário.");
                                            } catch (JaReservouException erro) {
                                                JOptionPane.showMessageDialog(null, erro.getMessage(), "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public String exibirReservas(Usuarios usuarioLogado, String reservas) {
        StringBuilder sb = new StringBuilder();

        // Verifica se o usuário tem reservas no sistema
        if (reservasPorUsuario.containsKey(usuarioLogado.getMatricula())) {
            ArrayList<String> reservasDoUsuario = reservasPorUsuario.get(usuarioLogado.getMatricula());
            if (reservasPorEspaco.containsKey(this.nome)) {
                if (!reservas.contains(this.nome)) {
                    sb.append("\n").append(this.nome).append("\n");
                }
            }
            // Filtra apenas as reservas deste espaço físico
            for (String horario : reservasDoUsuario) {
                if (reservasPorEspaco.containsKey(this.nome) &&
                        reservasPorEspaco.get(this.nome).containsKey(horario) &&
                        reservasPorEspaco.get(this.nome).get(horario).equals(usuarioLogado.getMatricula())) {
                    sb.append(horario).append("\n");
                }
            }
            reservas = sb.toString();
        }
        return reservas;
    }

    public String exibirReservas(EspacosFisicos espacosFisicos) {
        StringBuilder sb = new StringBuilder();

        if (!reservasPorEspaco.containsKey(this.nome) || reservasPorEspaco.get(this.nome).isEmpty()) {
            return sb.append(this.nome).append(": \nSem reservas\n").toString();
        }

        // Mapa temporário para agrupar horários por matrícula
        Map<String, List<String>> reservasPorMatricula = new HashMap<>();

        // Agrupa os horários por matrícula
        for (Map.Entry<String, String> entry : reservasPorEspaco.get(this.nome).entrySet()) {
            String horario = entry.getKey();
            String matricula = entry.getValue();

            if (!reservasPorMatricula.containsKey(matricula)) {
                reservasPorMatricula.put(matricula, new ArrayList<>());
            }
            reservasPorMatricula.get(matricula).add(horario);
        }

        sb.append(this.nome).append(":\n").append("Capacidade - ").append(this.capacidade).append("\n");

        if ((espacosFisicos instanceof SalaDeAula) || (espacosFisicos instanceof Laboratorio)) {
            sb.append(espacosFisicos.getEquipamentosDisponiveis()).append("\n");
        }
        // Para cada matrícula, mostra os horários reservados
        for (Map.Entry<String, List<String>> entry : reservasPorMatricula.entrySet()) {
            sb.append("Usuário: ").append(entry.getKey()).append("\n");
            sb.append("Horários reservados:\n");

            for (String horario : entry.getValue()) {
                sb.append("- ").append(horario).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


}
