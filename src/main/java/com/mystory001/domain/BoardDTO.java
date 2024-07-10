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
	
	//답글 객체
	private int re_ref;
	private int re_lev;
	private int re_seq;
	
	//alt + shift + s : s
	

	public int getNum() {
		return num;
	}
	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", name=" + name + ", subject=" + subject + ", content=" + content
				+ ", readCount=" + readCount + ", date=" + date + ", file=" + file + ", re_ref=" + re_ref + ", re_lev="
				+ re_lev + ", re_seq=" + re_seq + "]";
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
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	
}
