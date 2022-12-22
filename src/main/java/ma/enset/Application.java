package ma.enset;

import ma.enset.service.SecurityContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ma.enset.service.IMetier;

@ComponentScan(value = {"ma.enset"})
public class Application {
    public static void main(String[] args) {
        try {
            SecurityContext.authenticate("admin","admin",new String[]{"USER"});
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
            IMetier metier = applicationContext.getBean(IMetier.class);
            System.out.println("*****************");
            System.out.println(metier.getClass().getName());
            System.out.println("*****************");
            metier.process();
            System.out.println("Result : " + metier.compute());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
