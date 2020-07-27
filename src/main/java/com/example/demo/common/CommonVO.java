package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@Alias("commonVO")
public class CommonVO implements Serializable {
	static final String SUCCESS = "S";
	static final String ERROR = "E";
	static final String BUSY = "B";
	private String reg_dt = "";
	private String reg_id = "";
	private String update_dt = "";
	private String update_id = "";
	private String wato_user_id = "";
	
	private String reg_dtFrom = "";
	private String reg_dtTo = "";
	private String update_dtFrom = "";
	private String update_dtTo = "";
	
	/**
	 * 파일
	 * */
	private String fileid = "";
	private String filenm = "";
	private String fileurl = "";
	private String filepath = "";
	private String filetpcd = "";
	private String pkid1 = "";
	private String pkid2 = "";
	private String pkid3 = "";

}