package com.foo.app.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AppUser.class)
public class AppUser_ {
	public static volatile SingularAttribute<AppUser, Integer> id;
	public static volatile SingularAttribute<AppUser, String> firstName;
	public static volatile SingularAttribute<AppUser, String> lastName;
	public static volatile SingularAttribute<AppUser, Date> birthTime;
	public static volatile SingularAttribute<AppUser, Date> lastChangedTime;
	public static volatile SingularAttribute<AppUser, Boolean> active;
}
