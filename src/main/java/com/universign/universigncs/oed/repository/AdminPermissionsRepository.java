package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.AdminPermissions;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AdminPermissions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminPermissionsRepository extends MongoRepository<AdminPermissions, String> {
}
