package com.example.bookmyshowJune.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bookmyshowJune.Models.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByLocation(String location);

    @Query("SELECT COUNT(DISTINCT t.location) FROM Theater t WHERE t.name = :theaterName")
    int findUniqueLocations(@Param("theaterName") String theaterName);
}
