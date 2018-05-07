package ru.itis.echpochmac.controller.auth;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.echpochmac.payload.JwtAuthenticationResponse;
import ru.itis.echpochmac.payload.LoginRequest;
import ru.itis.echpochmac.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AdminAuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/signin")
    public String login() {
        return "signin";
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateAdmin(@Valid LoginRequest loginRequest, HttpResponse httpResponse) throws URISyntaxException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginOrPhone(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        String location = "redirect:/cafes";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(new URI(location));
        responseHeaders.set("Bearer", jwt);
        return new ResponseEntity<String>( responseHeaders, HttpStatus.OK);
    }
}
