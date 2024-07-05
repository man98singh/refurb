package com.houstondirectauto.refurb.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.houstondirectauto.refurb.entity.RoleEntity;
import com.houstondirectauto.refurb.request.RoleRequest;
import com.houstondirectauto.refurb.service.RoleService;
import com.houstondirectauto.refurb.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;



@Component
public class RoleProxyService {

	private final RoleService roleService;

	/**
	 * Constructor
	 */
	@Autowired
	public RoleProxyService(RoleService roleService) {
		this.roleService = roleService;
	}



	/**
	 * Get List
	 * 
	 * @return list {@link List}
	 */
	public Page<RoleRequest> getAll(int pageNo, int pageSize, String sortBy, String sortDir, boolean pageableStatus) {
		if (!pageableStatus) {
			pageSize = Integer.MAX_VALUE;
		}
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<RoleEntity> dbData =  roleService.getAll(pageable);
        
        List<RoleRequest> data = new ArrayList<>();
        data.addAll(dbData.stream().map(entity -> MapperUtil.convertEntityToModel(entity, RoleRequest.class))
				.collect(Collectors.toList()));
        return new PageImpl<>(data, pageable, dbData.getTotalElements());
	}
}
