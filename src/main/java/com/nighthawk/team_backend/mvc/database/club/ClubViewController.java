package com.nighthawk.team_backend.mvc.database.club;

import com.nighthawk.team_backend.mvc.database.ModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Built using article: https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class ClubViewController {
    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD
    @Autowired
    private ModelRepository repository;

    @GetMapping("/database/club")
    public String club(Model model) {
        List<Club> list = repository.listAll();
        model.addAttribute("list", list);
        return "mvc/database/club";
    }

    /*  The HTML template Forms and ClubForm attributes are bound
        @return - template for club form
        @param - Club Class
    */
    @GetMapping("/database/clubcreate")
    public String clubAdd(Club club) {
        return "mvc/database/clubcreate";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Club object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/database/clubcreate")
    public String clubSave(@Valid Club club, BindingResult bindingResult) {
        // Validation of Decorated ClubForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/database/clubcreate";
        }
        repository.save(club);
        // Redirect to next step
        return "redirect:/database/club";
    }

    @GetMapping("/database/clubupdate/{id}")
    public String clubUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("club", repository.get(id));
        return "mvc/database/clubupdate";
    }

    @PostMapping("/database/clubupdate")
    public String clubUpdateSave(@Valid Club club, BindingResult bindingResult) {
        // Validation of Decorated ClubForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/database/clubupdate";
        }
        repository.save(club);

        // Redirect to next step
        return "redirect:/database/club";
    }

    @GetMapping("/database/clubdelete/{id}")
    public String clubDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:/database/club";
    }

    @GetMapping("/database/club/search")
    public String club() {
        return "mvc/database/club_search";
    }
}