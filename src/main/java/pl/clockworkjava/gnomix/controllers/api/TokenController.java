package pl.clockworkjava.gnomix.controllers.api;

import antlr.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.clockworkjava.gnomix.security.SecurityUtils;
import pl.clockworkjava.gnomix.security.model.User;
import pl.clockworkjava.gnomix.security.model.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class TokenController {

    private UserRepository userRepository;

    @Autowired
    public TokenController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("api/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = SecurityUtils.getAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedToken = verifier.verify(refreshToken);
                String username = decodedToken.getSubject();
                User user = this.userRepository.findByUsername(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (15 * 60 * 1000)))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.getRoles().stream().map( role -> {
                            return "ROLE_"+role;
                        }).collect(Collectors.toList()))
                        .sign(algorithm);

                response.addHeader("access_token", accessToken);
                response.addHeader("refresh_token", refreshToken);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            } catch (Exception exception) {
                log.error("Error while logging in {}", exception.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", exception.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing refresh token in the header");
        }
    }


}