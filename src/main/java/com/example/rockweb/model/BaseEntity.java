package com.example.rockweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"}
)
public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = -3235171066808068952L;

	@Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
	private Date createdAt;
	
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
	private Date updatedAt;
}
