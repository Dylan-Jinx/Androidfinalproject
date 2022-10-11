package com.demo.jinx.finalproject.bean;

import java.util.List;
import java.io.Serializable;

public class VideoBean implements Serializable {
	private String img;
	private String intro;
	private String name;
	private List<VideoDetailListItem> videoDetailList;
	private int id;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setIntro(String intro){
		this.intro = intro;
	}

	public String getIntro(){
		return intro;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setVideoDetailList(List<VideoDetailListItem> videoDetailList){
		this.videoDetailList = videoDetailList;
	}

	public List<VideoDetailListItem> getVideoDetailList(){
		return videoDetailList;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"VideoBean{" + 
			"img = '" + img + '\'' + 
			",intro = '" + intro + '\'' + 
			",name = '" + name + '\'' + 
			",videoDetailList = '" + videoDetailList + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}