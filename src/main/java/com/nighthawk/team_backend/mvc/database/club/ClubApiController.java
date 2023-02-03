package com.nighthawk.team_backend.mvc.database.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/club")
public class ClubApiController {
    // @Autowired
    // private JwtTokenUtil jwtGen;
    /*
     * #### RESTful API ####
     * Resource: https://spring.io/guides/gs/rest-service/
     */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private ClubDetailsService repository;
    @Autowired
    private ClubJpaRepository jparepository;

    /*
     * GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Club>> getPeople() {
        return new ResponseEntity<>(repository.listAll(), HttpStatus.OK);
    }

    /*
     * GET individual Club using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClub(@PathVariable long id) {
        Optional<Club> optional = jparepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Club club = optional.get(); // value from findByID
            return new ResponseEntity<>(club, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * DELETE individual Club using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Club> deleteClub(@PathVariable long id) {
        Optional<Club> optional = jparepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Club club = optional.get(); // value from findByID
            jparepository.deleteById(id); // value from findByID
            return new ResponseEntity<>(club, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * POST Aa record by Requesting Parameters from URI
     */
    @PostMapping("/post")
    public ResponseEntity<Object> postClub(@RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {
        // A club object WITHOUT ID will create a new record with default roles as
        // student
        Club club = new Club(email, password, name);
        repository.save(club);
        return new ResponseEntity<>(email + " is created successfully", HttpStatus.CREATED);
    }

    /*
     * The clubSearch API looks across database for partial match to term (k,v)
     * passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> clubSearch(@RequestBody final Map<String, String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Club> list = repository.listLike(term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
