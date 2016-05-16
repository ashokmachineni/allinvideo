package com.example.favorite;

public class Pojo {
	
	private int id;
	private String  vid;
	private String  videoId;
	private String VideoName;
	private String VideoUrl;
	private String Duration;
	private String CategoryId;
	private String CategoryName;
	private String Description;
	private String ImageUrl;
	private String VideoType;
	
	public Pojo()
	{
		
	}
	
	public Pojo(String vid)
	{
		this.vid=vid;
	}
	
	public Pojo(String vid,String videoid,String videoname,String videourl,String videoduration,String catname,String catid,String desc,String imageurl,String videotype)
	{
		this.vid=vid;
		this.videoId=videoid;
		this.VideoName=videoname;
		this.VideoUrl=videourl;
		this.Duration=videoduration;
		this.CategoryName=catname;
		this.CategoryId=catid;
		this.Description=desc;
		this.ImageUrl=imageurl;
		this.VideoType=videotype;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getVId() {
		return vid;
	}

	public void setVId(String vid) {
		this.vid = vid;
	}
	
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoid) {
		this.videoId = videoid;
	}
	
	public String getVideoName() {
		return VideoName;
	}

	public void setVideoName(String videoname) {
		this.VideoName = videoname;
	}
	
	public String getVideoUrl() {
		return VideoUrl;
	}

	public void setVideoUrl(String videourl) {
		this.VideoUrl = videourl;
	}
	
	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		this.Duration = duration;
	}
	
	public String getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(String catid) {
		this.CategoryId = catid;
	}
	
	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String catname) {
		this.CategoryName = catname;
	}
	public String getDescription() {
		return Description;
	}

	public void setDescription(String desc) {
		this.Description = desc;
	}
	
	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageurl) {
		this.ImageUrl = imageurl;
	}
	
	public String getVideoType() {
		return VideoType;
	}

	public void setVideoType(String videotype) {
		this.VideoType = videotype;
	}

}
