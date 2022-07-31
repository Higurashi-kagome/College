package com.zhangmingge.eduservice.controller;


import com.zhangmingge.commonutils.R;
import com.zhangmingge.eduservice.entity.EduVideo;
import com.zhangmingge.eduservice.entity.vo.VideoInfoVo;
import com.zhangmingge.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/eduservice/video")
// @CrossOrigin
public class EduVideoController {
	@Autowired
	private EduVideoService videoService;

	//添加小节
	@PostMapping("addVideo")
	public R addVideo(@RequestBody EduVideo eduVideo) {
		videoService.save(eduVideo);
		return R.ok();
	}

	/**
	 * 删除小节
	 * @param id 小节 id
	 * @return
	 */
	@DeleteMapping("{id}")
	public R deleteVideo(@PathVariable String id) {
		//删除小节中的视频
		videoService.removeVideoMediaByVideoId(id);
		//删除小节
		videoService.removeById(id);
		return R.ok();
	}

	//查询小节
	@GetMapping("getVideo/{videoId}")
	public R getVideo(@PathVariable String videoId) {
		VideoInfoVo video = videoService.getVideoInfo(videoId);
		return R.ok().data("video", video);
	}

	//修改小节
	@PostMapping("updateVideo")
	public R updateVideo(@RequestBody EduVideo eduVideo) {
		videoService.updateById(eduVideo);
		return R.ok();
	}
}

