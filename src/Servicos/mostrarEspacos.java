package Servicos;

import javax.swing.JOptionPane;
import Entidades.Alunos;
import Entidades.Usuarios;

public class mostrarEspacos {
    public static String reservas = "";
    public static String[] reservasV = new String[14];
    public static String historicoReserva = "";
    public static Laboratorio lab1 = new Laboratorio("Laboratório de Física", 30, "UED", "", "Disponível");
    public static Laboratorio lab2 = new Laboratorio("Laboratório de Quimica", 30, "UED", "", "Disponível");
    public static Auditorio mocap = new Auditorio("MOCAP", 200, "Bloco A", "Projetor", "Disponível");
    public static Auditorio auditorio = new Auditorio("Auditorio", 500, "Bloco A", "Projetor", "Disponível");
    public static SalaDeAula salaI1 = new SalaDeAula("I1", 60, "UAC", "Canetão, Giz e Computador", "Disponível");
    public static SalaDeAula salaI2 = new SalaDeAula("I2", 60, "UAC", "Canetão, Giz e Computador", "Disponível");
    public static SalaDeAula salaI3 = new SalaDeAula("I3", 60, "UAC", "Canetão, Giz e Computador", "Disponível");
    public static SalaDeAula salaI4 = new SalaDeAula("I4", 60, "UAC", "Canetão, Giz e Computador", "Disponível");
    public static SalaDeAula salaI5 = new SalaDeAula("I5", 60, "UAC", "Canetão, Giz e Computador", "Disponível");
    public static SalaDeAula salaS1 = new SalaDeAula("S1", 130, "UAC", "Canetão e Giz", "Disponível");
    public static SalaDeAula salaS2 = new SalaDeAula("S2", 130, "UAC", "Canetão e Giz", "Disponível");
    public static SalaDeAula salaS3 = new SalaDeAula("S3", 130, "UAC", "Canetão e Giz", "Disponível");
    public static SalaDeAula salaS4 = new SalaDeAula("S4", 130, "UAC", "Canetão e Giz", "Disponível");
    public static SalaDeAula salaS5 = new SalaDeAula("S5", 130, "UAC", "Canetão e Giz", "Disponível");

    public static void mostrarEspacosFisicos(Usuarios usuarioLogado) {
        reservas = "";
        // criação da caixa de opções
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
                Object[] opcoesLab = { "Laboratório Física", "Lababoratório Química" };
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
                        // Mostra a grade horaria do espaço selecionado
                        lab1.mostrarGradeHoraria(usuarioLogado);
                        reservas += lab1.exibirReservas(usuarioLogado, reservas);
                        // Adiciona a reserva feita ao vetor de reservas
                        reservasV[0] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        // verfica se o usuario logado é do tipo aluno
                        if (!(usuarioLogado instanceof Alunos)) {
                            // Caixa de pergunta para perguntar se vai adicionar ou remover algum
                            // equipamento
                            int escolha = opcaoAdicionaRemove();
                            switch (escolha) {
                                case 0:
                                    lab1.adicionarEquipamento();
                                    break;
                                case 1:
                                    lab1.removerEquipamento();
                                    break;
                            }
                        }
                        break;
                    case 1:
                        lab2.mostrarGradeHoraria(usuarioLogado);
                        reservas += lab2.exibirReservas(usuarioLogado, reservas);
                        reservasV[1] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s)" + "\n" + reservas);
                        if (!(usuarioLogado instanceof Alunos)) {
                            int escolha = opcaoAdicionaRemove();
                            switch (escolha) {
                                case 0:
                                    lab2.adicionarEquipamento();
                                    break;
                                case 1:
                                    lab2.removerEquipamento();
                                    break;
                            }
                        }
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
                        salaI1.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI1.exibirReservas(usuarioLogado, reservas);
                        reservasV[3] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s)" + "\n" + reservas);
                        // Chama o metodo onde mostra todos equipamentos disponiveis
                        salaI1.quantidadeEquipamentos();
                        break;
                    case 1:
                        salaI2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI2.exibirReservas(usuarioLogado, reservas);
                        reservasV[4] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaI2.quantidadeEquipamentos();
                        break;
                    case 2:
                        salaI3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI3.exibirReservas(usuarioLogado, reservas);
                        reservasV[5] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaI3.quantidadeEquipamentos();
                        break;
                    case 3:
                        salaI4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI4.exibirReservas(usuarioLogado, reservas);
                        reservasV[6] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaI4.quantidadeEquipamentos();
                        break;
                    case 4:
                        salaI5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI5.exibirReservas(usuarioLogado, reservas);
                        reservasV[7] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaI5.quantidadeEquipamentos();
                        break;
                    case 5:
                        salaS1.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS1.exibirReservas(usuarioLogado, reservas);
                        reservasV[8] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaS1.quantidadeEquipamentos();
                        break;
                    case 6:
                        salaS2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS2.exibirReservas(usuarioLogado, reservas);
                        reservasV[9] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaS2.quantidadeEquipamentos();
                        break;
                    case 7:
                        salaS3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS3.exibirReservas(usuarioLogado, reservas);
                        reservasV[9] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaS3.quantidadeEquipamentos();
                        break;
                    case 8:
                        salaS4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS4.exibirReservas(usuarioLogado, reservas);
                        reservasV[10] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaS4.quantidadeEquipamentos();
                        break;
                    case 9:
                        salaS5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS5.exibirReservas(usuarioLogado, reservas);
                        reservasV[11] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        salaS5.quantidadeEquipamentos();
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
                        auditorio.mostrarGradeHoraria(usuarioLogado);
                        reservas += auditorio.exibirReservas(usuarioLogado, reservas);
                        reservasV[12] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s) " + "\n" + reservas);
                        break;
                    case 1:
                        mocap.mostrarGradeHoraria(usuarioLogado);
                        reservas += mocap.exibirReservas(usuarioLogado, reservas);
                        reservasV[13] = reservas;
                        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s)" + "\n" + reservas);
                        break;
                }

                break;
        }
    }

    public static int opcaoAdicionaRemove() {
        Object[] opcoesLab = { "Adiciona", "Remove", "Nenhum" };
        return JOptionPane.showOptionDialog(
                null,
                "Deseja Adicionar ou remover equipamentos",
                "Equipamentos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesLab,
                opcoesLab[0]);
    }

    public static String montaReservas(Usuarios usuarioLogado) {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservas de ").append(usuarioLogado.getNomeCompleto()).append(": \n");
        for (String reserva : reservasV) {
            if (reserva != null) {
                sb.append(reserva);
            }
        }
        return sb.toString();
    }

}
