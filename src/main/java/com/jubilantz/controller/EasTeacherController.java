package com.jubilantz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jubilantz.entity.EasClass;
import com.jubilantz.entity.EasTeacher;
import com.jubilantz.services.EasTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师
 */
@Controller
@RequestMapping("/easTeacher")
public class EasTeacherController {
    @Autowired
    private EasTeacherService easTeacherService;


    @RequestMapping("/index")
    public String index() throws Exception{
        return "system/teacher/index";
    }



    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    EasTeacher easTeacher) throws Exception{
        Map<String, Object> map = new HashMap<>();

//        分页不能用待修改。。。

        Page<EasTeacher> pager = PageHelper.startPage(page,limit);
        List<EasTeacher> list = easTeacherService.findTeacherList(easTeacher);
        map.put("count",pager.getTotal());
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");

        return map;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<EasTeacher> search() throws Exception{
        return easTeacherService.getAll();
    }

}
