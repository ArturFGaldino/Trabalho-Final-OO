import java.util.ArrayList;
import Entidades.Usuarios;
import Cadastro.loginCadastro;
import Entidades.Alunos;

public class Main {
    private static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    private static int continuar = 1;
    private static Usuarios artur = new Alunos("Artur Fernandes Galdino", "241010923@aluno.unb.br", "61998658594", "1",
            "241010923", "Engenharia de Software");
    private static Usuarios pedro = new Alunos("Pedro Augusto Macedo Del Castilo", "241025354@aluno.unb.br",
            "61082859745", "1", "241025354", "Engenharia de Software");

    public static void main(String[] args) throws Exception {
        listaUsuarios.add(artur);
        listaUsuarios.add(pedro);
        do {
            // Chama o metdo para logar ou cadastrar na classe LoginCadastro
            loginCadastro.loginCadastroUsuario(listaUsuarios);
            System.exit(0);
        } while (continuar == 1);
    }
}
