package com.orangescout.Orange.Scout.security;

import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        private final UserRepository userRepository;

        public UserDetailsServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            // Busca o usuário pelo username (e.g., email)
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with Email: " + email));

            // Converte o objeto User em UserDetails
            System.out.println("O email extraído do user: "+ user.getEmail());
            System.out.println("O nome extraído do user: "+ user.getUsername());
            return new MyUserDetails(user);
        }
    }
