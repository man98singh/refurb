package com.houstondirectauto.refurb.repository;

import com.houstondirectauto.refurb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String string);
	public Optional<UserEntity> findByToken(int token);
	Optional<UserEntity> findByEmailAndPhoneNumber(String email, String phoneNumber);


}
