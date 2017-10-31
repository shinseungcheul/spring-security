package com.tistory.seungchul.model.user;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tistory.seungchul.model.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "USER_AUTHORITY")
@AttributeOverride(name = "OID", column = @Column(name  = "OID"))
@Data
public class UserAuthority extends BaseEntity {

	private static final long serialVersionUID = 1775961916786713755L;

	@Column(name = "USEROID", nullable = false, unique = false )
	private String userOID;
	
	@Column(name = "USERROLE", nullable = false)
	private String userRole;
}
