package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.Administrator;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Administrator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdministratorRepository extends MongoRepository<Administrator, String> {
}
