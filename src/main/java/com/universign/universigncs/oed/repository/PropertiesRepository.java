package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.Properties;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Properties entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropertiesRepository extends MongoRepository<Properties, String> {
}
