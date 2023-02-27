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

    // /*
    // * DELETE individual Club using ID
    // */
    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<Club> deleteClub(@PathVariable long id) {
    // Optional<Club> optional = jparepository.findById(id);
    // if (optional.isPresent()) { // Good ID
    // Club club = optional.get(); // value from findByID
    // jparepository.deleteById(id); // value from findByID
    // return new ResponseEntity<>(club, HttpStatus.OK); // OK HTTP response: status
    // code, headers, and body
    // }
    // // Bad ID
    // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }

    /*
     * DELETE individual Club using ID
     */
    @PostMapping("/delete/{id}")
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

    @PostMapping("/post")
    public ResponseEntity<Object> postClub(@RequestBody Club club) {
        // A club object WITHOUT ID will create a new record with default roles as
        // student
        repository.save(club);
        return new ResponseEntity<>(club.getEmail() + " was created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateClub(@PathVariable long id, @RequestBody Club club) {
        Optional<Club> optional = jparepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Club oldClub = optional.get(); // value from findByID
            // update attributes of the club
            oldClub.setEmail(club.getEmail());
            oldClub.setPassword(club.getPassword());
            oldClub.setName(club.getName());
            oldClub.setTypes(club.getTypes());
            oldClub.setPurpose(club.getPurpose());
            oldClub.setPresident(club.getPresident());
            oldClub.setAdvisor(club.getAdvisor());
            oldClub.setMeeting(club.getMeeting());
            oldClub.setInfo(club.getInfo());
            oldClub.setOfficial(club.getOfficial());
            repository.save(oldClub);
            return new ResponseEntity<>(oldClub.getName() + " was updated successfully", HttpStatus.OK); // OK HTTP
            // response: status
            // code, headers,
            // and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * The clubSearch API looks across database for partial match to term (k,v)
     * passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> clubSearch(@RequestBody final Map<String, String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");
        //these "terms" are same as used in frontend 

        // JPA query to filter on term
        //origionally was jsut list like but mort said by native is better because more precise 
        List<Club> list = repository.listLikeNative(term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
