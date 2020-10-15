package com.iphayao.linebot;

public class Data {
	
      public String Detail;
      public String FileId;
      
      public Data() {
    
      }
      
      

	public Data(String Detail,String FileId) {
    	  this.Detail=Detail;
    	  this.FileId=FileId;
      }
      
      public void  setDetail(String Detail) {
    	  this.Detail=Detail;
      }
      
      public String getDetail() {
    	  
    	  return this.Detail;
      }
      
      public void  setFileId(String FileId) {
    	  this.FileId=FileId;
      }
      
      public String getFileId() {
    	  
    	  return this.FileId;
      }
      
}
