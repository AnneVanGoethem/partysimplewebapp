package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;
    private final String venueList[] = {"De Loods", "Bozar", "Roma", "Kuub", "De Cirque"};

    @GetMapping({"/", "/home"})
    /*door bovenstaande annotatie weet Spring dat deze handler een handler is
    voor de request / of /home
    */

    public String home(Model model) {
        //aan home.html een parameter doorgeven van type Model
        //werk zoals in HashMap met key-value pair
        model.addAttribute("mySpecialNummer", mySpecialNumber);

        return "home";
        //home.html (=view) zal gerendered worden
    }

    @GetMapping("/about")
    public String about(Model model) {

        model.addAttribute("mySpecialNummer", mySpecialNumber);
        return "about";
        //about.html zal gerendered worden
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        LocalDateTime myDateObj = LocalDateTime.now();
        //myDateObj = myDateObj.plusDays(1);
        int dayWeek = myDateObj.getDayOfWeek().getValue();
        boolean weekend = false;
        if (dayWeek == 6 || dayWeek == 7) {
            weekend = true;
        }
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        model.addAttribute("dateToday", formattedDate);
        myDateObj = myDateObj.plusDays(30);
        String formattedDate30 = myDateObj.format(myFormatObj);
        model.addAttribute("dateToday", formattedDate);
        model.addAttribute("weekend", weekend);
        model.addAttribute("payDate", formattedDate30);
        return "pay";
    }

    @GetMapping({"/venuedetailsbyindex/{index}", "/venuedetailsbyindex"})
    public String venuedetailsbyindex(Model model, @PathVariable(required = false) Integer index) {
        String message = "Geef een nummer op dat bestaat : tussen 0 en " + venueList.length;
      /*eerste test moet zijn op index != null, anders krijg je exception
        op de volgende testen wanneer index == null */
        if (index != null && index <= venueList.length && index >= 0) {
            model.addAttribute("venue", this.venueList[index]);
            int next = index+1;
            if (next == venueList.length)
                next = 0;
            model.addAttribute("next", next);
            int prev = index-1;
            if (prev == -1)
                prev = venueList.length - 1;
            model.addAttribute("prev", prev);
            model.addAttribute("showNextPrev", true);
        } else {
            model.addAttribute("venue", message);
            model.addAttribute("showNextPrev", false);
        }
        return "venuedetailsbyindex";
    }

    @GetMapping({"/venuedetails/{venueName}", "/venuedetails"})
    public String venuedetails(Model model, @PathVariable(required = false) String venueName) {
        model.addAttribute("venueName", (venueName != null) ? venueName : "no venue choosen");

        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        model.addAttribute("venues", venueList);
        return "venuelist";
    }
}
