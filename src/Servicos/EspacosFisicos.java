package Servicos;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import Entidades.Usuarios;
import Entidades.Alunos;

public abstract class EspacosFisicos {
    private final int capacidade;
    private final String localizacao, equipamentosDisponiveis, disponibilidades;
    protected final String nome;
    // private final static Map<String, ArrayList<String>> horariosReservados = new
    // HashMap<>();
    // Alteração: Mapa que armazena quem reservou cada horário (chave: horário,
    // valor: matrícula)
    protected final static Map<String, String> reservasPorHorario = new HashMap<>();
    // Alteração: Mapa que armazena as reservas por usuário (para exibição
    // individual)
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
                JButton botao = getJButton(hora, dia, fonte, usuarioLogado);
                painel.add(botao);
            }
        }

        JButton btnFinalizar = new JButton("Finalizar Reservas");
        btnFinalizar.addActionListener(e -> dialog.dispose());

        dialog.add(painel, BorderLayout.CENTER);
        dialog.add(btnFinalizar, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JButton getJButton(String hora, String dia, Font fonte, Usuarios usuarioLogado) {
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
                reservasPorEspaco.get(this.nome).put(horarioKey, usuarioLogado.getMatricula());
                JOptionPane.showMessageDialog(null,
                        "Você foi alocado para " + dia + " às " + hora,
                        "Confirmação", JOptionPane.INFORMATION_MESSAGE);

                if (!reservasPorUsuario.containsKey(usuarioLogado.getMatricula())) {
                    reservasPorUsuario.put(usuarioLogado.getMatricula(), new ArrayList<>());
                }
                boolean condicao = podeReservarHorario(dia, usuarioLogado);
                if (condicao) {
                    reservasPorUsuario.get(usuarioLogado.getMatricula()).add(horarioKey);
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
                                JOptionPane.showMessageDialog(null, "Alunos não podem reservar em dias consecutivos.");
                                return false;
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
        if (!reservas.contains("Reservas de") || !reservas.contains(usuarioLogado.getMatricula())) {
            sb.append("Reservas de: \n").append(usuarioLogado.getNomeCompleto()).append("\n\n");
        }
        // Verifica se o usuário tem reservas no sistema
        if (reservasPorUsuario.containsKey(usuarioLogado.getMatricula())) {
            ArrayList<String> reservasDoUsuario = reservasPorUsuario.get(usuarioLogado.getMatricula());
            int contador = 0;
            if (reservasPorEspaco.containsKey(this.nome)) {
                sb.append(this.nome).append("\n");
            }
            // Filtra apenas as reservas deste espaço físico
            for (String horario : reservasDoUsuario) {
                // Verifica se a reserva pertence a este espaço
                if (reservasPorEspaco.containsKey(this.nome) &&
                        reservasPorEspaco.get(this.nome).containsKey(horario) &&
                        reservasPorEspaco.get(this.nome).get(horario).equals(usuarioLogado.getMatricula())) {

                    sb.append(horario).append("\n");
                    contador++;
                }
            }

            if (contador == 0) {
                sb.append("Nenhuma reserva encontrada neste espaço.\n");
            }
        } else {
            sb.append("Nenhuma reserva encontrada para esta matrícula.\n");
        }
        reservas = sb.toString();
        return reservas;
    }
}
