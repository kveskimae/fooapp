package com.foo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.foo.app.domain.AppUser;


public interface AppUserDao extends JpaRepository<AppUser, Integer>, JpaSpecificationExecutor<AppUser> {}
