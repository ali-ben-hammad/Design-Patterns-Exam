package enset.ali.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Aspect
@Component
public class SecurityAspect {
    private static final String ADMIN_PASSWORD = "admin";
    private boolean isAuthenticated = false;
    private Scanner scanner = new Scanner(System.in);

    @Before("@annotation(RequiresAuthentication)")
    public void checkSecurity(JoinPoint joinPoint) {
        if (!isAuthenticated) {
            System.out.print("🔐 Please enter password to execute the application: ");
            String password = scanner.nextLine();

            if (!ADMIN_PASSWORD.equals(password)) {
                throw new SecurityException("❌ Invalid password. Access denied.");
            }
            isAuthenticated = true;
            System.out.println("✅ Access granted. Executing application...\n");
        }
    }
}