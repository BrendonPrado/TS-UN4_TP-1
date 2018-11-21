/*
 * This Java source file was generated by the Gradle 'init' task.
 */




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import controller.ContatoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.UsuarioController;
import model.Usuario;

public class ControllerTest {


    Usuario bre;
    UsuarioController controllerUser;
    ContatoController contatoController;

    @BeforeEach
    public void montaCenario() throws IOException {
    this.bre = new Usuario("brendon","batata");

    controllerUser =  new UsuarioController();
    controllerUser.CadastrarNovoUsuario(bre);

    contatoController = new ContatoController();

    }
    
    @Test
    public void verCriptografiaCadastro() throws IOException {
    	File f = new File("users.txt");
    	FileReader fileReader = new FileReader(f);
    	BufferedReader bufferedReader = new BufferedReader(fileReader);
    	
    	String linha1 = bufferedReader.readLine();
    	
    	assertEquals("euhqgrq",linha1.substring(0,linha1.indexOf(';')));
    }

    @Test
    public void  verValidacaoCadastroMenorQue4Campos() throws IOException {
        assertEquals(false, controllerUser.validarCampos("bata","bata","bata"));
    }

    @Test
    public void verValidacaoCadastroMenorQue4Senha() throws IOException{
        assertEquals(false, controllerUser.validarCampos("bata5","bata","bata"));
    }

    @Test
    public void verValidacaoCadastroMaiorQue15Nome() throws IOException{
        assertEquals(false, controllerUser.validarCampos("1234567891234567","batata","batata"));
    }

    @Test
    public void verValidacaoCadastroMaiorQue15Senha() throws IOException{
        assertEquals(false, controllerUser.validarCampos("batata","1234567891234567","1234567891234567"));
    }

    @Test
    public void  verValidacaoCadastroConfirmacaoSenha() throws  IOException{
        assertEquals(false, controllerUser.validarCampos("12345678","12345678","123456789"));
    }

    @Test
    public void verValidacaoCadastroValido() throws IOException{
        assertEquals(true, controllerUser.validarCampos("12345678","12345678","12345678"));
    }

    @Test
    public void verValidacaoCadastroNomeInvalido() throws IOException{
        assertEquals(false, controllerUser.validarCampos("&% gagvav","batata","batata"));
    }

    @Test
    public void verValidacaoCadastroSenhaInvalida() throws IOException{
        assertEquals(false, controllerUser.validarCampos("batata","&% gagvav","&% gagvav"));
    }

    @Test
    public void verValidacaoCadastroNomeJaCadastrado() throws IOException{
        assertEquals(false, controllerUser.validarCampos("brendon","12345","12345"));
    }

    @Test
    public void verCriacaoDadosDeContatoUsuario() throws IOException{
        assertEquals(true,new File("data_ueuhqgrq.txt").isFile());
    }

    @Test
    public void InserirUsuarioValido() throws IOException {
        assertEquals(true,contatoController.validaCampos("brendon","rua fatec","123456789","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoNomeInvalido() throws IOException {
        assertEquals(false,contatoController.validaCampos("6666%ff@","rua fatec","123456789","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoNomeVazio() throws IOException {
        assertEquals(false,contatoController.validaCampos("","rua fatec","123456789","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoTelefoneMenorQue8() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","rua fatec","1234567","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoTelefoneMaiorQue20() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","rua fatec","1234567891234567833333333912","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoTelefoneCaracterInvalido() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","rua fatec","abcdefghijk","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoTelefoneComEspaco() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","rua fatec","             ","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoNomeMenorQue3() throws IOException {
        assertEquals(false,contatoController.validaCampos("b","rua fatec","12345678","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoNomeMaiorQue255() throws IOException {
        assertEquals(false,contatoController.validaCampos("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","rua fatec","12345678","brendon@g.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoEnderecoInvalido() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","brendon@g.com","cadastrar",bre));
    }
    @Test
    public void verValidacaoContatoEmailInvalido() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","@g.com","cadastrar",bre));
    }
    @Test
    public void verValidacaoContatoEmailInvalidoSemArroba() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","bg.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoEmailInvalidoSemPontoDepoisDoArroba() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","b@g","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoEmailInvalidoSemProvedor() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","b@.com","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoEmailInvalidoSemDominio() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","b@g.","cadastrar",bre));
    }

    @Test
    public void verValidacaoContatoEmailVazio() throws IOException {
        assertEquals(false,contatoController.validaCampos("brendon","judas perdeu as botas","12345678","","cadastrar",bre));
    }
    @Test
    public void loginComSucesso() throws IOException {

        assertEquals(this.bre!=null,controllerUser.autenticarLogin("brendon","batata")!=null);
    }
    @Test
    public void loginFalhoUsuarioNaoExistente() throws IOException {

        assertEquals(true,controllerUser.autenticarLogin("brendon1","batata1")==null);
    }
    @Test
    public void loginFalhoUsuarioSenhaIncorreta() throws IOException {
        assertEquals(true,controllerUser.autenticarLogin("brendon","batata1")==null);
    }


}
