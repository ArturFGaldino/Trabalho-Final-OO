package Servicos;

import javax.swing.JOptionPane;

import Entidades.Alunos;
import Entidades.Usuarios;

import java.util.Map;

import static Servicos.EspacosFisicos.reservasPorEspaco;

public class mostrarEspacos {
    public static String reservas = "";
    public static String[] reservasV = new String[14];
    public static String historicoReserva= "";
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
                        reservas += lab1.exibirReservas(usuarioLogado, reservas);
                        reservasV[0] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        if(!(usuarioLogado instanceof Alunos)){
                            int escolha = opcaoAdicionaRemove();
                            switch (escolha){
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
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s)" + "\n" + reservas);
                        if(!(usuarioLogado instanceof Alunos)){
                            int escolha = opcaoAdicionaRemove();
                            switch (escolha){
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
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s)" + "\n" + reservas);
                        salaI1.quantidadeEquipamentos();
                        break;
                    case 1:
                        salaI2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI2.exibirReservas(usuarioLogado, reservas);
                        reservasV[4] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaI2.quantidadeEquipamentos();
                        break;
                    case 2:
                        salaI3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI3.exibirReservas(usuarioLogado, reservas);
                        reservasV[5] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaI3.quantidadeEquipamentos();
                        break;
                    case 3:
                        salaI4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI4.exibirReservas(usuarioLogado, reservas);
                        reservasV[6] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaI4.quantidadeEquipamentos();
                        break;
                    case 4:
                        salaI5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaI5.exibirReservas(usuarioLogado, reservas);
                        reservasV[7]=reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaI5.quantidadeEquipamentos();
                        break;
                    case 5:
                        salaS1.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS1.exibirReservas(usuarioLogado, reservas);
                        reservasV[8] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaS1.quantidadeEquipamentos();
                        break;
                    case 6:
                        salaS2.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS2.exibirReservas(usuarioLogado, reservas);
                        reservasV[9] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaS2.quantidadeEquipamentos();
                        break;
                    case 7:
                        salaS3.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS3.exibirReservas(usuarioLogado, reservas);
                        reservasV[9] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaS3.quantidadeEquipamentos();
                        break;
                    case 8:
                        salaS4.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS4.exibirReservas(usuarioLogado, reservas);
                        reservasV[10] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        salaS4.quantidadeEquipamentos();
                        break;
                    case 9:
                        salaS5.mostrarGradeHoraria(usuarioLogado);
                        reservas += salaS5.exibirReservas(usuarioLogado, reservas);
                        reservasV[11] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
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
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s) " + "\n" + reservas);
                        break;
                    case 1:
                        mocap.mostrarGradeHoraria(usuarioLogado);
                        reservas += mocap.exibirReservas(usuarioLogado, reservas);
                        reservasV[13] = reservas;
                        JOptionPane.showMessageDialog(null,"Sua(s) reserva(s)" + "\n" + reservas);
                        break;
                }

                break;
        }
    }
    public static int opcaoAdicionaRemove(){
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
    public static String montaReservas(Usuarios usuarioLogado){
        StringBuilder sb = new StringBuilder();
        sb.append("Reservas de ").append(usuarioLogado.getNomeCompleto()).append(": \n");
        for(String reserva : reservasV){
            if(reserva!=null){
                sb.append(reserva);
            }
        }
        return sb.toString();
    }
    public static String montaReservas(){
        String relatorio = "";
        Object[] opcoesespacos = {"Lab1", "Lab2", "I1", "I2", "I3", "I4", "I5", "S1", "S2", "S3",
                "S4", "S5", "Auditório principal", "Mocap" };
        int escolherespaco = JOptionPane.showOptionDialog(
                null,
                "Qual espaço deseja saber o relatório?",
                "Salas disponíveis",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesespacos,
                opcoesespacos[0]);
        switch (escolherespaco){
            case 0:
                Map<String, String> laboratorioDeFisica = reservasPorEspaco.get("Laboratório de Física");
                for (Map.Entry<String, String> rel : laboratorioDeFisica.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 1:
                Map<String, String> laboratorioDeQuimica = reservasPorEspaco.get("Laboratório de Química");
                for (Map.Entry<String, String> rel : laboratorioDeQuimica.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 2:
                Map<String, String> I1= reservasPorEspaco.get("I1");
                for (Map.Entry<String, String> rel : I1.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 3:
                Map<String, String> I2 = reservasPorEspaco.get("I2");
                for (Map.Entry<String, String> rel : I2.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 4:
                Map<String, String> I3 = reservasPorEspaco.get("I3");
                for (Map.Entry<String, String> rel : I3.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 5:
                Map<String, String> I4 = reservasPorEspaco.get("I4");
                for (Map.Entry<String, String> rel : I4.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 6:
                Map<String, String> I5 = reservasPorEspaco.get("I5");
                for (Map.Entry<String, String> rel : I5.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 7:
                Map<String, String> S1 = reservasPorEspaco.get("S1");
                for (Map.Entry<String, String> rel : S1.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 8:
                Map<String, String> S2 = reservasPorEspaco.get("S2");
                for (Map.Entry<String, String> rel : S2.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 9:
                Map<String, String> S3 = reservasPorEspaco.get("S3");
                for (Map.Entry<String, String> rel : S3.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 10:
                Map<String, String> S4 = reservasPorEspaco.get("S4");
                for (Map.Entry<String, String> rel : S4.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 11:
                Map<String, String> S5 = reservasPorEspaco.get("S5");
                for (Map.Entry<String, String> rel : S5.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 12:
                Map<String, String> Auditorio = reservasPorEspaco.get("Auditorio");
                for (Map.Entry<String, String> rel : Auditorio.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
            case 13:
                Map<String, String> MOCAP = reservasPorEspaco.get("MOCAP");
                for (Map.Entry<String, String> rel : MOCAP.entrySet()) {
                    relatorio += rel.getKey() +  rel.getValue();
                }
                break;
        }
        return relatorio;
    }
}
