package Cadastro;
import Servicos.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entidades.Usuarios;

public class loginCadastro {
    static int sair;

    // LOGIN OU CADASTRO
    public static void loginCadastroUsuario(ArrayList<Usuarios> listaUsuarios) {
        Object[] opcoes001 = { "LOGIN", "CADASTRO", "CANCELAR" };
        int opcao02 = JOptionPane.showOptionDialog(null,
                "",
                "LOGIN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes001,
                opcoes001[0]);
            if (opcao02 == 2 || opcao02 == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null,
                        "Operação cancelada pelo usuário.",
                        " ",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                switch (opcao02) {
                    case 0:
                    Usuarios usuarioLogado = login.loginCadastro(listaUsuarios);
                    if(usuarioLogado!=null){
                        do {
                            mostrarEspacos.mostrarEspacosFisicos(usuarioLogado);
                            loginCadastro.geraRelatorio(usuarioLogado);
                        } while (sair == 0);
                    }
                    mostrarEspacos.historicoReserva = "";
                    usuarioLogado = null;
                    break;
                    case 1:
                        cadastro.cadastroUsuario(listaUsuarios);
                        break;
                }
            }
    }

    private static void geraRelatorio(Usuarios usuarioLogado){
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
        switch (escolher){
            case 0:
                montaRelatorioUsuarios(usuarioLogado);
                //montaRelatorioEspacosFisicos
                System.exit(0);
                break;
            case 1:
                sair = 0;
                break;
            case 2:
                sair = 1;
                break;
        }
    }
    private static void montaRelatorioUsuarios(Usuarios usuarioLogado){
        try{
            FileWriter arquivo = new FileWriter("relatorioUsuario.txt");
            arquivo.write(mostrarEspacos.montaReservas(usuarioLogado));
            arquivo.close();
            JOptionPane.showMessageDialog(null,"Suas reservas foram gravadas no arquivo relatorioUsuario.txt");
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }
}
