package backend.security;

import backend.model.Usuario;

public class JwtUtil {

    
    public static String gerarToken(Usuario usuario) {
        
        return "token-gerado-temporario-para-" + usuario.getEmail();
    }
}