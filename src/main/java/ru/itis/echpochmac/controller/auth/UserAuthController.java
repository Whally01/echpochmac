package ru.itis.echpochmac.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.util.URLs;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.RoleName;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.JwtAuthenticationResponse;
import ru.itis.echpochmac.payload.LoginRequest;
import ru.itis.echpochmac.payload.SignUpRequest;
import ru.itis.echpochmac.security.JwtTokenProvider;
import ru.itis.echpochmac.service.impl.RoleService;
import ru.itis.echpochmac.service.impl.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping(URLs.API)
public class UserAuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public UserAuthController(AuthenticationManager authenticationManager, RoleService roleService, UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(URLs.LOGIN)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("BindinErrors: ", bindingResult.getAllErrors());
            return new ResponseEntity<>(new ApiResponse(false, "Errors"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginOrPhone(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(URLs.SIGNUP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity<>(new ApiResponse(false, "Login is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity<>(new ApiResponse(false, "Phone number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getLogin(),
                signUpRequest.getPhone(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.findByName(RoleName.ROLE_ORDERER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{login}")
                .buildAndExpand(result.getLogin()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
