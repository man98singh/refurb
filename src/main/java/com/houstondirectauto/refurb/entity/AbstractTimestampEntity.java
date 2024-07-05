package com.houstondirectauto.refurb.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractTimestampEntity {

	
	@CreatedDate
	@DateTimeFormat(pattern="MM.dd.yyyy HH:mm:ss")
	@JsonFormat(pattern = "MM.dd.yyyy HH:mm:ss")
	@Column(name = "created_at", updatable = false)
	public Date createdAt;
	
	@LastModifiedDate
	@DateTimeFormat(pattern="MM.dd.yyyy HH:mm:ss")
	@JsonFormat(pattern = "MM.dd.yyyy HH:mm:ss")
	@Column(name = "updated_at")
	public Date updatedAt;

}
