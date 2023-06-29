package leomaia.musicmananger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.io.*;

/**
 *
 * @author leoma
 */
public class Login {
    private static final String SECRET_KEY_SPEC = "AES";
    private static final String SECRET_KEY_FACTORY = "PBKDF2WithHmacSHA256";
    private static final int KEY_SIZE = 256;
    private static final int ITERATIONS = 65536;
    private static final String SALT = "ThisIsASalt";
    
    private static SecretKey gerarChave() throws Exception {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY);
        KeySpec keySpec = new PBEKeySpec(SALT.toCharArray(), SALT.getBytes(), ITERATIONS, KEY_SIZE);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        return new SecretKeySpec(secretKey.getEncoded(), SECRET_KEY_SPEC);
    }
    
    public static void salvarCredenciais(String login, String senha, boolean isAdmin) {
        try {
            SecretKey secretKey = gerarChave();

            Cipher cipher = Cipher.getInstance(SECRET_KEY_SPEC);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedLogin = cipher.doFinal(login.getBytes());
            byte[] encryptedSenha = cipher.doFinal(senha.getBytes());
            byte[] encryptedIsAdmin = cipher.doFinal(String.valueOf(isAdmin).getBytes());

            try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("login.enc", true))) {
                outputStream.writeInt(encryptedLogin.length);
                outputStream.write(encryptedLogin);
                outputStream.writeInt(encryptedSenha.length);
                outputStream.write(encryptedSenha);
                outputStream.writeInt(encryptedIsAdmin.length);
                outputStream.write(encryptedIsAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean verificarCredenciais(String login, String senha) {
        try (DataInputStream input = new DataInputStream(new FileInputStream("login.enc"))) {
            while (input.available() > 0) {
                int loginLength = input.readInt();
                byte[] encryptedLogin = new byte[loginLength];
                input.readFully(encryptedLogin);

                int senhaLength = input.readInt();
                byte[] encryptedSenha = new byte[senhaLength];
                input.readFully(encryptedSenha);

                int isAdminLength = input.readInt();
                byte[] encryptedIsAdmin = new byte[isAdminLength];
                input.readFully(encryptedIsAdmin);

                SecretKey secretKey = gerarChave();

                Cipher cipher = Cipher.getInstance(SECRET_KEY_SPEC);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);

                String decryptedLogin = new String(cipher.doFinal(encryptedLogin));
                String decryptedSenha = new String(cipher.doFinal(encryptedSenha));
                boolean isAdmin = Boolean.parseBoolean(new String(cipher.doFinal(encryptedIsAdmin)));

                if (decryptedLogin.equals(login) && decryptedSenha.equals(senha)) {
                    //System.out.println("Aprovado\n");
                    return true; // Autenticação bem-sucedida
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Reprovado\n");
        return false; // Autenticação inválida
    }
    
    public static void imprimirUsuarios() {
        try (DataInputStream input = new DataInputStream(new FileInputStream("login.enc"))) {
            while (input.available() > 0) {
                int loginLength = input.readInt();
                byte[] encryptedLogin = new byte[loginLength];
                input.readFully(encryptedLogin);

                int senhaLength = input.readInt();
                byte[] encryptedSenha = new byte[senhaLength];
                input.readFully(encryptedSenha);

                int isAdminLength = input.readInt();
                byte[] encryptedIsAdmin = new byte[isAdminLength];
                input.readFully(encryptedIsAdmin);

                SecretKey secretKey = gerarChave();

                Cipher cipher = Cipher.getInstance(SECRET_KEY_SPEC);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);

                String decryptedLogin = new String(cipher.doFinal(encryptedLogin));
                String decryptedSenha = new String(cipher.doFinal(encryptedSenha));
                boolean isAdmin = Boolean.parseBoolean(new String(cipher.doFinal(encryptedIsAdmin)));

                System.out.println("Login: " + decryptedLogin);
                System.out.println("Senha: " + decryptedSenha);
                System.out.println("isAdmin: " + isAdmin);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    
    }
    
    public static void removerCadastro(String login) {
        try {
            File inputFile = new File("login.enc");
            File tempFile = new File("temp.enc");

            try (DataInputStream input = new DataInputStream(new FileInputStream(inputFile));
                 DataOutputStream output = new DataOutputStream(new FileOutputStream(tempFile))) {

                while (input.available() > 0) {
                    int loginLength = input.readInt();
                    byte[] encryptedLogin = new byte[loginLength];
                    input.readFully(encryptedLogin);

                    int senhaLength = input.readInt();
                    byte[] encryptedSenha = new byte[senhaLength];
                    input.readFully(encryptedSenha);

                    int isAdminLength = input.readInt();
                    byte[] encryptedIsAdmin = new byte[isAdminLength];
                    input.readFully(encryptedIsAdmin);

                    SecretKey secretKey = gerarChave();

                    Cipher cipher = Cipher.getInstance(SECRET_KEY_SPEC);
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);

                    String decryptedLogin = new String(cipher.doFinal(encryptedLogin));

                    if (!decryptedLogin.equals(login)) {
                        output.writeInt(loginLength);
                        output.write(encryptedLogin);
                        output.writeInt(senhaLength);
                        output.write(encryptedSenha);
                        output.writeInt(isAdminLength);
                        output.write(encryptedIsAdmin);
                    }
                }
            }

            // Remove o arquivo original
            if (inputFile.delete()) {
                // Renomeia o arquivo temporário para o nome original
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Erro ao renomear o arquivo temporário");
                }
            } else {
                System.out.println("Erro ao remover o arquivo original");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
