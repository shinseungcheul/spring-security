package com.tistory.seungchul.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USER_ENTITY")
@AttributeOverride(name = "OID", column = @Column(name  = "OID"))
@Data
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = -3017887802298899188L;
	
	private String userId;
	
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USEROID")
	private List<UserAuthority> userAuthorities;
	
//	private Timestamp createdAt;
	
//	private Timestamp last_used;

	@PrePersist
	public void onPrePersist() {

	}
	
	
	
	@PostPersist
	public void onPostPersist() {
		
	}
}
