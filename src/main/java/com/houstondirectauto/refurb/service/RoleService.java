package com.houstondirectauto.refurb.service;

import com.houstondirectauto.refurb.entity.RoleEntity;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.repository.RoleRepository;
import com.houstondirectauto.refurb.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Service
public class RoleService {

	private final RoleRepository roleRepository;

	/**
	 * Constructor
	 * 
	 * @param Repository
	 */

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}


	/**
	 * Get List.
	 * @return List.
	 */
	public Page<RoleEntity> getAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	public RoleEntity findById(Integer id) throws EntityNotFoundException {
		RoleEntity userEntity = roleRepository.findById(id).orElse(null);
		if (Objects.isNull(userEntity)) {
			log.error("User with id {} not found.", id);
			throw new EntityNotFoundException(Constants.USERS_NOT_EXIST);
		}
		return userEntity;
	}

}
