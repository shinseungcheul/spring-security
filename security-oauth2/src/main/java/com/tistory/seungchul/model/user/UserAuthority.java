package com.tistory.seungchul.model.user;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USER_AUTHORITY")
@AttributeOverride(name = "OID", column = @Column(name  = "OID"))
@Data
public class UserAuthority implements Serializable {

	private static final long serialVersionUID = 1775961916786713755L;

	@Id
	@Column( name = "OID")
	private String oid ;
	
	@Column(name = "USEROID", nullable = false, unique = false )
	private String userOID;
	
	@Column(name = "USERROLE", nullable = false)
	private String userRole;
}
