package com.houstondirectauto.refurb.proxy;

import com.houstondirectauto.refurb.entity.VendorEntity;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.Vendor;
import com.houstondirectauto.refurb.service.VendorService;
import com.houstondirectauto.refurb.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
@Slf4j
public class VendorProxyService {

	private final VendorService vendorService;


	@Autowired
	public VendorProxyService(VendorService vendorService) {
		this.vendorService = vendorService;
	}


	public Vendor create(Vendor dto) throws BadRequestException {
		VendorEntity entity = MapperUtil.convertModelToEntity(dto, VendorEntity.class);
		return MapperUtil.convertEntityToModel(vendorService.create(entity), Vendor.class);
	}

	public Vendor update(Integer id, Vendor dto) throws EntityNotFoundException {
		VendorEntity dbEntity = vendorService.findById(id);
		if (Objects.nonNull(dto.getFirstName())) {
			dbEntity.setFirstName(dto.getFirstName());
		}
		if (Objects.nonNull(dto.getLastName())) {
			dbEntity.setLastName(dto.getLastName());
		}
		if (Objects.nonNull(dto.getEmail())) {
			dbEntity.setEmail(dto.getEmail());
		}
		if (Objects.nonNull(dto.getPhoneNumber())) {
			dbEntity.setPhoneNumber(dto.getPhoneNumber());
		}
		if (Objects.nonNull(dto.getMobileNumber())) {
			dbEntity.setMobileNumber(dto.getMobileNumber());
		}
		if (Objects.nonNull(dto.getCity())) {
			dbEntity.setCity(dto.getCity());
		}
		if (Objects.nonNull(dto.getState())) {
			dbEntity.setState(dto.getState());
		}
		if (Objects.nonNull(dto.getCountry())) {
			dbEntity.setCountry(dto.getCountry());
		}
		if (Objects.nonNull(dto.getFax())) {
			dbEntity.setFax(dto.getFax());
		}
		if (Objects.nonNull(dto.getTitle())) {
			dbEntity.setTitle(dto.getTitle());
		}
		if (Objects.nonNull(id)) {
			dbEntity.setId(id);
		}
		vendorService.update(id,  dbEntity);
		return dto;
	}

	public void delete(Integer id) throws EntityNotFoundException {
		vendorService.delete(id);
	}

	public Page<Vendor> getAll(int pageNo, int pageSize, String sortBy, String sortDir, boolean pageableStatus) {
		if (!pageableStatus) {
			pageSize = Integer.MAX_VALUE;
		}

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<VendorEntity> dtos =  vendorService.getAll(pageable);

		List<Vendor> arrayLists = new ArrayList<>();
		arrayLists.addAll(dtos.stream().map(entity -> MapperUtil.convertEntityToModel(entity, Vendor.class))
				.collect(Collectors.toList()));
		return new PageImpl<>(arrayLists, pageable, dtos.getTotalElements());
	}

	public Vendor getById(Integer id) throws EntityNotFoundException {
		return MapperUtil.convertEntityToModel(vendorService.findById(id), Vendor.class);
	}


}
