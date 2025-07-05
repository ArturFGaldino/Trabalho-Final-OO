import java.util.ArrayList;
import Entidades.Usuarios;
import Cadastro.loginCadastro;
import Entidades.Alunos;

public class Main {
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    public static int continuar = 1, sair = 0;
    public static Usuarios artur = new Alunos("Artur Fernandes Galdino", "241010923@aluno.unb.br", "61998658594", "1", "241010923", "Engenharia de Software");
    public static Usuarios pedro = new Alunos("Pedro Augusto Macedo Del Castilo", "241025354@aluno.unb.br", "61082859745", "1", "241025354", "Engenharia de Software");
    public static void main(String[] args) throws Exception {
        listaUsuarios.add(artur);
        listaUsuarios.add(pedro);
        do {
            try {
                loginCadastro.loginCadastroUsuario(listaUsuarios);
            } catch (Exception erro) {
                System.out.println(erro.getMessage());
            }
        } while (continuar == 1);
    }
}
