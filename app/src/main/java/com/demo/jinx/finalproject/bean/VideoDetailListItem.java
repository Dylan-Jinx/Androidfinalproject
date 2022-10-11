package com.demo.jinx.finalproject.bean;

import java.io.Serializable;

public class VideoDetailListItem implements Serializable {
	private String videoName;
	private String videoId;

	public void setVideoName(String videoName){
		this.videoName = videoName;
	}

	public String getVideoName(){
		return videoName;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return videoId;
	}

	@Override
 	public String toString(){
		return 
			"VideoDetailListItem{" + 
			"video_name = '" + videoName + '\'' + 
			",video_id = '" + videoId + '\'' + 
			"}";
		}
}