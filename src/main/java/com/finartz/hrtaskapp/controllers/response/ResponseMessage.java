package com.finartz.hrtaskapp.controllers.response;

public enum ResponseMessage {
	UPDATED("Güncelleme Başarılı"),
	DELETED("Silme işlemi başarılı"),
	DELETEERROR("Silerken bir problem oluştu"),
	LOGGEDIN("Giriş Başarılı"),
	EDITERROR("Güncelleme işleminde problem oluştu"),
	ADDED("Başarıyla Eklendi"),
	ADDINGERROR("Ekleme sırasında hata oluştu"),
	NOVALUE("Aradığınız nesne bulunumadaı");
	private String msg;
	
	ResponseMessage(String msg){
		this.msg=msg;
	}
	
	public String get(){
		return msg;
	}
}
