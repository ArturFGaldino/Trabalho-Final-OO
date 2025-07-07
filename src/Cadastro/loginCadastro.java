package Cadastro;
import Servicos.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entidades.Usuarios;

import static Servicos.mostrarEspacos.*;
import static Servicos.mostrarEspacos.auditorio;
import static Servicos.mostrarEspacos.mocap;
import static Servicos.mostrarEspacos.salaI2;
import static Servicos.mostrarEspacos.salaI3;
import static Servicos.mostrarEspacos.salaI4;
import static Servicos.mostrarEspacos.salaI5;
import static Servicos.mostrarEspacos.salaS1;
import static Servicos.mostrarEspacos.salaS2;
import static Servicos.mostrarEspacos.salaS3;
import static Servicos.mostrarEspacos.salaS4;
import static Servicos.mostrarEspacos.salaS5;

public class loginCadastro {
    static int sair;
    public static int aux;
    public static ArrayList<EspacosFisicos> listaEspacos = new ArrayList<>();
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
                    if(aux!=1){
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
                montaRelatorio(usuarioLogado);
                montaRelatorio();
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
    private static void montaRelatorio(Usuarios usuarioLogado){
        try{
            FileWriter arquivo = new FileWriter("relatorioUsuario.txt");
            arquivo.write(mostrarEspacos.montaReservas(usuarioLogado));
            arquivo.close();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }

    private static void montaRelatorio(){
        try{
            for(EspacosFisicos espacofisico : listaEspacos){
                FileWriter arquivo = new FileWriter("relatorio"+ espacofisico.getNome() + ".txt");
                arquivo.write(espacofisico.exibirReservas(espacofisico));
                arquivo.close();
            }
            JOptionPane.showMessageDialog(null,"Suas reservas foram gravadas em arquivos .txt");
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }
}
