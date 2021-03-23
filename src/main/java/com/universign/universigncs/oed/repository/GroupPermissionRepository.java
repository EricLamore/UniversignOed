package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.GroupPermission;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the GroupPermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupPermissionRepository extends MongoRepository<GroupPermission, String> {
}
