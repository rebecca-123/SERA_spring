package com.nighthawk.team_backend.mvc.database.note;

import com.nighthawk.team_backend.mvc.database.club.Club;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    @NotNull
    @Column(columnDefinition="TEXT")
    private String text;


    public String toString(){
        return ( "{ \"Club_Id\": "  + id +  ", " + "\"Number of Notes\": "  + 4+ " }" );
     }	

}
