package com.tistory.seungchul.model.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.tistory.seungchul.model.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "USER_ENTITY")
@AttributeOverride(name = "UID", column = @Column(name  = "UID"))
@Data
public class UserEntity extends BaseEntity implements Serializable  {

	private static final long serialVersionUID = -3017887802298899188L;
	
	@Column( name = "USERID", unique = true, nullable = false)
	private String userId;
	
	@Column( name = "PASSWORD", nullable = false)
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USERUID")
	private List<UserAuthority> userAuthorities;
	

	@PrePersist
	public void onPrePersist() {

	}
	
	
	@PostPersist
	public void onPostPersist() {
		
	}
}
