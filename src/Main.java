import javax.swing.*;
public class Main {
    private static String nome, email, telefone, senha, matricula;
    public static void main(String[] args) {
        nome = JOptionPane.showInputDialog("NOME: ");
        email = JOptionPane.showInputDialog("EMAIL: ");
        telefone = JOptionPane.showInputDialog("TELEFONE: ");
        matricula = JOptionPane.showInputDialog("MATRÍCULA: ");
        Usuarios usuarios = new Usuarios(nome, email, telefone,"1234", matricula);
        JOptionPane.showMessageDialog(null, usuarios);

    }
}