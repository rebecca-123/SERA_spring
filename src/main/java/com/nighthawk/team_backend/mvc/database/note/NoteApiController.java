package com.nighthawk.team_backend.mvc.database.note;


// import com.nighthawk.team_backend.mvc.database.ModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.team_backend.mvc.database.ModelRepository;
import com.nighthawk.team_backend.mvc.database.club.Club;

import java.util.*;

@RestController
@RequestMapping("/api/note")
public class NoteApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD
    @Autowired
    private ModelRepository repository;

    /*
    GET List of People
     */
    @GetMapping("/all")
    public ResponseEntity<List<Club>> getPeople() {
        return new ResponseEntity<>(repository.listAll(), HttpStatus.OK);
    }

    /*
    GET individual Person using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClub(@PathVariable long id) {
        return new ResponseEntity<>(repository.get(id), HttpStatus.OK);
    }

    /*
    DELETE individual Person using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClub(@PathVariable long id) {
        repository.delete(id);
        return new ResponseEntity<>( ""+ id +" deleted", HttpStatus.OK);
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */
    @PostMapping( "/post")
    public ResponseEntity<Object> postClub(@RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam("name") String name) {
        // A club object WITHOUT ID will create a new record with default roles as student
        Club club = new Club(email, password, name);
        repository.save(club);
        return new ResponseEntity<>(email +" is created successfully", HttpStatus.CREATED);
    }

    /*
    The personSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> clubSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // custom JPA query to filter on term
        List<Club> list = repository.listLikeNative(term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}