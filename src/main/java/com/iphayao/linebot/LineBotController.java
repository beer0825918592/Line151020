package com.iphayao.linebot;

import com.iphayao.linebot.Configs;
import com.cpfit.line.flex.*;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.CameraAction;
import com.linecorp.bot.model.action.CameraRollAction;
import com.linecorp.bot.model.action.LocationAction;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.LeaveEvent;
import com.linecorp.bot.model.event.MemberJoinedEvent;
import com.linecorp.bot.model.event.MemberLeftEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.ThingsEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.message.quickreply.QuickReply.QuickReplyBuilder;
import com.linecorp.bot.model.message.quickreply.QuickReplyItem;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.util.LinkedHashMap; 
import java.util.Map; 
//import com.mendix.core.Core;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger; 

@Slf4j
@LineMessageHandler
public class LineBotController {
	
	//////config
	Configs configs=new Configs();
	String web=configs.getWeb();
	String deeplinklogin=configs.getDeepLinkLogin();
	String deeplinkqrcode=configs.getDeepLinkQROCODE();
	String deeplinkorderstatus=configs.getDeepLinkOrderStatus();
	String texterror=configs.getTextError();
	String textnoconfigbu=configs.getTextNoConfigBU();
	String textnoconfig=configs.getTextNoConfig();
	String textnorest=configs.getTextNoRest();
	String textnotsetupcv=configs.getTextNotSetUpCv();
	String textnotoken=configs.getTextNoToken();
	String textnolink=configs.getTextNoLink();
	String textnouserid=configs.getTextNoUserid();
	String textcontact=configs.getTextContact();
	
	
	
	
    @Autowired
    private LineMessagingClient lineMessagingClient;
    
    // Create a Logger 
    Logger logger  = Logger.getLogger(LineBotController.class.getName()); 

    @EventMapping
    public void handleTextMessage(MessageEvent<TextMessageContent> event) throws ClientProtocolException, IOException, ParseException, URISyntaxException {
        logger.info(event.toString());
    	
        TextMessageContent message = event.getMessage();
        handleTextContent(event.getReplyToken(), event, message);
    }
    
    @EventMapping
    public void handleFollowEvent(FollowEvent event) throws ClientProtocolException, IOException {
        logger.info(event.toString());
    	
        handleFollowUser(event.getReplyToken(), event);
    }
    
    @EventMapping
    public void handleUnfollowEvent(UnfollowEvent event) throws ClientProtocolException, IOException {
        logger.info(event.toString());
        String userId = event.getSource().getUserId();
    	Callrest rest=new Callrest();
        String token =rest.getToken(userId);
        
        //// 
        rest.UnFollow(token);
        
    }
    
    @EventMapping
    public void handleBeaconEvent(BeaconEvent event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleJoinEvent(JoinEvent event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleLeaveEvent(LeaveEvent event){
        logger.info(event.toString());
       
    }
    
    @EventMapping
    public void handleMemberJoinedEvent(MemberJoinedEvent event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleMemberLeftEvent(MemberLeftEvent event){
        logger.info(event.toString());
       
    }
    
    @EventMapping
    public void handlePostbackEvent(PostbackEvent event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleThingsEvent(ThingsEvent event){
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    
    
    
    
    
    @EventMapping
    public void handleStickerMessage(MessageEvent<StickerMessageContent> event) {
        logger.info(event.toString());
//        StickerMessageContent message = event.getMessage();
//        reply(event.getReplyToken(), new StickerMessage(
//                message.getPackageId(), message.getStickerId()
//        ));
        //////ถ้าเป็น sticker ซื้อ พัง  sticker ฟรีได้  ก็ต้องดักหมดเลย 
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleAudioMessage(MessageEvent<AudioMessageContent> event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleVideoMessage(MessageEvent<VideoMessageContent> event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleFileMessage(MessageEvent<FileMessageContent> event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    
   
    
    @EventMapping
    public void handleLocationMessage(MessageEvent<LocationMessageContent> event) {
        logger.info(event.toString());
//        LocationMessageContent message = event.getMessage();
//        reply(event.getReplyToken(), new LocationMessage(
//                (message.getTitle() == null) ? "Location replied" : message.getTitle(),
//                message.getAddress(),
//                message.getLatitude(),
//                message.getLongitude()
//        ));
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    @EventMapping
    public void handleUnknownMessage(MessageEvent<UnknownMessageContent> event) {
        logger.info(event.toString());
        this.replyText(event.getReplyToken(),configs.getAutoReplyMessage());
    }
    
    private void handleFollowUser(String replyToken, Event event)  throws ClientProtocolException, IOException {
    	
	 
		
    	String userId = event.getSource().getUserId();
    	Callrest rest=new Callrest();
    	
    	String name =rest.getProfileName(userId);
    	
    	if(name.equals("!Config"))this.replyText(replyToken,textnoconfig);
    	else if(name.equals("!rest"))this.replyText(replyToken,textnorest);
    	else {
    		String follow=rest.CheckFollow(userId);
        	
        	if(follow.equals(""))this.replyText(replyToken,texterror);
        	else if(follow.equals("false")) {
        		String token=rest.getTokenLogin(userId);
            	
            	String msg = "ยินดีต้อนรับคุณ "+name+"  \r\n" + 
            			"เข้าสู่ TestCPFMของซีพีเฟรชมาร์ทค่ะ\r\n" + 
            			"\r\n" + 
            			"นี่เป็นช่องทางการช่วยเหลือลูกค้า สำหรับติดตามข้อมูลคำสั่งซื้อ \r\n" + 
            			"ราคาสินค้าและยอดวงเงิน\r\n" + 
            			"\r\n" + 
            			"ก่อนเริ่มใช้งานระบบกวนลงทะเบียนการใช้งาน "+web+deeplinklogin+token;
            		//	"ก่อนเริ่มใช้งานระบกวนลงทะเบียนการใช้งาน www.xxxx.cpf.co.th/"+Token;
            	
            	this.replyText(replyToken,msg);
        		
        	}else if(follow.equals("true")) {
        		String msg = "ยินดีต้อนรับคุณ "+name+"  \r\n" + 
            			     "เข้าสู่ TestCPFMของซีพีเฟรชมาร์ทค่ะ\r\n"; 
            	
            	this.replyText(replyToken,msg);
        		
        	}
    		
    		
    		
    	}
    	
    	
    }


    
    

    private void handleTextContent(String replyToken, Event event, 
                                   TextMessageContent content) throws ClientProtocolException, IOException, ParseException, URISyntaxException {
        String text = content.getText();
        Callrest rest=new Callrest();
        String userId = event.getSource().getUserId();
        
        String reply="";
       // logger.info("Got text message from %s : %s", replyToken, text);

        switch (text) {
        
            case "ONLINE ORDER":{
            	
            	String token =rest.getToken(userId);
            	
            	reply=rest.getOnlineOrder(token);
            	
            	if(reply.equals("!token"))this.replyText(replyToken,textnotoken);
            	else if(reply.equals("!link"))this.replyText(replyToken,textnolink);
            	else this.replyText(replyToken,reply);
             
           	    break;
           }
            case "ORDER STATUS":{
            	
            	String checkid=rest.getCheckID(userId);
            	
            	if(checkid.equals("false"))this.replyText(replyToken,textnouserid);
            	else if(checkid.equals("true"))	{
            		String token =rest.getToken(userId);
                	
                	
                	
               	 this.replyText(replyToken,"ดูรายละเอียดได้ที่  \r\n"
   		   					+web+deeplinkorderstatus+token);
            	}
            	
            	
            	
            	
             
           	    break;
           }
            case "PRODUCT PRICE":{
            	
            	String checkid=rest.getCheckID(userId);
            	
            	if(checkid.equals("false"))this.replyText(replyToken,textnouserid);
            	else if(checkid.equals("true"))	{
            		
                    String cvname=rest.getCVName(userId);
                 //   String cvname="Test";
            		
            		if(cvname.equals("โปรดติดต่อพนักงานขาย") ||
            		   cvname.equals("โปรดติดต่อพนักงานเนื่องจากไม่สามารถค้นหาชื่อได้" ))
            			this.replyText(replyToken,cvname);
            		
            		//else if(cvname.equals("!ConfigBU"))this.replyText(replyToken,textnoconfigbu);
            		else if(cvname.equals("!Config"))this.replyText(replyToken,textnoconfig);
            		else if(cvname.equals("notsetupcv"))this.replyText(replyToken,textnotsetupcv);
            		else  {
            			String token =rest.getToken(userId);
            			 this.reply(replyToken, new FlexMsgProductPrice().get(userId,token,cvname));
            			
            		}
            	}
            	
            		
            	break;
            	
           }
            case "CHECK BALANCE":{
            	
            	

                String checkid=rest.getCheckID(userId);

                if(checkid.equals("false"))this.replyText(replyToken,textnouserid);
            	else if(checkid.equals("true"))	{
            	
            	
            	String token =rest.getToken(userId);
            	reply=rest.getCheckBalance(token);
            	
            	//if(reply.equals("!ConfigBU\n"))this.replyText(replyToken,textnoconfigbu);
            	 if(reply.equals("!Config\n"))this.replyText(replyToken,textnoconfig);
            	else if(reply.equals("!token\n"))this.replyText(replyToken,textnotoken);
            	else if(reply.equals("!rest\n"))this.replyText(replyToken,textcontact);
            	else {
            		
            		 this.reply(replyToken,new FlexMsgCredit().get(userId,reply));  
            		 
            		 
            	}
            	
            	}
             
           	    break;
           }
            case "PAYMENT":{
            	
            	String checkid=rest.getCheckID(userId);

            	if(checkid.equals("false"))this.replyText(replyToken,textnouserid);
            	else if(checkid.equals("true"))	{
            		
            		JSONObject items1 = new JSONObject(); 
                	items1.put("type","action");
                    
                	Map m = new LinkedHashMap(2); 
                  
                	m.put("type","message");
                	m.put("label","QR Code");
                	m.put("text","QR Code");
                    items1.put("action",m);
                    
                    
                    m = new LinkedHashMap(2); 
                 
                    m.put("type","message");
                	m.put("label","Counter Service");
                	m.put("text","Counter Service");
                    
                    JSONObject items2 = new JSONObject(); 
                    items2.put("type","action");
                    items2.put("action",m);
                    
                    JSONArray items = new JSONArray();
                     items.add(items1);
                     items.add(items2);
                   
                    
                    final QuickReply quickReply = QuickReply.items(items);
                    this.reply(replyToken,TextMessage
                                     .builder()
                                     .text(configs.getTextPayment())
                                     .quickReply(quickReply)
                                     .build());
            	}
            	
                break;
           }
            
            case "CONTACT":{
            	
            	
            	String checkid=rest.getCheckID(userId);
            	//checkid="false";
            	//checkid="true";

            	if(checkid.equals("false"))this.reply(replyToken,new FlexMsgContactCv().get(userId,""));  
            	else if(checkid.equals("true")) {
            		
            		String token =rest.getToken(userId);
                	
                	reply=rest.getContact(token);
                	//reply="Test";
                	
                	//check text  $LineAppConfig/DefualtMsg_NotSetupCv
                	
                	//if(reply.equals("!Config"))this.replyText(replyToken,textnoconfigbu);
                	 if(reply.equals("!Config"))this.replyText(replyToken,textnoconfig);
                	else if(reply.equals("!token"))this.replyText(replyToken,textnotoken);
                	else if(!reply.equals("") || !reply.equals(null) ){
                		
                		if(reply.equals("โปรดติดต่อพนักงานขาย")||reply.equals("notsetupcv"))reply="";
                	
                	 this.reply(replyToken,new FlexMsgContactCv().get(userId,reply));         	
                	}
            		
            	}
            	
            	
            	
                break;
           }
            
            case "QR Code":{
            	
            	String checkid=rest.getCheckID(userId);

            	if(checkid.equals("false"))this.replyText(replyToken,textnouserid);
            	else if(checkid.equals("true"))	{
            		
            		String token =rest.getToken(userId);
               	  
              	   this.replyText(replyToken,"ดูรายละเอียดได้ที่  \r\n"
              			   					+web+deeplinkqrcode+token);
            		
            	}
            	
         	   
         	  
         	  
            	
                
               
                
           	    break;
           }
            
            case "Counter Service":{
            	
            	String checkid=rest.getCheckID(userId);

            	if(checkid.equals("false"))this.replyText(replyToken,textnouserid);
            	else if(checkid.equals("true"))	{
            		
            		String data=rest.getPaymentFileId(userId,1);
                	if(data.equals("!mindate") || data.equals("!cca") 
                		|| data.equals("!sum") || data.equals("!Success") )
                		{
                		this.replyText(replyToken," ไม่สามารถสร้าง BarCode ได้  เนื่องจากข้อมูลบางส่วนไม่ครบ");
                		
                		//throw 
                	}else if(data.equals("!rest")) {
                			this.replyText(replyToken,textnorest);
                		
                	}else if(data.contains("{")&&data.contains("}")){
                		System.out.println("Data :"+data);
                    	//String invno="InvoiceNo";
                    	String amount="Amount";
                    	String duedate="DueDate";
                    	String fileid="FileId";
                    	
                    	//String InvNo=data.substring(data.indexOf(":", data.indexOf(invno))+1,data.indexOf(";", data.indexOf(invno)));
                    	
                    	String Amount=data.substring(data.indexOf(":", data.indexOf(amount))+1,data.indexOf(";", data.indexOf(amount)));
                    	
                    	String DueDate=data.substring(data.indexOf(":", data.indexOf(duedate))+1,data.indexOf(";", data.indexOf(duedate)));
                    	
                    	String FileId=data.substring(data.indexOf(":", data.indexOf(fileid))+1,data.indexOf(";", data.indexOf(fileid)));
                    	
                    	//System.out.println(invno+" "+InvNo);
                    	System.out.println(amount+" "+Amount);
                    	System.out.println(duedate+" "+DueDate);
                    	System.out.println(fileid+" "+FileId);
                    	
                    	int fileid_=Integer.parseInt(FileId);
                    	
                        //this.replyText(replyToken,"Counter Service");
                    	this.reply(replyToken,new FlexPaymentBarCode().get(userId,Amount,DueDate,fileid_));
                		
                    	
//                	}else if(data.equals("!ConfigBU")) {
//            			this.replyText(replyToken,textnoconfigbu);
                		
            	   }else if(data.equals("!Config")) {
            			this.replyText(replyToken,textnoconfig);
                		
            		
            	   }else if(data.equals("!no")) {
           			this.replyText(replyToken,"ไม่พบยอดค้างชำระ");
            		
           		    ////// Config reply nouser
           	        }else if(data!="") 	this.replyText(replyToken,data);
            		
            	}
            	
            	
            	
        	   
            	
            	break;
           }
            
            case "ลงทะเบียน":{
            	
            	String name =rest.getProfileName(userId);
            	
            	if(name.equals("!Config"))this.replyText(replyToken,textnoconfig);
            	else if(name.equals("!rest"))this.replyText(replyToken,textnorest);
            	else {
            		String follow=rest.CheckFollow(userId);
                	
                	if(follow.equals(""))this.replyText(replyToken,texterror);
                	else if(follow.equals("false")) {
                		String token=rest.getTokenLogin(userId);
                    	
                    	String msg = "ยินดีต้อนรับคุณ "+name+"  \r\n" + 
                    			"เข้าสู่ TestCPFMของซีพีเฟรชมาร์ทค่ะ\r\n" + 
                    			"\r\n" + 
                    			"นี่เป็นช่องทางการช่วยเหลือลูกค้า สำหรับติดตามข้อมูลคำสั่งซื้อ \r\n" + 
                    			"ราคาสินค้าและยอดวงเงิน\r\n" + 
                    			"\r\n" + 
                    			"ก่อนเริ่มใช้งานระบบกวนลงทะเบียนการใช้งาน "+web+deeplinklogin+token;
                    		//	"ก่อนเริ่มใช้งานระบกวนลงทะเบียนการใช้งาน www.xxxx.cpf.co.th/"+Token;
                    	
                    	this.replyText(replyToken,msg);
                		
                	}else if(follow.equals("true")) {
                		String msg = "ยินดีต้อนรับคุณ "+name+"  \r\n" + 
                    			     "เข้าสู่ TestCPFMของซีพีเฟรชมาร์ทค่ะ\r\n"; 
                    	
                    	this.replyText(replyToken,msg);
                		
                	}
            		
            		
            		
            	}
              	
           	 break;
           }
            
            case "test":{
            	 this.reply(replyToken,new FlexMsgContactCv().get(userId,"Testsdfsdf setset"));
            	//this.reply(replyToken,new FlexMsgCredit().get(reply));  	
            	 break;
            }

            default:
               // logger.info("Return echo message %s : %s", replyToken, text);
                this.replyText(replyToken,new Configs().getWordUnknown());
        }
    }

    

	private void replyText(@NonNull  String replyToken, @NonNull String message) {
        if(replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken is not empty");
        }

        if(message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "...";
        }
        this.reply(replyToken, new TextMessage(message));
    }

    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, messages)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    

}