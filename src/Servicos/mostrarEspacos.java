package Servicos;

import javax.swing.JOptionPane;

import Entidades.Usuarios;

public class mostrarEspacos {
    public static String reservas = "";
    public static Laboratorio lab1 = new Laboratorio("Laboratório de Física", 30, "UED", "", "Disponível");
    public static Laboratorio lab2 = new Laboratorio("Laboratório de Quimica", 30, "UED", "", "Disponível");
    public static Auditorio MOCAP = new Auditorio("MOCAP", 200, "Bloco A", "Projetor", "Disponível");
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
                        reservas = "";
                        lab1.mostrarGradeHoraria(usuarioLogado);
                        reservas += lab1.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 1:
                        reservas = "";
                        lab2.mostrarGradeHoraria(usuarioLogado);
                        reservas += lab2.exibirReservas(usuarioLogado, reservas);
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
                        reservas += salaI1.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 1:
                        reservas = "";
                        salaI2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI2.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 2:
                        reservas = "";
                        salaI3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI3.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 3:
                        reservas = "";
                        salaI4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI4.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 4:
                        reservas = "";
                        salaI5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI5.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 5:
                        reservas = "";
                        salaS1.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS1.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 6:
                        reservas = "";
                        salaS2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS2.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 7:
                        reservas = "";
                        salaS3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS3.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 8:
                        reservas = "";
                        salaS4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS4.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 9:
                        reservas = "";
                        salaS5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS5.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
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
                        reservas += auditorio.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                    case 1:
                        reservas = "";
                        MOCAP.mostrarGradeHoraria(usuarioLogado);
                        reservas += MOCAP.exibirReservas(usuarioLogado, reservas);
                        JOptionPane.showMessageDialog(null, reservas);
                        break;
                }

                break;
        }
    }
}
