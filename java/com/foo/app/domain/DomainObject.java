package com.foo.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public interface DomainObject extends Serializable {	

	Integer getId();
	
	void setId(Integer id);

}