package com.jubilantz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jubilantz.entity.EasStudent;
import com.jubilantz.services.EasStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生
 */
@Controller
@RequestMapping("/easStudent")
public class EasStudentController {
    @Autowired
    private EasStudentService easStudentService;
    @RequestMapping("/index")
    public String index() throws Exception{
        return "system/student/index";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    EasStudent easStudent) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Page<EasStudent> pager = PageHelper.startPage(page,limit);
        List<EasStudent> list = easStudentService.findList(easStudent);
        map.put("count",pager.getTotal());
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");

        return map;
    }

}
