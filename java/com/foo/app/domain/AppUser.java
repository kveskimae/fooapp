package com.foo.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@Entity
@Table(schema="fooappdata")
public class AppUser implements DomainObject {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String firstName;

	private String lastName;
	
	private Date birthTime;
    
	private Date lastChangedTime;
    
	private Boolean active;

	public AppUser() {
	}

	@Override
	public String toString() {
	     return (new ReflectionToStringBuilder(this)).toString();
	}

	@Id
	@SequenceGenerator(name="USERID_GENERATOR", sequenceName="APP_USER_ID_SEQ", schema="fooappdata", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="USERID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
