package com.houstondirectauto.refurb.service;

import com.houstondirectauto.refurb.entity.DepartmentEntity;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.repository.DepartmentRepository;
import com.houstondirectauto.refurb.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public DepartmentEntity create(DepartmentEntity entity) {
		return departmentRepository.save(entity);
	}

	public DepartmentEntity update(Integer id, DepartmentEntity entity) throws EntityNotFoundException {
		DepartmentEntity dbEntity = findById(id);
		return departmentRepository.save(entity);
	}

	public void delete(Integer id) throws EntityNotFoundException {
		Optional<DepartmentEntity> entity = departmentRepository.findById(id);
		if (entity.isPresent()) {
			departmentRepository.deleteById(id);
		}
	}

	public DepartmentEntity findById(Integer id) throws EntityNotFoundException {
		DepartmentEntity entity = departmentRepository.findById(id).orElse(null);
		if (Objects.isNull(entity)) {
			log.error("Vendor with id {} not found.", id);
			throw new EntityNotFoundException(Constants.VENDOR_NOT_EXIST);
		}
		return entity;
	}

	public Page<DepartmentEntity> getAll(Pageable pageable) {
		return departmentRepository.findAll(pageable);
	}



}
