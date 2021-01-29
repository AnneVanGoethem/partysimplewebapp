package be.thomasmore.party;
//package naam = omgekeerde domeinnaam van uw organisatie plus party

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//annotation : gebruik Spring magic om applicatie op te starten
public class PartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartyApplication.class, args);
    }

}
