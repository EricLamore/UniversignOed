package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.AdminPreferences;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AdminPreferences entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminPreferencesRepository extends MongoRepository<AdminPreferences, String> {
}
