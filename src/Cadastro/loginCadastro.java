package Cadastro;
import Servicos.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entidades.Usuarios;

public class loginCadastro {
    public static int sair() {
        Object[] opcoes001 = { "CONTINUAR", "SAIR" };
        return JOptionPane.showOptionDialog(null,
                "",
                "SAIR",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes001,
                opcoes001[0]);
    }

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
                            int sair;
                            do {
                                mostrarEspacos.mostrarEspacosFisicos(usuarioLogado);
                                sair = loginCadastro.sair();
                            } while (sair == 0);
                        }
                        mostrarEspacos.reservas = "";
                        usuarioLogado = null;
                        break;
                    case 1:
                        cadastro.cadastroUsuario(listaUsuarios);
                        break;
                }
            }
    }
}
