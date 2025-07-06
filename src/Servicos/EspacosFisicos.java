package Servicos;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import Entidades.Usuarios;
import Execoes.DiasExcedidosException;
import Entidades.Alunos;
import Execoes.JaReservouException;

public abstract class EspacosFisicos {
    private final int capacidade;
    private final String localizacao, equipamentosDisponiveis, disponibilidades;
    protected final String nome;
    protected final static Map<String, String> reservasPorHorario = new HashMap<>();
    protected final static Map<String, ArrayList<String>> reservasPorUsuario = new HashMap<>();
    protected static final Map<String, Map<String, String>> reservasPorEspaco = new HashMap<>();

    EspacosFisicos(String nome, int capacidade, String localizacao, String equipamentosDisponiveis,
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
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        String[] horas = {"08h", "10h", "12h", "14h", "16h"};

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
                botao.setBackground(new Color(220, 180, 180)); // Vermelho claro para indicar ocupado
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
                boolean condicao = podeReservarHorario(dia, usuarioLogado);
                boolean condicao2 = podeReservarHorario(dia, hora, usuarioLogado);
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
                botao.setText("Livre");
                botao.setBackground(new Color(200, 230, 250));
                reservasPorUsuario.get(usuarioLogado.getMatricula()).remove(horarioKey);
                reservasPorHorario.remove(horarioKey);
                reservasPorEspaco.get(this.nome).remove(horarioKey);
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
                                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                                                throw new JaReservouException("Você já tem uma reserva para esse horário.");
                                            } catch (JaReservouException erro) {
                                                JOptionPane.showMessageDialog(null, erro.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                // Verifica se a reserva pertence a este espaço
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
}
