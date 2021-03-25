package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.Group;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Group entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
}
