package com.universign.universigncs.oed.repository;

import com.universign.universigncs.oed.domain.CertifiedUser;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the CertifiedUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertifiedUserRepository extends MongoRepository<CertifiedUser, String> {

}
