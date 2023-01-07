/*package com.nighthawk.spring_portfolio.mvc.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorApiController {

    @GetMapping("/{expression}")
    public ResponseEntity<JsonNode> getResult (@PathVariable String expression) throws JsonMappingException, JsonProcessingException {
      // Backend Year Object
      Calculator calc = new Calculator(expression);
    
      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(calc.toString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }
    
}
*/

package com.nighthawk.team_backend.mvc.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Calculator api, endpoint: /api/calculator/
@RestController
@RequestMapping("/api/calculator")
public class CalculatorApiController {
    @GetMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody final String expression) {
        try {
            Calculator calculatedExpression = new Calculator(expression);
            return new ResponseEntity<>(calculatedExpression.toString(), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Error/Parsing Error, check your expression", HttpStatus.BAD_REQUEST);
        }
    }
}

