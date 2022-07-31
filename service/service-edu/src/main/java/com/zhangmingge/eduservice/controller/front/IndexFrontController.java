package com.zhangmingge.eduservice.controller.front;

import com.zhangmingge.commonutils.R;
import com.zhangmingge.eduservice.entity.EduCourse;
import com.zhangmingge.eduservice.entity.EduTeacher;
import com.zhangmingge.eduservice.service.EduCourseService;
import com.zhangmingge.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
// @CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @ApiModelProperty(value = "查询前8条热门课程，查询前4条名师")
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        List<EduCourse> eduList = courseService.getHotEight();
        //查询前4条名师
        List<EduTeacher> teacherList = teacherService.getFirstFour();
        return R.ok().data("eduList", eduList).data("teacherList", teacherList);
    }

}
