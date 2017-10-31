package com.tistory.seungchul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable  {

	private static final long serialVersionUID = -7784087123320225337L;

	@Id
	@Column(name = "OID")
	String oid ;
	
	
}
