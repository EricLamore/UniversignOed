package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.Link;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Link entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LinkRepository extends MongoRepository<Link, String> {

}
