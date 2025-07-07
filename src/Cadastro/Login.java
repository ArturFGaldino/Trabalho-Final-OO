package Cadastro;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import Entidades.Usuarios;

public class Login {
    public static Usuarios loginCadastro(ArrayList<Usuarios> listaUsuarios) {

        boolean logado = false;
        String tempLogin, tempSenha;
        Usuarios usuarioLogado = null;
        // laço com a condição de logado
        while (!logado) {
            tempLogin = JOptionPane.showInputDialog("MATRÍCULA: ");
            if (tempLogin == null) {
                break;
            }
            tempSenha = JOptionPane.showInputDialog("SENHA: ");
            if (tempSenha == null) {
                break;
            }
            // verificação se o usuario existe dentro de listaUsuarios
            for (Usuarios usuario : listaUsuarios) {
                // verficação se a matricula e senha estão corretos
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
