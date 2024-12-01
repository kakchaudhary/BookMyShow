package com.example.bookmyshowJune.Controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyshowJune.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshowJune.Dtos.RequestDto.ShowSeatsDto;
import com.example.bookmyshowJune.Services.ShowService;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody AddShowDto addShowDto){
        try{
            return showService.addShow(addShowDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    
    // For Adding Multiple Shows at One Call By Kaushik 
    @PostMapping("/addMultiple")
    public String addShows(@RequestBody List<AddShowDto> addShowDtos) {
        StringBuilder response = new StringBuilder();
        for (AddShowDto addShowDto : addShowDtos) {
            try {
                response.append(showService.addShow(addShowDto)).append("\n");
            } catch (Exception e) {
                response.append("Failed to add show: ").append(e.getMessage()).append("\n");
            }
        }
        return response.toString();
    }


    @PostMapping("/associate-seats")
    public String associateSeats(@RequestBody ShowSeatsDto showSeatsDto){

            try{
                return showService.associateShowSeats(showSeatsDto);
            }catch (Exception e){
                return e.getMessage();
            }

    }

    @GetMapping("/most-recommended-movie-name")
    public String getMovieName(AddShowDto addShowDto){

        return showService.getMovieName(addShowDto);
    }

    @GetMapping("/{theaterId}/{movieId}")
    public LocalTime GetShowtime(@PathVariable Integer movieId, @PathVariable Integer theaterId) {

        return showService.getShowtime(movieId, theaterId);
    }

}
