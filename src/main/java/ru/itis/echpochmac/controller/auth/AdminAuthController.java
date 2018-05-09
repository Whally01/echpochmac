package ru.itis.echpochmac.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.echpochmac.util.URLs;
import ru.itis.echpochmac.payload.LoginRequest;
import ru.itis.echpochmac.security.JwtTokenProvider;
import ru.itis.echpochmac.util.CookieUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AdminAuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;
    @Value("${app.jwtCookieName}")
    private String jwtCookieName;

    @Autowired
    public AdminAuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping(URLs.LOGIN)
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());

        return "signin";
    }

    @PostMapping(URLs.LOGIN)
    public String login(@Valid @ModelAttribute LoginRequest loginRequest, HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signin";
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginOrPhone(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        CookieUtil.create(response, /*"Bearer " +*/ jwtCookieName, jwt, false, 36000000, "localhost");
        return "redirect:/orders/new";
    }

    @RequestMapping(URLs.LOGOUT)
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, jwtCookieName);
        return "redirect:/orders-new.html";
    }
}
