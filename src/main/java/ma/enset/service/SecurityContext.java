package ma.enset.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SecurityContext {
    private static String username="";
    private static String password="";
    private static String[] roles= {};

    public static void authenticate(String u, String p, String[] r){
        if(u.equals("admin")&&p.equals("admin")){
            username=u;
            password=p;
            roles=r;
        }
        else {
            throw new RuntimeException("Bad Credentials");
        }
    }

    public static boolean hasRole(String r){
        for(String role:roles){
            if (role.equals(r)){
                return true;
            }
        }
        return false;
    }
}
