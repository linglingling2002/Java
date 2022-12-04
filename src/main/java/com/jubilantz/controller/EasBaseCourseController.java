package com.jubilantz.controller;

import com.jubilantz.entity.EasBaseCourse;
import com.jubilantz.entity.EasClass;
import com.jubilantz.services.EasBaseCourseService;
import com.jubilantz.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本课程
 */
//设置为ioc容器类
@Controller                             //一个类中定义多个接口
@RequestMapping("/easBaseCourse")       //处理请求地址映射
public class EasBaseCourseController {
    @Autowired                          //自动装配
    private EasBaseCourseService easBaseCourseService;

    @RequestMapping("/index")
    public String index() throws Exception {
        return "system/baseCourse/index";
    }

    @RequestMapping("/list")
    //返回结果直接写入HTTP response body中
    @ResponseBody                  //将请求参数绑定到控制器的方法参数上
    public Map<String,Object> list(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit,
                                   EasBaseCourse easBaseCourse) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int count = easBaseCourseService.getCount();
        PageUtil pageUtil = new  PageUtil(page,limit);          //当前页，页数
        List<EasBaseCourse> list = easBaseCourseService.getList(easBaseCourse,pageUtil);
        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("/baseCourseAddForm")
    public String baseCourseAddForm() throws Exception {
        return "system/baseCourse/baseCourseAddForm";
    }

    @RequestMapping(value = "/addBaseCourse",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addBaseCourse(EasBaseCourse easBaseCourse) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<EasBaseCourse> list = easBaseCourseService.findBaseCourseName(easBaseCourse.getCoursename());
        if (list.size() != 0){      //判断课程是否存在
            map.put("msg","基本课程已存在");
            map.put("result",false);
        }else if(easBaseCourse.getCoursename().length() <= 0){
            map.put("msg","课程名称不能为空");
            map.put("result",false);
        }else{
            //课程为null也可以添加 还需完善
            easBaseCourseService.addBaseCourse(easBaseCourse);
            map.put("msg","添加成功");
            map.put("result",true);
        }
        return map;
    }

    @RequestMapping("/batchDeleteBaseCourse")
    @ResponseBody
    @RequiresPermissions("basecourse:delete")
    public Map<String, Object> batchDeleteBaseCourse(Integer[] ids) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        easBaseCourseService.batchDeleteBaseCourse(ids);
        map.put("msg","删除成功");
        map.put("result",true);
        return map;
    }

    @RequestMapping(value = "/getBaseCourseView")
    @ResponseBody
    public EasBaseCourse getBaseCourseView(Integer id) throws Exception {
        return easBaseCourseService.getBaseCourseView(id);
    }


    @RequestMapping(value = "/editBaseCourse",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editBaseCourse(EasBaseCourse easBaseCourse) throws Exception{
        Map<String, Object> map = new HashMap<>();

        easBaseCourseService.updateBaseCourse(easBaseCourse);

        map.put("result",true);
        return map;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<EasBaseCourse> search() throws Exception{
        return easBaseCourseService.getAll();
    }

}
