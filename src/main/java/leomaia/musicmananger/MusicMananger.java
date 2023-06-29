package leomaia.musicmananger;

import javax.swing.UIManager;
import leomaia.musicmananger.GUI.App;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author leoma
 */
public class MusicMananger {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException  {
        
        /*
        
        +---------------------------------------+
                  USUARIOS PARA TESTE          
        +---------------------------------------+
          Usuario Adiministrador                
          login: admin1                         
          senha: 1234                           
        +---------------------------------------+
          Usuarios Comum                         
          login: leo                             
          senha: abc                             
        +---------------------------------------+
          login: creusa                          
          senha: xyz                             
        +---------------------------------------+
          login: ana                             
          senha: 0000                            
        +---------------------------------------+

        
        */
        
        javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        
        //Se nao possuir nenhum ADMINISTRADOR cadastrado executar descomentando a linha abaixo:
        //Usuario.cadastroNovoADM("Administrador", "admin1", "1234"); 
        Login.imprimirUsuarios();
        new App().setVisible(true);
        
                  
    }
}

