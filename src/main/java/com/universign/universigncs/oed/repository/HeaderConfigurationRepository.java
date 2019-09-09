package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.HeaderConfiguration;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the HeaderConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HeaderConfigurationRepository extends MongoRepository<HeaderConfiguration, String> {

}
