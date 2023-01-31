package com.nighthawk.team_backend.mvc.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.nighthawk.team_backend.controllers.*;
import com.nighthawk.team_backend.mvc.jokes.JokesJpaRepository;
import com.nighthawk.team_backend.mvc.database.note.Note;
import com.nighthawk.team_backend.mvc.database.note.NoteJpaRepository;
import com.nighthawk.team_backend.mvc.database.club.Club;
import com.nighthawk.team_backend.mvc.database.club.ClubJpaRepository;
import com.nighthawk.team_backend.mvc.database.reviews.Review;
import com.nighthawk.team_backend.mvc.database.reviews.ReviewJpaRepository;

import java.util.List;
@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {  
    @Autowired JokesJpaRepository jokesRepo;
    @Autowired ClubJpaRepository clubRepo;
    @Autowired NoteJpaRepository noteRepo;
    @Autowired ReviewJpaRepository reviewRepo;


    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {

         

            // Person database is populated with test data
            Club[] clubArray = Club.init();
            for (Club club : clubArray) {
                List<Club> clubFound = clubRepo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(club.getName(), club.getEmail());  // JPA lookup
                if (clubFound.size() == 0) {
                    clubRepo.save(club);  // JPA Save

                    // Each test person starts with a note
                    Club p = clubRepo.findByEmail(club.getEmail());  // pull newly saved club from table
                    String text = "Test " + p.getEmail();
                    Note n = new Note(text, p);  // constructor uses new note as Many-to-One association
                    noteRepo.save(n);  // JPA Save 

                    /* String blank="No Reviews Yet!";
                    Review r = new Review(blank, p);  // constructor uses new review as Many-to-One association
                    reviewRepo.save(r);  // JPA Save */               
                }
            }

        };
    }
}