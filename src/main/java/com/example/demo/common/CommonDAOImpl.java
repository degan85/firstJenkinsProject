package com.example.demo.common;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonDAOImpl implements CommonDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(String key,Object obj) throws Exception {
		sqlSessionTemplate.insert(key,obj);
	}

	@Override
	public Object update(String key, Object obj) throws Exception {
		return sqlSessionTemplate.update(key, obj);
	}

	@Override
	public Object selectList(String key, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(key, obj);
	}

	@Override
	public Object select(String key, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(key, obj);
	}

	@Override
	public void delete(String key, Object obj) throws Exception {
		sqlSessionTemplate.delete(key,obj);
	}

	@Override
	public List queryForList(Map var1) throws DataAccessException {
		return sqlSessionTemplate.selectList((String) var1.get("queryKey"), var1);
	}

	@Override
	public List queryForList(Map var1, Object var2) throws DataAccessException {
		return sqlSessionTemplate.selectList((String) var1.get("queryKey"), new BeanPropertySqlParameterSource(var2));
	}

	@Override
	public List queryForList(String var1, Map var2) throws DataAccessException {
		return sqlSessionTemplate.selectList(var1, var2);
	}

	@Override
	public List queryForList(String var1, Object var2) throws DataAccessException {
		return sqlSessionTemplate.selectList(var1, new BeanPropertySqlParameterSource(var2));
	}
	
	
}