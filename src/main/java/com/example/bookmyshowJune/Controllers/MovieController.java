package com.example.bookmyshowJune.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyshowJune.Dtos.RequestDto.MovieEntryDto;
import com.example.bookmyshowJune.Exception.MovieNotFound;
import com.example.bookmyshowJune.Services.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieEntryDto movieEntryDto){

        return movieService.addMovie(movieEntryDto);
    }
    
    
    // For Adding Multiple Movies at One Call By Kaushik 
    @PostMapping("/addMultiple")
    public String addMovies(@RequestBody List<MovieEntryDto> movieEntryDtos){
        StringBuilder response = new StringBuilder();
        for (MovieEntryDto movieEntryDto : movieEntryDtos) {
            response.append(movieEntryDto.getMovieName()+" ").append(movieService.addMovie(movieEntryDto)).append("\n");
        }
        return response.toString();
    }


    @GetMapping("/movieNamewithTheMaximumNumberOfShows")
    public String movieNamewithTheMaximumNumberOfShows() {

        return movieService.movieNamewithTheMaximumNumberOfShows();
    }

    @GetMapping("/{totalCollectionByParticularMovie}")
    public int totalCollectionByParticularMovie(@PathVariable("totalCollectionByParticularMovie") Integer movieId) throws MovieNotFound {

        return movieService.totalCollectionByParticularMovie(movieId);
    }

    @GetMapping("/getMovie/{movieId}")
    public String checkMovieStatus(@PathVariable String movieId) throws MovieNotFound {

        int movieId1  = Integer.parseInt(movieId);
        return movieService.checkMovieStatus(movieId1);
    }
}
