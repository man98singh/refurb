package com.houstondirectauto.refurb.proxy;

import com.houstondirectauto.refurb.entity.DepartmentEntity;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.Department;
import com.houstondirectauto.refurb.service.DepartmentService;
import com.houstondirectauto.refurb.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Slf4j
public class DepartmentProxyService {

	private final DepartmentService departmentService;


	@Autowired
	public DepartmentProxyService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}


	public Department create(Department dto) throws BadRequestException {
		DepartmentEntity entity = MapperUtil.convertModelToEntity(dto, DepartmentEntity.class);
		return MapperUtil.convertEntityToModel(departmentService.create(entity), Department.class);
	}

	public Department update(Integer id, Department dto) throws EntityNotFoundException {
		DepartmentEntity dbEntity = departmentService.findById(id);

		if (Objects.nonNull(dto.getTitle())) {
			dbEntity.setTitle(dto.getTitle());
		}
		if (Objects.nonNull(id)) {
			dbEntity.setId(id);
		}
		if (Objects.nonNull(dto.getOrders())) {
			dbEntity.setOrders(dto.getOrders());
		}
		departmentService.update(id,  dbEntity);
		return dto;
	}

	public void delete(Integer id) throws EntityNotFoundException {
		departmentService.delete(id);
	}

	public Page<Department> getAll(int pageNo, int pageSize, String sortBy, String sortDir, boolean pageableStatus) {
		if (!pageableStatus) {
			pageSize = Integer.MAX_VALUE;
		}

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<DepartmentEntity> dtos =  departmentService.getAll(pageable);

		List<Department> arrayLists = new ArrayList<>();
		arrayLists.addAll(dtos.stream().map(entity -> MapperUtil.convertEntityToModel(entity, Department.class))
				.collect(Collectors.toList()));
		return new PageImpl<>(arrayLists, pageable, dtos.getTotalElements());
	}

	public Department getById(Integer id) throws EntityNotFoundException {
		return MapperUtil.convertEntityToModel(departmentService.findById(id), Department.class);
	}


}
