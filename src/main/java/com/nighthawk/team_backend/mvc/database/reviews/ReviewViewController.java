package com.nighthawk.team_backend.mvc.database.reviews;

import com.nighthawk.team_backend.mvc.database.club.Club;
import com.nighthawk.team_backend.mvc.database.club.ClubDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.List;

@Controller
public class ReviewViewController {

@Autowired
private ClubDetailsService clubRepo;

@Autowired
private ReviewJpaRepository reviewRepository;

public static String convertMarkdownToHTML(String markdown) {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(markdown);
    HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
    return htmlRenderer.render(document);
}

@GetMapping("/database/reviews/{id}")
public ResponseEntity<List<Review>> reviews(@PathVariable("id") Long id) {
    Club club = clubRepo.get(id);
    if (club != null) { // Good ID
        List<Review> reviews = reviewRepository.findAllByClub(club);
        return new ResponseEntity<>(reviews, HttpStatus.OK); // OK HTTP response: status code, headers, and body
    }

    // Bad ID
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}

@PostMapping("/database/addreview/{id}")
public ResponseEntity<Object> reviewsAdd(@RequestParam("text") String text,
        @PathVariable("id") Long clubId) {
    
    Club club = clubRepo.get(clubId);
    if (club != null) { // Good ID
        Review review = new Review(text, club);
        reviewRepository.save(review);
        return new ResponseEntity<>("New review is created successfully for Club:" + clubId, HttpStatus.CREATED);
    }

    // Bad ID
    return new ResponseEntity<>("Club not found in club list - Club:" + clubId, HttpStatus.CREATED);
}

}