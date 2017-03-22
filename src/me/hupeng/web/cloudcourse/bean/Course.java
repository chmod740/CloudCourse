package me.hupeng.web.cloudcourse.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


/**
 * µ„≤• ”∆µ
 * */
@Table("course")
public class Course {
	@Id
	private int id;
	
	@Column("course_name")
	private String courseName;
	
	@Column("video_url")
	private String videoUrl;
	
	@Column("pdf_url")
	private String pdfUrl;
	
	@Column("video_pdf_mapping_list")
	private String videoPdfMappingList;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public String getVideoPdfMappingList() {
		return videoPdfMappingList;
	}

	public void setVideoPdfMappingList(String videoPdfMappingList) {
		this.videoPdfMappingList = videoPdfMappingList;
	}
}
