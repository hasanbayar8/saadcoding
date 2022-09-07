package com.example.saatcoding.requirement.repository;

import com.example.saatcoding.requirement.main.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository
        extends JpaRepository<Content, Long>{


    /* SELECT * FROM content WHERE name = ? */
    @Query ("SELECT s FROM Content s WHERE s.name = ?1 ")
    Optional<Content> findContentByName(String name);

    Optional<Content> findContentByVideoUrl(String videoUrl);

    Content findOneById(Long contentId);
}
