package com.mystory001.domain;

import java.sql.Timestamp;

public class BoardDTO {

	private int num;
	private String name;
	private String subject;
	private String content;
	private int readCount;
	private Timestamp date;
	private String file;
	
	//alt + shift + s : s
	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", name=" + name + ", subject=" + subject + ", content=" + content
				+ ", readCount=" + readCount + ", date=" + date + ", file=" + file + "]";
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
