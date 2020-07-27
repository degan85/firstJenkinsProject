package com.example.demo.common;

import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface CommonDAO {
	
	public void insert(String key, Object obj) throws Exception;
	
	public Object update(String key, Object obj) throws Exception;

	public Object selectList(String key, Object obj) throws Exception;
	
	public Object select(String key, Object obj) throws Exception;
	
	public void delete(String key, Object obj)throws Exception;

	List queryForList(Map var1) throws DataAccessException;

	List queryForList(Map var1, Object var2) throws DataAccessException;

	List queryForList(String var1, Map var2) throws DataAccessException;

	List queryForList(String var1, Object var2) throws DataAccessException;
	
}