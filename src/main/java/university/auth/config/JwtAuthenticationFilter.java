package university.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * TEMPORAIREMENT DÉSACTIVÉ POUR TESTER
 * Ce filtre sera réactivé après validation des endpoints de base
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Pour le moment, on laisse TOUT passer sans vérification
        // Cela nous permet de tester register et login
        System.out.println("🔓 JWT Filter - Letting through: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}