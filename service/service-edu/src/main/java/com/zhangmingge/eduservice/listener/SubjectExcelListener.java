package com.zhangmingge.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zhangmingge.eduservice.entity.EduSubject;
import com.zhangmingge.eduservice.entity.excel.SubjectData;
import com.zhangmingge.eduservice.service.EduSubjectService;
import com.zhangmingge.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService subjectService;

    public SubjectExcelListener() {}
    /**
     * SubjectExcelListener 不能交给 spring 进行管理，需要自己 new，不能注入其他对象。<br>
     * 所以使用有参构造注入 subjectService，实现数据库操作。
     * @param subjectService
     */
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // 读取 excel 内容，一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        // 判断是否有数据
        if(subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }

        // 一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        // 判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName());
        if(existOneSubject == null) { // 没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName()); // 一级分类名称
            subjectService.save(existOneSubject);
        }

        // 获取一级分类 id 值
        String pid = existOneSubject.getId();

        // 添加二级分类
        // 判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid); // 将一级分类的 id 作为 parent_id
            existTwoSubject.setTitle(subjectData.getTwoSubjectName()); // 二级分类名称
            subjectService.save(existTwoSubject);
        }
    }

    // 一级分类不能重复添加，判断一级分类是否重复
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    // 二级分类不能重复添加，判断二级分类是否重复
    private EduSubject existTwoSubject(String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
