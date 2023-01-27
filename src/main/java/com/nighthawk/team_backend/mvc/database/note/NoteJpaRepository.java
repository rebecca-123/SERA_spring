package com.nighthawk.team_backend.mvc.database.note;

import com.nighthawk.team_backend.mvc.database.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
    static List<Note> findAllByClub(Club club) {
        // TODO Auto-generated method stub
        return null;
    }

}

