package ru.itis.echpochmac.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.echpochmac.exception.ResourceNotFoundException;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginOrPhone_number) throws UsernameNotFoundException {

        User user = userRepository.findByLoginOrPhone(loginOrPhone_number, loginOrPhone_number)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with login or phone_number : " + loginOrPhone_number)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
