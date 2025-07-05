package Cadastro;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Entidades.Usuarios;

public class login {
    public static Usuarios loginCadastro(ArrayList<Usuarios> listaUsuarios) {
        
        boolean logado = false;
        String tempLogin, tempSenha;
        Usuarios usuarioLogado = null;
        while (!logado) {
            tempLogin = JOptionPane.showInputDialog("MATRÍCULA: ");
                if (tempLogin == null) {
                    break;
                }
                tempSenha = JOptionPane.showInputDialog("SENHA: ");
                if (tempSenha == null) {
                    break;
                }
                for (Usuarios usuario : listaUsuarios) {
                    if (usuario.getMatricula().equals(tempLogin) && usuario.getSenha().equals(tempSenha)) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo, " + usuario.getNomeCompleto());
                        usuarioLogado = usuario;
                        logado = true;
                        break;
                    }
                }
                if (!logado) {
                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos.");
                }
            }
        return usuarioLogado;
    }
}
