package com.foo.app.controller;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foo.app.dao.AppUserDao;
import com.foo.app.domain.AppUser;

@RestController
public class TestDataController {

	@Autowired
	private AppUserDao appUserDao;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/testjson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String greet(@RequestParam(value = "name", required = false, defaultValue = "World") String name, HttpServletRequest request, HttpServletResponse response) {
	    response.setStatus(HttpServletResponse.SC_OK);
		return "Hello " + name +"! "+ counter.incrementAndGet();
	}

	@RequestMapping(value = "/addtestdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addTestData(@RequestParam(value = "name", required = false, defaultValue = "World") String name, HttpServletRequest request, HttpServletResponse response) {
		for (int i = 1; i <= 10; i++) {
			AppUser user = new AppUser();
			user.setActive(true);
			user.setBirthTime(new Date());
			user.setFirstName("First" + i);
			user.setLastName("Last" + i);
			user.setLastChangedTime(new Date());
			appUserDao.save(user);
		}
	    response.setStatus(HttpServletResponse.SC_OK);
		return "Test data added";
	}
	
}