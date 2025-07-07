package Servicos;

import javax.swing.JOptionPane;
import Entidades.Alunos;
import Entidades.Usuarios;

public abstract class MostrarEspacos {
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
        if (escolhaEspacoFisico == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada pelo usuário.",
                    " ",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }else{
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
                            interfaceEspacos(lab1,usuarioLogado);
                            reservasV[1] = reservas;
                            break;
                        case 1:
                            interfaceEspacos(lab2,usuarioLogado);
                            reservasV[2] = reservas;
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
                            interfaceEspacos(salaI1,usuarioLogado);
                            reservasV[3] = reservas;
                            break;
                        case 1:
                            interfaceEspacos(salaI2,usuarioLogado);
                            reservasV[4] = reservas;
                            break;
                        case 2:
                            interfaceEspacos(salaI3,usuarioLogado);
                            reservasV[5] = reservas;
                            break;
                        case 3:
                            interfaceEspacos(salaI4,usuarioLogado);
                            reservasV[6] = reservas;
                            break;
                        case 4:
                            interfaceEspacos(salaI5,usuarioLogado);
                            reservasV[7] = reservas;
                            break;
                        case 5:
                            interfaceEspacos(salaS1,usuarioLogado);
                            reservasV[8] = reservas;
                            break;
                        case 6:
                            interfaceEspacos(salaS2,usuarioLogado);
                            reservasV[9] = reservas;
                            break;
                        case 7:
                            interfaceEspacos(salaS3,usuarioLogado);
                            reservasV[10] = reservas;
                            break;
                        case 8:
                            interfaceEspacos(salaS4,usuarioLogado);
                            reservasV[11] = reservas;
                            break;
                        case 9:
                            interfaceEspacos(salaS5,usuarioLogado);
                            reservasV[12] = reservas;
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
                            interfaceEspacos(auditorio,usuarioLogado);
                            reservasV[13] = reservas;
                            break;
                        case 1:
                            interfaceEspacos(mocap,usuarioLogado);
                            reservasV[14] = reservas;
                            break;
                    }
    
                    break;
            }
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
    private static void interfaceEspacos(EspacosFisicos espacosFisicos, Usuarios usuarioLogado){
        // Mostra a grade horaria do espaço selecionado
        espacosFisicos.mostrarGradeHoraria(usuarioLogado);
        // Adiciona a reserva feita ao vetor de reservas
        reservas += espacosFisicos.exibirReservas(usuarioLogado, reservas);
        JOptionPane.showMessageDialog(null, "Sua(s) reserva(s)" + "\n" + reservas);
        if(espacosFisicos instanceof Laboratorio laboratorio){
            // verfica se o usuario logado é do tipo aluno
            if (!(usuarioLogado instanceof Alunos)) {
                // Caixa de pergunta para perguntar se vai adicionar ou remover algum
                // equipamento
                int escolha = opcaoAdicionaRemove();
                switch (escolha) {
                    case 0:
                        laboratorio.adicionarEquipamento();
                        break;
                    case 1:
                        laboratorio.removerEquipamento();
                        break;
                }
            }
        } else if (espacosFisicos instanceof SalaDeAula salaDeAula) {
            if(reservas.length()>(espacosFisicos.getNome().length()+2)){
                // Chama o metodo onde mostra todos os equipamentos disponiveis
                salaDeAula.quantidadeEquipamentos();
            }
        }
    }
}
