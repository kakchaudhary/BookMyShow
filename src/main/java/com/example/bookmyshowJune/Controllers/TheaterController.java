package com.example.bookmyshowJune.Controllers;

import com.example.bookmyshowJune.Dtos.RequestDto.TheaterEntryDto;
import com.example.bookmyshowJune.Dtos.RequestDto.TheaterSeatsEntryDto;
import com.example.bookmyshowJune.Models.Theater;
import com.example.bookmyshowJune.Services.TheaterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterServices theaterServices;

    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterEntryDto theaterEntryDto){

        return theaterServices.addTheater(theaterEntryDto);
    }

    // For Adding Multiple Theaters at One Call By Kaushik 
    @PostMapping("/addMultiple")
    public String addTheaters(@RequestBody List<TheaterEntryDto> theaterEntryDtos) {
        StringBuilder response = new StringBuilder();
        for (TheaterEntryDto theaterEntryDto : theaterEntryDtos) {
            response.append(theaterEntryDto.getName()+" ").append(theaterServices.addTheater(theaterEntryDto)).append("\n");
        }
        return response.toString();
    }

    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody TheaterSeatsEntryDto entryDto){

        return theaterServices.addTheaterSeats(entryDto);

    }

    @GetMapping("/showTime")
    public List<Theater> listOfTheatersShowingParticularTime(@RequestParam LocalTime showTime) {

        List<Theater> theaters = theaterServices.listOfTheatersShowingParticularTime(showTime);

        return theaters;
    }

}

