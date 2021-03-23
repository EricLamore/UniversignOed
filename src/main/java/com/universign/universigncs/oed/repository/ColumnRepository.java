package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.Column;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Column entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColumnRepository extends MongoRepository<Column, String> {
}
