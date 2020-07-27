package com.example.demo.common;

import java.util.List;
import java.util.Map;

/**
 * @author watoPark on 2020-07-27
 * @project testSpringBoot
 */

public interface CommonService {
    List queryForList(Map var1) throws Exception;

    List queryForList(Map var1, Object var2) throws Exception;

    List queryForList(String var1, Map var2) throws Exception;

    List queryForList(String var1, Object var2) throws Exception;
}
