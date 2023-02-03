// package com.nighthawk.team_backend.mvc.database.note;

// // import com.nighthawk.team_backend.mvc.database.ModelRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.nighthawk.team_backend.mvc.database.ModelRepository;
// import com.nighthawk.team_backend.mvc.database.club.Club;

// import java.util.*;

// @RestController
// @RequestMapping("/api/note")
// public class NoteApiController {
// /*
// #### RESTful API ####
// Resource: https://spring.io/guides/gs/rest-service/
// */

// // Autowired enables Control to connect HTML and POJO Object to database
// easily for CRUD
// @Autowired
// private ModelRepository repository;

// /*
// GET List of People

// @GetMapping("/all")
// public ResponseEntity<List<Note>> getClub_note() {
// return new ResponseEntity<>(repository.listAll_note(), HttpStatus.OK);
// }
// */
// /*
// GET individual Person using ID
// */
// @GetMapping("/{id}")
// public ResponseEntity<Club> getClub_note(@PathVariable long id) {
// return new ResponseEntity<>(repository.get(id), HttpStatus.OK);
// }

// /*
// DELETE individual Person using ID

// @DeleteMapping("/delete/{id}")
// public ResponseEntity<Object> delete(@PathVariable long id) {
// repository.delete_note(id);
// return new ResponseEntity<>( ""+ id +" deleted", HttpStatus.OK);
// }
// */
// /*
// POST Aa record by Requesting Parameters from URI
// */

// /*
// The personSearch API looks across database for partial match to term (k,v)
// passed by RequestEntity body
// */

// }