// package com.nighthawk.team_backend.mvc.database.reviews;

// import com.nighthawk.team_backend.mvc.database.ModelRepository;
// import com.nighthawk.team_backend.mvc.database.club.Club;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// import org.commonmark.node.Node;
// import org.commonmark.parser.Parser;
// import org.commonmark.renderer.html.HtmlRenderer;

// import javax.validation.Valid;
// import java.util.List;

// @Controller
// public class ReviewViewController {
// // Autowired enables Control to connect HTML and POJO Object to database
// easily for CRUD
// @Autowired
// private ModelRepository modelRepository;

// @Autowired
// private ReviewJpaRepository reviewRepository;

// public static String convertMarkdownToHTML(String markdown) {
// Parser parser = Parser.builder().build();
// Node document = parser.parse(markdown);
// HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
// return htmlRenderer.render(document);
// }

// @GetMapping("/database/reviews/{id}")
// public String reviews(@PathVariable("id") Long id, Model model) {
// Club club = modelRepository.get(id);
// List<Review> reviews = reviewRepository.findAllByClub(club);
// Review review = new Review();
// review.setClub(club);

// for (Review r : reviews)
// r.setText(convertMarkdownToHTML(r.getText()));

// model.addAttribute("club", club);
// model.addAttribute("reviews", reviews);
// model.addAttribute("review", review);
// return "mvc/database/reviews";
// }

// @PostMapping("/database/reviews")
// public String reviewsAdd(@Valid Review review, BindingResult bindingResult) {
// // back to person ID on redirect
// String redirect = "redirect:/database/reviews/"+review.getClub().getId();

// // database errors
// if (bindingResult.hasErrors()) {
// return redirect;
// }

// // note is saved and person ID is pre-set
// reviewRepository.save(review);

// // Redirect to next step
// return redirect;
// }
// }