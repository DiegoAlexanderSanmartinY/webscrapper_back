package com.ds.webscrapper.repository;

import com.ds.webscrapper.models.Webpage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface WebpageRepository extends CrudRepository<Webpage, Integer> {

    @Query("SELECT w FROM Webpage w WHERE w.domain LIKE %:text% OR w.description LIKE %:text% OR w.title LIKE %:text% OR w.url LIKE %:text% order by w.rank")
    List<Webpage> findByText(@Param("text") String text);

    Webpage findByUrl(String url);
}
