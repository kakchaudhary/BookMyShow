package com.example.bookmyshowJune.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bookmyshowJune.Models.Show;
import com.example.bookmyshowJune.Models.Theater;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    @Query(value = "select movie_id from shows group by movie_id order by count(*) desc limit 1;",nativeQuery = true)
    public Integer getMostShowedMovie(Date checkDate);
    
    @Query("SELECT s FROM Show s WHERE s.movie.name = :movieName AND s.theater.name = :theaterName")
    List<Show> findShowTimes(@Param("movieName") String movieName, @Param("theaterName") String theaterName);
    
    @Query("SELECT DISTINCT s.theater FROM Show s WHERE s.time = :showTime")
    List<Theater> findTheatersByShowTime(@Param("showTime") LocalTime showTime);

}
