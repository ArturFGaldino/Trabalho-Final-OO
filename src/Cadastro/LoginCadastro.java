package Cadastro;
import Entidades.Alunos;
import Servicos.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entidades.Usuarios;
import static Servicos.MostrarEspacos.*;

public abstract class LoginCadastro {
    private static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    private static Usuarios artur = new Alunos("Artur Fernandes Galdino", "241010923@aluno.unb.br", "61998658594", "1",
            "241010923", "Engenharia de Software");
    private static Usuarios pedro = new Alunos("Pedro Augusto Macedo Del Castilo", "241025354@aluno.unb.br",
            "61082859745", "1", "241025354", "Engenharia de Software");

    static int sair;
    public static int aux;
    public static ArrayList<EspacosFisicos> listaEspacos = new ArrayList<>();

    // LOGIN OU CADASTRO
    public static void loginCadastroUsuario() {
        listaUsuarios.add(artur);
        listaUsuarios.add(pedro);
        // Cria a janela de opções
        Object[] opcoes001 = { "LOGIN", "CADASTRO", "CANCELAR" };
        int opcao02 = JOptionPane.showOptionDialog(null,
                "Bem-vindo ao sistema de reservas de espaços físicos da UnB!\n\n"
                        + "Selecione uma opção:\n\n",
                "LOGIN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes001,
                opcoes001[0]);
        // verificação da opção cancelar
        if (opcao02 == 2 || opcao02 == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada pelo usuário.",
                    " ",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            switch (opcao02) {
                case 0:
                    Usuarios usuarioLogado = Login.loginCadastro(listaUsuarios);
                    // Chama o metodo LoginCasdastro da classe Login criando um usuario
                    if (aux != 1) {
                        // adiciona todos espaços a ArrayList listaEspacos
                        listaEspacos.add(lab1);
                        listaEspacos.add(lab2);
                        listaEspacos.add(salaI1);
                        listaEspacos.add(salaI2);
                        listaEspacos.add(salaI3);
                        listaEspacos.add(salaI4);
                        listaEspacos.add(salaI5);
                        listaEspacos.add(salaS1);
                        listaEspacos.add(salaS2);
                        listaEspacos.add(salaS3);
                        listaEspacos.add(salaS4);
                        listaEspacos.add(salaS5);
                        listaEspacos.add(mocap);
                        listaEspacos.add(auditorio);
                        aux++;
                    }
                    if(usuarioLogado!=null){
                        Object[] opcoes = { "RESERVAR ESPAÇO", "ALTERAR SENHA" };
                        int opcao = JOptionPane.showOptionDialog(null,
                                "Selecione uma opção:\n\n",
                                "RESERVAS",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opcoes,
                                opcoes[0]);
                        switch (opcao){
                            case 0:
                                    do {
                                        // chama o metedo para mostrar as opcões de espacos fisicos na clase
                                        // mostrarEspacos
                                        MostrarEspacos.mostrarEspacosFisicos(usuarioLogado);
                                        // chama o metedo onde pode ter acesso ao relatorio do proprio usuário
                                        LoginCadastro.geraRelatorio(usuarioLogado, listaUsuarios);
                                    } while (sair == 0);
                                MostrarEspacos.historicoReserva = "";
                                break;
                            case 1:
                                String senha = JOptionPane.showInputDialog(usuarioLogado + "DIGITE A NOVA SENHA");
                                if (senha == null) {
                                    return;
                                }
                                String tempSenha = JOptionPane.showInputDialog(usuarioLogado + "CONFIRME A NOVA SENHA");
                                if (tempSenha == null) {
                                    return;
                                }
                                while (!senha.equals(tempSenha) || senha.isEmpty()) {
                                    senha = JOptionPane.showInputDialog(usuarioLogado + "DIGITE NOVAMENTE A NOVA SENHA");
                                    if (senha == null) {
                                        return;
                                    }
                                    tempSenha = JOptionPane.showInputDialog(usuarioLogado + "CONFIRME A NOVA SENHA");
                                    if (tempSenha == null) {
                                        return;
                                    }
                                }
                                usuarioLogado.setSenha(senha);
                        }
                        usuarioLogado = null;
                        break;
                    }
                    break;
                case 1:
                    // chama o metodo cadastroUsuario da classe Cadastro
                    Cadastro.cadastroUsuario(listaUsuarios);
                    break;
            }
        }
    }

    private static void geraRelatorio(Usuarios usuarioLogado, ArrayList<Usuarios> listaUsuarios) {
        // Cria janela de opções
        Object[] opcoes = { "Relatórios", "Continuar neste Usuário", "Sair deste Usuário" };
        int escolher = JOptionPane.showOptionDialog(
                null,
                "",
                "Relatórios",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

            if (escolher == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.",
                    " ",
                    JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
            }else{
                switch (escolher) {
                    case 0:
                        // Chama o metodo onde cria o arquivo .txt
                        montaRelatorio(usuarioLogado);
                        // Chama o metodo que adiciona o espaço alocado
                        montaRelatorio();
                        System.exit(0);
                        break;
                    case 1:
                        sair = 0;
                        break;
                    case 2:
                        LoginCadastro.loginCadastroUsuario();
                        sair = 1;
                        break;
                }
            }
    }

    private static void montaRelatorio(Usuarios usuarioLogado) {
        try {
            FileWriter arquivo = new FileWriter("relatorioUsuario.txt");
            arquivo.write(MostrarEspacos.montaReservas(usuarioLogado));
            arquivo.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }

    private static void montaRelatorio() {
        try {
            StringBuilder sb = new StringBuilder();
            for (EspacosFisicos espacofisico : listaEspacos) {
                if(!(espacofisico.exibirReservas(espacofisico).contains("Sem reservas"))){
                    sb.append(espacofisico.exibirReservas(espacofisico)).append("\n");
                }
            }
            FileWriter arquivo = new FileWriter("relatorioEspacos.txt");
            arquivo.write(sb.toString());
            arquivo.close();
            JOptionPane.showMessageDialog(null, "Suas reservas foram gravadas em arquivos .txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }
}
