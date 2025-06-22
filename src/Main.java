import javax.swing.*;
import java.util.ArrayList;
import Entidades.*;

public class Main {
    public static String nome, email, telefone, matricula;
    public static String senha;
    public static String tempSenha;
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    public static int continuar = 1;
    public static String tempLogin;
    public static Usuarios usuarioLogado = null;
    public static JFrame frame = new JFrame();
    public static void main(String[] args) {
        do {
            int opcao = loginCadastro();
            if (opcao == 2 || opcao == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(frame,
                        "Operação cancelada pelo usuário.",
                        " ",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }else{
                switch (opcao) {
                    case 0:
//                    login();
                        mostrarEspacosFisicos();
                        break;
                    case 1:
                        cadastro();
                        break;
                }
            }
        }while(continuar == 1);
    }

    //LOGIN OU CADASTRO
    private static int loginCadastro(){
        Object[]opcoes = {"LOGIN","CADASTRO","CANCELAR"};
        return JOptionPane.showOptionDialog(frame,
                "",
                "LOGIN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );
    }

    //CADASTRO
    private static void cadastro(){
        int parar = 0;
        nome = JOptionPane.showInputDialog("NOME COMPLETO");
        if(Usuarios.validaNull(nome)){
            return;
        }
        while (Usuarios.validaEmpty(nome)){
            nome = JOptionPane.showInputDialog("NOME INVÁLIDO. DIGITE CORRETAMENTE:");
        }
        telefone = JOptionPane.showInputDialog("TELEFONE");
        if(Usuarios.validaNull(telefone)){
            return;
        }
        while(Usuarios.validaTelefone(telefone)){
            telefone = JOptionPane.showInputDialog("TELEFONE INVÁLIDO. DIGITE CORRETAMENTE: ");
        }
        matricula = JOptionPane.showInputDialog("MATRÍCULA");
        if(Usuarios.validaNull(matricula)){
            return;
        }
        while(Usuarios.validaMatricula(matricula)){
            matricula = JOptionPane.showInputDialog("MATRÍCULA INVÁLIDA. DIGITE CORRETAMENTE: ");
        }
        senha = JOptionPane.showInputDialog("DIGITE SUA SENHA");
        if(Usuarios.validaNull(senha)){
            return;
        }
        tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
        if(Usuarios.validaNull(tempSenha)){
            return;
        }
        while (!senha.equals(tempSenha) || Usuarios.validaEmpty(senha)){
            senha = JOptionPane.showInputDialog("DIGITE NOVAMENTE SUA SENHA");
            tempSenha = JOptionPane.showInputDialog("CONFIRME SUA SENHA");
        }
        do{
            Object[] opcoes = { "ALUNO" , "PROFESSOR" , "SERVIDOR", "CANCELAR"};
            boolean emailValido = false;
            int relacao = JOptionPane.showOptionDialog(frame,
                    "INFORME O TIPO DE RELAÇÃO COM A INSTITUIÇÃO",
                    "RELAÇÃO COM A INSTITUIÇÃO",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
            if (relacao == 3 || relacao == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(frame, "Operação cancelada pelo usuário.",
                        " ",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }else {
                switch (relacao) {
                    case 0:
                        String curso = JOptionPane.showInputDialog("CURSO: ");
                        if(Usuarios.validaNull(curso)){
                            return;
                        }
                        while (Usuarios.validaEmpty(curso)){
                            curso = JOptionPane.showInputDialog("DIGITE UM CURSO VÁLIDO: ");
                        }
                        do {
                            email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                            Alunos alunoTemp = new Alunos(nome, email, telefone, senha, matricula, curso);
                            if (alunoTemp.validaEmail(matricula, email)) {
                                listaUsuarios.add(alunoTemp);
                                emailValido = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO PARA ALUNO.");
                            }
                        } while (!emailValido);
                        break;
                    case 1:
                        String cargoAcademico = JOptionPane.showInputDialog("CARGO ACADEMICO: ");
                        if(Usuarios.validaNull(cargoAcademico)){
                            return;
                        }
                        while (Usuarios.validaEmpty(cargoAcademico)){
                            cargoAcademico = JOptionPane.showInputDialog("DIGITE UM CARGO ACADEMICO VÁLIDO: ");
                        }
                        String cargoMinistrado = JOptionPane.showInputDialog("CARGO MINISTRADO: ");
                        if(Usuarios.validaNull(cargoMinistrado)){
                            return;
                        }
                        while (Usuarios.validaEmpty(cargoMinistrado)){
                            cargoMinistrado = JOptionPane.showInputDialog("DIGITE UM CARGO MINISTRADO VÁLIDO: ");
                        }
                        do {
                            email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                            Professores professorTemp = new Professores(nome, email, telefone, senha, matricula, cargoAcademico, cargoMinistrado);
                            if (professorTemp.validaEmail(matricula, email)) {
                                listaUsuarios.add(professorTemp);
                                emailValido = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO.");
                            }
                        } while (!emailValido);
                        break;
                    case 2:
                        String cargoExercido = JOptionPane.showInputDialog("CARGO EXERCIDO: ");
                        if(Usuarios.validaNull(cargoExercido)){
                            return;
                        }
                        while (Usuarios.validaEmpty(cargoExercido)){
                            cargoExercido = JOptionPane.showInputDialog("DIGITE UM CARGO EXERCÍDO VÁLIDO: ");
                        }
                        String departamento = JOptionPane.showInputDialog("DEPARTAMENTO: ");
                        if(Usuarios.validaNull(departamento)){
                            return;
                        }
                        while (Usuarios.validaEmpty(departamento)){
                            departamento = JOptionPane.showInputDialog("DIGITE UM DEPARTAMENTO VÁLIDO: ");
                        }
                        do {
                            email = JOptionPane.showInputDialog("EMAIL INSTITUCIONAL");
                            Servidores servidorTemp = new Servidores(nome, email, telefone, senha, matricula, cargoExercido, departamento);
                            if (servidorTemp.validaEmail(matricula, email)) {
                                listaUsuarios.add(servidorTemp);
                                emailValido = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "EMAIL INVÁLIDO.");
                            }
                        } while (!emailValido);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
                        parar = 1;
                        break;
                }
            }
        }while(parar == 1);
    }

    //VALIDA LOGIN
    private static void login(){
        boolean logado = false;
        while (!logado) {
            tempLogin = JOptionPane.showInputDialog("MATRÍCULA: ");
            if(Usuarios.validaNull(tempLogin)){
                return;
            }
            tempSenha = JOptionPane.showInputDialog("SENHA: ");
            if(Usuarios.validaNull(tempSenha)){
                return;
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
    }

    public static void mostrarEspacosFisicos() {

        //Criar Matriz com os horários
        String[] dias = {"Seg", "Ter", "Qua", "Qui", "Sex"};
        String[] horarios = {"08:00", "10:00", "12:00", "14:00", "16:00"};
        String[][] horariosPrincipais = new String[6][5];

        for (int coluna = 0; coluna < dias.length; coluna++) {
            horariosPrincipais[0][coluna] = dias[coluna];
        }

        for (int linha = 1; linha < (horarios.length + 1); linha++) {
            for (int coluna = 0
                 ; coluna < dias.length; coluna++) {
                horariosPrincipais[linha][coluna] = horarios[linha - 1];
            }
        }

        // Copiar matriz principal
        String[][] matrizLaboratorio = copiarMatriz(horariosPrincipais);
        String[][] matrizSalaDeAula = copiarMatriz(horariosPrincipais);
        String[][] matrizAuditorio = copiarMatriz(horariosPrincipais);

        Object[] opcoes = {"LABORATÓRIO", "SALA DE AULA", "AUDITÓRIO"};
        int espacoFisico = JOptionPane.showOptionDialog(frame,
                "INFORME O TIPO DE RELAÇÃO COM A INSTITUIÇÃO",
                "ESPAÇOS FÍSICOS",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );
        String diaDaSemana = JOptionPane.showInputDialog(null, "Qual dia da semana você deseja ver os horários \n 1 - Segunda \n 2 - Terça \n 3 - Quarta \n 4 - Quinta \n 5 - Sexta");

        switch (espacoFisico) {
            case 0:
                // Laboratório
                String escolherHorarioLaboratorio = JOptionPane.showInputDialog(mostrarMatriz(matrizLaboratorio, (Integer.parseInt(diaDaSemana) - 1)) + "\n");

                // Cofirmar Matricula e verificar o seu cargo, então, depois, criar o Objeto
                String matriculaConfirmacao = JOptionPane.showInputDialog(" Digite sua matricula para confirmar ");
                while(!matriculaConfirmacao.equals(usuarioLogado.getMatricula())){
                    matriculaConfirmacao = JOptionPane.showInputDialog("Matricula incorreta! Digite novamente");
                }
                if(usuarioLogado instanceof Alunos){
                    //restricoes e criacao do objeto
                } else if (usuarioLogado instanceof Professores) {
                    //restricoes e criacao do objeto
                }else{
                    //restricoes e criacao do objeto
                }

                break;
            case 1:
                // Sala de aula
                String escolherHorarioSalaDeAula = JOptionPane.showInputDialog(mostrarMatriz(matrizSalaDeAula, (Integer.parseInt(diaDaSemana) - 1)) + "\n");


                if(usuarioLogado instanceof Alunos){
                    //restricoes e criacao do objeto
                } else if (usuarioLogado instanceof Professores) {
                    //restricoes e criacao do objeto
                }else{
                    //restricoes e criacao do objeto
                }
                break;
            case 2:
                // Auditorio
                String escolherHorarioAuditorio = JOptionPane.showInputDialog(mostrarMatriz(matrizAuditorio, (Integer.parseInt(diaDaSemana) - 1)) + "\n");

                if(usuarioLogado instanceof Alunos){
                    //restricoes e criacao do objeto
                } else if (usuarioLogado instanceof Professores) {
                    //restricoes e criacao do objeto
                }else{
                    //restricoes e criacao do objeto
                }
                break;
            default:

        }
    }

    public static StringBuilder mostrarMatriz(String[][] matriz, int coluna) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html> <font color='green'> ");
        for (int j = 0; j < 6; j++) {
            sb.append(String.format("%-10s", matriz[j][coluna]));
        }
        sb.append("\n");
        sb.append("</font></html>");

        return sb;
    }

    public static String[][] copiarMatriz(String[][] original) {
        int linhas = original.length;
        int colunas = original[0].length;
        String[][] nova = new String[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                nova[i][j] = original[i][j];
            }
        }
        return nova;
    }

}