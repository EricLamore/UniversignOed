package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.ErrorRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the ErrorRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ErrorRequestRepository extends MongoRepository<ErrorRequest, String> {

}
