package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.GroupConfiguration;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the GroupConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupConfigurationRepository extends MongoRepository<GroupConfiguration, String> {
}
