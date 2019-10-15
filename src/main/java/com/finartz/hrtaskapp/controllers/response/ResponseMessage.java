package com.finartz.hrtaskapp.controllers.response;

public enum ResponseMessage {
	UPDATED("Güncelleme Başarılı"),
	LOGGEDIN("Giriş Başarılı"),
	EDITERROR("Yalnızca Kendi Görevinizi Güncelleyebilirsiniz !"),
	ADDED("Başarıyla Eklendi");
	
	private String msg;
	
	ResponseMessage(String msg){
		this.msg=msg;
	}
	
	public String get(){
		return msg;
	}
}
