package Likelion.SpringStudy.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

// SimpleUrlAuthenticationFailureHandler 상속받으면 setDefaultFailureUrl을 못 쓴다.
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = "Invalid Username or Password";
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        if (exception instanceof BadCredentialsException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> data = new HashMap<>();
            data.put("/timestamp", Calendar.getInstance().getTime());
            data.put("exception", exception.getMessage());
        } else if (exception instanceof InsufficientAuthenticationException) { // 세션 만료 시
            msg = "Invalid Secret Key";
        }

        setDefaultFailureUrl("/login?error=true&exception="+msg); // controller에서 받음
        super.onAuthenticationFailure(request, response, exception);
    }
}
