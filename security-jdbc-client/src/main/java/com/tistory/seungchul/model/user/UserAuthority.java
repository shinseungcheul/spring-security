package com.tistory.seungchul.model.user;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tistory.seungchul.model.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "USER_AUTHORITY")
@AttributeOverride(name = "UID", column = @Column(name  = "UID"))
@Data
public class UserAuthority extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1775961916786713755L;

	@Column(name = "USERUID", nullable = false, unique = false )
	private String userUID;
	
	@Column(name = "USERROLE", nullable = false)
	private String userRole;
}
