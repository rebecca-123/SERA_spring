package com.nighthawk.team_backend.mvc.database;

import com.nighthawk.team_backend.mvc.database.club.Club;
import com.nighthawk.team_backend.mvc.database.club.ClubJpaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
This class has an instance of Java Persistence API (JPA)
-- @Autowired annotation. Allows Spring to resolve and inject collaborating beans into our bean.
-- Spring Data JPA will generate a proxy instance
-- Below are some CRUD methods that we can use with our database
*/
@Service
@Transactional
public class ModelRepository implements UserDetailsService {  // "implements" ties ModelRepo to Spring Security
    // Encapsulate many object into a single Bean (Club, Roles, and Scrum)
    @Autowired  // Inject ClubJpaRepository
    private ClubJpaRepository clubJpaRepository;

    // Setup Password style for Database storing and lookup
    @Autowired  // Inject PasswordEncoder
    private PasswordEncoder passwordEncoder;
    @Bean  // Sets up password encoding style
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* UserDetailsService Overrides and maps Club & Roles POJO into Spring Security */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Club club = clubJpaRepository.findByEmail(email); // setting variable user equal to the method finding the username in the database
        if(club==null){
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(club.getEmail(), club.getPassword(), authorities);
    }


    /* Club Section */

    public  List<Club>listAll() {
        return clubJpaRepository.findAllByOrderByNameAsc();
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Club>listLike(String term) {
        return clubJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Club>listLikeNative(String term) {
        String like_term = String.format("%%%s%%",term);  // Like required % rappers
        return clubJpaRepository.findByLikeTermNative(like_term);
    }

    public void save(Club club) {
        club.setPassword(passwordEncoder.encode(club.getPassword()));
        clubJpaRepository.save(club);
    }

    public Club get(long id) {
        return (clubJpaRepository.findById(id).isPresent())
                ? clubJpaRepository.findById(id).get()
                : null;
    }

    public Club getByEmail(String email) {
        return (clubJpaRepository.findByEmail(email));
    }

    public void delete(long id) {
        clubJpaRepository.deleteById(id);
    }

    public void defaults(String password, String roleName) {
        for (Club club: listAll()) {
            if (club.getPassword() == null || club.getPassword().isEmpty() || club.getPassword().isBlank()) {
                club.setPassword(passwordEncoder.encode(password));
            }
        }
    }


}