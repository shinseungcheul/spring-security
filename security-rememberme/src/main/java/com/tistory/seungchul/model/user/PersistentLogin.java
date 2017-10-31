package com.tistory.seungchul.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table( name = "PERSISTENT_LOGINS")
@Data
public class PersistentLogin {
	
	@Id
	private String series;

	@Column(name="USERNAME", unique=true, nullable=false)
	private String username;
	
	@Column(name="TOKEN", unique=true, nullable=false)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;
	
}
