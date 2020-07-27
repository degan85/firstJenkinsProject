package com.example.demo.common;

/**
 * @author watoPark on 2020-07-27
 * @project testSpringBoot
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonDAO commonDAO;

    public List queryForList(Map var1) throws Exception {
        return commonDAO.queryForList(var1);
    }

    public List queryForList(Map var1, Object var2) throws Exception {
        return commonDAO.queryForList(var1, var2);
    }

    public List queryForList(String var1, Map var2) throws Exception {
        return commonDAO.queryForList(var1, var2);
    }

    public List queryForList(String var1, Object var2) throws Exception {
        return commonDAO.queryForList(var1, var2);
    }
}