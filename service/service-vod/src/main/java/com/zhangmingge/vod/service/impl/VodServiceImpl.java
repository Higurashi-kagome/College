package com.zhangmingge.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.zhangmingge.servicebase.exceptionhandler.GuliException;
import com.zhangmingge.vod.Utils.ConstantVodUtils;
import com.zhangmingge.vod.Utils.InitVodClient;
import com.zhangmingge.vod.service.VodService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {

	@Override
	public String uploadVideoAly(MultipartFile file)
	{
		try {
			//accessKeyId, accessKeySecret
			//fileName：上传文件原始名称
			String fileName = file.getOriginalFilename();
			//title：上传之后显示名称
			String title = fileName.substring(0, fileName.lastIndexOf("."));
			//inputStream：上传文件输入流
			InputStream inputStream = file.getInputStream();
			UploadStreamRequest request = new UploadStreamRequest(
					ConstantVodUtils.ACCESS_KEY_ID,
					ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName,
					inputStream);

			UploadVideoImpl uploader = new UploadVideoImpl();
			UploadStreamResponse response = uploader.uploadStream(request);

			String videoId = response.getVideoId();
			return videoId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 删除多个视频
	 * @param videoIdList
	 */
	@Override
	public void removeMoreAlyVideo(List videoIdList)
	{
		try {
			//初始化对象
			DefaultAcsClient client = InitVodClient.initVodClient(
					ConstantVodUtils.ACCESS_KEY_ID,
					ConstantVodUtils.ACCESS_KEY_SECRET);
			//创建删除视频request对象
			DeleteVideoRequest request = new DeleteVideoRequest();

			//videoIdList值转换成 1,2,3
			String videoIds = StringUtils.join(videoIdList.toArray(), ",");
			//向request设置视频id
			request.setVideoIds(videoIds);

			//调用初始化对象的方法实现删除
			client.getAcsResponse(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuliException(20001, "删除视频失败");
		}
	}
}
