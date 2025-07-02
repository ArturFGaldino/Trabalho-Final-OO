package Servicos;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import Entidades.Usuarios;
import Entidades.Alunos;

public abstract class EspacosFisicos {
    private final int capacidade;
    private final String localizacao, equipamentosDisponiveis, disponibilidades, nome;
    private final static Map<String, ArrayList<String>> horariosReservados = new HashMap<>();

    EspacosFisicos(String nome, int capacidade, String localizacao, String equipamentosDisponiveis, String disponibilidades) {
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.equipamentosDisponiveis = equipamentosDisponiveis;
        this.disponibilidades = disponibilidades;
        this.nome = nome;
    }


    // Gets
    public int getCapacidade(){
        return  capacidade;
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

    public void mostrarGradeHoraria(String espacoSelecionado, Usuarios usuarioLogado) {
        String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta" };
        String[] horas = { "08h", "10h", "12h", "14h", "16h" };

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Horários disponíveis - " + espacoSelecionado);
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
        String reservas = exibirReservasUsuario(usuarioLogado);
        JOptionPane.showMessageDialog(null, reservas);
    }

    private JButton getJButton(String hora, String dia, Font fonte, Usuarios usuarioLogado) {
        JButton botao = new JButton("Livre");
        botao.setFont(fonte);
        botao.setBackground(new Color(200, 230, 250));
        botao.setFocusPainted(false);
        String horarioReservado = dia + " às " + hora;

        botao.addActionListener(e -> {
            if (botao.getText().equals("Livre")){
                botao.setText("Alocado");
                botao.setBackground(new Color(180, 220, 180));
                JOptionPane.showMessageDialog(null,
                        "Você foi alocado para " + dia + " às " + hora,
                        "Confirmação", JOptionPane.INFORMATION_MESSAGE);

                if (!horariosReservados.containsKey(usuarioLogado.getMatricula())) {
                    horariosReservados.put(usuarioLogado.getMatricula(), new ArrayList<>());
                }
                boolean condicao = podeReservarHorario(dia, usuarioLogado);
                if (condicao) {
                    horariosReservados.get(usuarioLogado.getMatricula()).add(horarioReservado);
                } else {
                    botao.setText("Livre");
                    botao.setBackground(new Color(200, 230, 250));
                }
            }else{
                botao.setText("Livre");
                botao.setBackground(new Color(200, 230, 250));
                horariosReservados.get(usuarioLogado.getMatricula()).remove(horarioReservado);
            }
        });
        return botao;
    }

    public String exibirReservasUsuario(Usuarios usuarioLogado) {
        if (horariosReservados.containsKey(usuarioLogado.getMatricula())) {
            StringBuilder reservas = new StringBuilder("SUAS RESERVAS:\n\n");
            for (String hr : horariosReservados.get(usuarioLogado.getMatricula())) {
                reservas.append("- ").append(hr).append("\n");
            }
            reservas.insert(0, "Resumo de reservas para matrícula: " + usuarioLogado.getMatricula() + "\n\n");
            return reservas.toString();
        } else {
            return "NENHUMA RESERVA ESCOLHIDA";
        }
    }

    private boolean podeReservarHorario(String dia, Usuarios usuarioLogado) {
        if (usuarioLogado instanceof Alunos) {
            List<String> reservas = horariosReservados.get(usuarioLogado.getMatricula());

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
    
}
