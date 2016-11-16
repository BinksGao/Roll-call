package com.courseinfo.anlaiye;
/**
 * @author 高小黑
 *
 * 2016年5月12日下午5:36:45
 */
public class CourseInfo {
	private int id;
	private String coursename;
	private String classname;
	private String time;
	private String address;
	
	public CourseInfo(String coursename, String classname, String time, String address, int id){
		super();
		this.coursename=coursename;
		this.classname=classname;
		this.time=time;
		this.address=address;
		this.id=id;
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CourseInfo [coursename=" + coursename + ", classname="
				+ classname + ", time=" + time + ", address=" + address + "]";
	}
	
	
}
