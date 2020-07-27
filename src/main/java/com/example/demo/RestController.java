package com.example.demo;

import com.example.demo.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.print.SimpleDoc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author watoPark on 2020-07-27
 * @project testSpringBoot
 */

@Controller
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Resource
    private CommonService commonService;

    @GetMapping(value="/")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping({"/simpleAction/{queryKey}"})
    public ModelAndView simpleAction(HttpServletRequest request, HttpServletResponse response,
                                     @PathVariable String queryKey,
                                     @RequestParam Map<String,Integer> requestParams
    ) throws Exception {

        Map command = requestParams;
        List list = null;
        Map map = null;
        Map message = new HashMap();

        String resultURL = "";
        String actionKey = "search";

        try {
            actionKey = (String) command.get("actionKey");
            if (actionKey == null) {
                actionKey = "search";
            }
        } catch (Exception var24) {
            ;
        }

        try {
            if (command.get("queryKey").toString().equals("")) {
                command.put("queryKey", queryKey);
            }

        } catch (Exception var25) {
            command.put("queryKey", queryKey);
        }

        ModelAndView mav = new ModelAndView(resultURL);
        if (actionKey.equals("search")) {
            String resultType = "JSON";
            String[] outParamKey = null;

            try {
                outParamKey = ((String) command.get("outParamKey")).split(";");
            } catch (Exception var21) {
                ;
            }

            if (resultType.equals("List")) {
                list = this.commonService.queryForList(command);
                mav.addObject("resultList", list);
            } else {
                int i;
                SimpleDoc simpleDoc;
                Map record;
                LinkedHashMap data;

                ArrayList resultList;
                if (resultType.toUpperCase().equals("JSON")) {
                    list = this.commonService.queryForList(command);
                    resultList = new ArrayList();
                    if (outParamKey != null && list.size() > 0) {
                        for (i = 0; i < list.size(); ++i) {
                            record = (Map) list.get(i);
                            data = new LinkedHashMap();

                            for (i = 0; i < outParamKey.length; ++i) {
                                data.put(outParamKey[i], record.get(outParamKey[i]));
                            }

                            resultList.add(data);
                        }
                    }
                    mav = new ModelAndView("jsonView");
                    mav.addObject("data", list);
                    mav.addObject("resultList", resultList);
                }
            }
            mav.addObject("command", command);
        } else {

        }
        String attributeValue;
        String attribute;
        for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); request.setAttribute(attribute,
                attributeValue)) {
            attribute = (String) en.nextElement();
            attributeValue = request.getParameter(attribute);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("set Attribute in Request : " + attribute + "=" + attributeValue);
            }
        }

        return mav;
    }
}

