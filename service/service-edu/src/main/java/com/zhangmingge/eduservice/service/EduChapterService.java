package com.zhangmingge.eduservice.service;

import com.zhangmingge.eduservice.entity.chapter.ChapterVo;
import com.zhangmingge.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 */
public interface EduChapterService extends IService<EduChapter> {

	boolean deleteChapter(String chapterId);

	List<ChapterVo> getChapterVideoByCourseId(String courseId);

	void removeChapterByCourseId(String courseId);
}
