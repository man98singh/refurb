package com.houstondirectauto.refurb.service;

import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.entity.VendorEntity;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.repository.VendorRepository;
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
public class VendorService {

	private final VendorRepository vendorRepository;

	@Autowired
	public VendorService(VendorRepository vendorRepository) {
		super();
		this.vendorRepository = vendorRepository;
	}

	public VendorEntity create(VendorEntity entity) {
		return vendorRepository.save(entity);
	}

	public VendorEntity update(Integer id, VendorEntity entity) throws EntityNotFoundException {
		VendorEntity vendorEntity = findById(id);
		return vendorRepository.save(entity);
	}

	public void delete(Integer id) throws EntityNotFoundException {
		Optional<VendorEntity> entity = vendorRepository.findById(id);
		if (entity.isPresent()) {
			vendorRepository.deleteById(id);
		}
	}

	public VendorEntity findById(Integer id) throws EntityNotFoundException {
		VendorEntity entity = vendorRepository.findById(id).orElse(null);
		if (Objects.isNull(entity)) {
			log.error("Vendor with id {} not found.", id);
			throw new EntityNotFoundException(Constants.VENDOR_NOT_EXIST);
		}
		return entity;
	}

	public Page<VendorEntity> getAll(Pageable pageable) {
		return vendorRepository.findAll(pageable);
	}



}
