package com.nighthawk.team_backend.mvc.database.club;

// import com.nighthawk.team_backend.mvc.database.role.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.nighthawk.team_backend.mvc.database.note.Note;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Club {
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Club Name (2 to 50 chars)")
    private String name;

    /* 
    @NonNull
    private Note note = new Note("test", this);

    public String getNote(){
        return note.getText();
    }

    public void setNote(String note_input){
        note.setText(note_input);;
    }

    */

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 30, message = "Club President (2 to 30 chars)")
    private String president;

    // Initializer used when setting database from an API
    public Club(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
   


    public static Club[] init() {

        // basics of class construction
        Club gics = new Club();
        gics.setName("Girls in Computer Science");
        gics.setEmail("gics.dnhs@gmail.com");
        gics.setPassword("cs123%!");
       // p1.setNote("hi this is aadya");

        // adding Note to notes collection
     

        Club dnas = new Club();
        dnas.setName("Del Norte Arduino Society");
        dnas.setEmail("arduinoclub.dnhs@gmail.com");
        dnas.setPassword("arduino!");
       // p2.setNote("hi this is prashant");
     

        Club nhs = new Club();
        nhs.setName("National Honors Society");
        nhs.setEmail("nhs@gmail.com");
        nhs.setPassword("nhs123!");
       // p3.setNote("hi this is sirish");
    

        Club optix = new Club();
        optix.setName("Del Norte Optix");
        optix.setEmail("optix@gmail.com");
        optix.setPassword("1234!");
      //  p4.setNote("hi this is avan");



        Club gidas = new Club();
        gidas.setName("Genes in Diseases");
        gidas.setEmail("gidas@gmail.com");
        gidas.setPassword("54321!");
       // p5.setNote("hi this is efhdjwjkf");
  

        // Array definition and data initialization
        Club clubs[] = {gics, dnas, nhs, optix, gidas};
        return(clubs);
    }

}