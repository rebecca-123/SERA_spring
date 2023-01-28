package com.nighthawk.team_backend.mvc.database.club;

// import com.nighthawk.team_backend.mvc.database.role.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Club Purpose (2 to 50 chars)")
    private String purpose;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 30, message = "Club President (2 to 30 chars)")
    private String president;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Staff Advisor (2 to 50 chars)")
    private String advisor;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Meeting Time and Location (2 to 50 chars)")
    private String meeting;

    @Size(min = 2, max = 50, message = "Additional Info (2 to 50 chars)")
    private String info;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 1, max = 1, message = "Official Club? (Y or N)")
    private String official;

    // Initializer used when setting database from an API
    public Club(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}