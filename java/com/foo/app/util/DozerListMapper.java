package com.foo.app.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class DozerListMapper {
	
	public static <T,S> List<T> mapListToList(List<S> objects, Class<T> newObjectClass, Mapper mapper) {
		final List<T> newObjects = new ArrayList<T>();
		for (S s : objects) {
		    newObjects.add(mapper.map(s, newObjectClass));
		}
		return newObjects;
	}

}
