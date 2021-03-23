package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.CheckResult;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CheckResult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CheckResultRepository extends MongoRepository<CheckResult, String> {
}
