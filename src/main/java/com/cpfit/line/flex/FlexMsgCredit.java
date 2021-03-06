package com.cpfit.line.flex;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.function.Supplier;

import org.apache.http.client.ClientProtocolException;

import com.iphayao.linebot.Callrest;
import com.iphayao.linebot.Configs;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.action.URIAction.AltUri;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Spacer;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.component.Button.ButtonHeight;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleBuilder;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class  FlexMsgCredit implements Supplier<FlexMessage> {
	Configs config=new Configs();
	String imageherolink=config.getImageHeaderlink();
	String linksupport=config.getLinkSupport();
	String callcenter=config.getCallCenter();
	String web=config.getWeb();
	String linkimage=config.getLinkImage();
	String steptest=config.getStepTest();
	
	Callrest rest=new Callrest();
	public FlexMessage get(String Userid,String Data) throws URISyntaxException, ClientProtocolException, IOException {
		
		
		
        final Image heroBlock = createHeroBlock(Userid);
        final Box bodyBlock = createBodyBlock(Data);
        

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
               
                .size(BubbleSize.KILO)
                .build();
        return new FlexMessage("Contact", bubbleContainer);
    }
	
	 private Image createHeroBlock(String Userid) throws URISyntaxException, ClientProtocolException, IOException {
		 String imagehero=rest.getImageFlexHero(Userid);
		 String step=rest.getStepTest(Userid);

		 if(step.equals("true")) {
			 imagehero=imageherolink;
			 return Image.builder()
		                .url(new URI(imagehero))
		                
		                .size(Image.ImageSize.FULL_WIDTH)
		                .aspectRatio(ImageAspectRatio.R20TO13)
		                .aspectMode(ImageAspectMode.Cover)
		                .build();
		 }
		 
		 if(imagehero.equals("!found")) {
			 imagehero=imageherolink;
			 return Image.builder()
		                .url(new URI(imagehero))
		                
		                .size(Image.ImageSize.FULL_WIDTH)
		                .aspectRatio(ImageAspectRatio.R20TO13)
		                .aspectMode(ImageAspectMode.Cover)
		                .build();
		 }
		 
		 return Image.builder()
	                .url(new URI(web+linkimage+imagehero))
	                .size(Image.ImageSize.FULL_WIDTH)
	                .aspectRatio(ImageAspectRatio.R20TO13)
	                .aspectMode(ImageAspectMode.Cover)
	                .build();
		 
	       
	    }
	 
	   private Box createBodyBlock(String Data) {
		   
		   String[] arrOfStr = Data.split("<"); 
  		 
  		 ///<>
  		 
  		 String Customer=arrOfStr[1].substring(0,arrOfStr[1].indexOf(">"));
  		 
  		 String Remaining=arrOfStr[2].substring(0,arrOfStr[2].indexOf(">"));
  		 
  	   // String Remaining="วงเงินคงเหลือ 10,000,000.00 บาท";
  		 
  		 String[] rem_split = Remaining.split(" "); 
  		 
  		
  		 
  		//outstandingbalance=ob 
  		 
  		 String OB=arrOfStr[3].substring(0,arrOfStr[3].indexOf(">"));
  		 
  		String[] OB_split = OB.split(" "); 
  		 
  		 String DueDate=arrOfStr[4].substring(0,arrOfStr[4].indexOf(">"));
  		 
  		 String DateCurrent=arrOfStr[5].substring(0,arrOfStr[5].indexOf(">"));
		   
		   
	        
	        final Text title = Text.builder()
	               // .text("ติดต่อเจ้าหน้าที่"+decimalFormat.format(555555555.55))
	        		.text(Customer)
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.LG)
	                .margin(FlexMarginSize.LG)
	                .build();
	        

	       
	        	
	        	Box rem = null;
	        	
	        	Box rem_double=null;
	        	
	        	if(rem_split[1].length()>10) {
	        		rem=createInfoBoxSplit(rem_split[0]);
	        		rem_double= createInfoBoxSplit(rem_split[1]+" "+rem_split[2]);
	        	}else if(rem_split[1].length()<=10) {
	        		rem = createInfoBoxSplit(Remaining);
	        		
	        	}
	        	
	        	 Box ob = null;
	        	
	        	Box ob_double=null;
	        	
	        	if(OB_split[1].length()>10) {
	        		ob= createInfoBoxSplit(OB_split[0]);
	        		ob_double= createInfoBoxSplit(OB_split[1]+" "+OB_split[2]);
	        	}else if(OB_split[1].length()<=10) {
	        		ob = createInfoBoxSplit(OB);
	        	}
	        	
	        	
	        	final Box duedate = createInfoBoxSplit(DueDate);
	        	
	        	
	        	final Box datecurrent = createInfoBox(DateCurrent);
	        	
	        	if(rem_split[1].length()>10&&OB_split[1].length()>10) {
	        		return Box.builder()
			                .layout(FlexLayout.VERTICAL)
			                .contents(asList(title,rem,rem_double,ob,ob_double,duedate,datecurrent))
			                .build();
	        	}else if(rem_split[1].length()>10) {
	        		return Box.builder()
			                .layout(FlexLayout.VERTICAL)
			                .contents(asList(title,rem,rem_double,ob,duedate,datecurrent))
			                .build();
	        	}else if(OB_split[1].length()>10) {
	        		return Box.builder()
			                .layout(FlexLayout.VERTICAL)
			                .contents(asList(title,rem,ob,ob_double,duedate,datecurrent))
			                .build();
	        		
	        	}
	        	
		        return Box.builder()
		                .layout(FlexLayout.VERTICAL)
		                .contents(asList(title,rem,ob,duedate,datecurrent))
		                .build();
		        
	        
	        

	    }
	   
	   private Box createInfoBoxSplit(String Data) {
		      /*  final Box place = Box.builder()
		                .layout(FlexLayout.BASELINE)
		                //.spacing(FlexMarginSize.DEFAULT)
		                .contents(asList(
		                        Text.builder()
		                            .text(Data)
		                            //.color("#aaaaaa")
		                            //.weight(Text.TextWeight.BOLD)
		                            .size(FlexFontSize.Md)
		                            .margin(FlexMarginSize.LG)
		                            .flex(1)
		                            .build()
		                        
		                )).build();
		       
		        return Box.builder()
		                .layout(FlexLayout.VERTICAL)
		                .margin(FlexMarginSize.LG)
		                .spacing(FlexMarginSize.SM)
		                .contents(asList(place))
		                .build();*/
			   
			   String[] s = Data.split("@");
			 
			   
			   return Box.builder()
		                .layout(FlexLayout.HORIZONTAL).margin(FlexMarginSize.SM)
		                .contents(asList(
		                		Text.builder()
		     				   .text(s[0])
		     				   .size(FlexFontSize.SM)
		     				   .color("#555555")
		     				   .build(),
		     				  Text.builder()
		                      .text(s[1])
		                      .size(FlexFontSize.SM)
		                      .color("#111111")
		                      .align(FlexAlign.END)
		                      .build()
		                )).build();
		    }
	   
	   private Box createInfoBox(String Data) {
	      /*  final Box place = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                //.spacing(FlexMarginSize.DEFAULT)
	                .contents(asList(
	                        Text.builder()
	                            .text(Data)
	                            //.color("#aaaaaa")
	                            //.weight(Text.TextWeight.BOLD)
	                            .size(FlexFontSize.Md)
	                            .margin(FlexMarginSize.LG)
	                            .flex(1)
	                            .build()
	                        
	                )).build();
	       
	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .margin(FlexMarginSize.LG)
	                .spacing(FlexMarginSize.SM)
	                .contents(asList(place))
	                .build();*/
		   
		   final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
		   
		   return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .contents(asList(separator,
	                		Text.builder()
	     				   .text(Data)
	     				   .size(FlexFontSize.SM)
	     				   .color("#555555")
	     				   .flex(0)
	     				   .margin(FlexMarginSize.XXL)
	     				   .align(FlexAlign.CENTER)
	     				   .build()
	                )).build();
	    }
	   
//	   private Box createInfoBoxDouble(String Data,String Unit) {
////		   DecimalFormat decimalFormat = new DecimalFormat("#.##");
////	        decimalFormat.setGroupingUsed(true);
////	        decimalFormat.setGroupingSize(3);
//	        
//	       // double  m=Double.parseDouble(Data); 
//	       // float m=Float.parseFloat(Data);
//	        
//	        final Box place = Box.builder()
//	                .layout(FlexLayout.BASELINE)
//	                .spacing(FlexMarginSize.MD)
//	                .contents(asList(
//	                        Text.builder()
//	                            .text(Data)
//	                            .color("#aaaaaa")
//	                            .size(FlexFontSize.SM)
//	                            .flex(1)
//	                            .build(),
//	                            Text.builder()
//	                            .text(" "+Unit)
//	                            .color("#aaaaaa")
//	                            .size(FlexFontSize.SM)
//	                            .flex(1)
//	                            .build()
//	                        
//	                )).build();
//	       
//	        return Box.builder()
//	                .layout(FlexLayout.VERTICAL)
//	                .margin(FlexMarginSize.LG)
//	                .spacing(FlexMarginSize.SM)
//	                .contents(asList(place))
//	                .build();
//	    }
	   
	   

	@Override
	public FlexMessage get() {
		// TODO Auto-generated method stub
		return null;
	}

}


//String Customer=arrOfStr[1].substring(0,arrOfStr[1].indexOf(">"));
//	 
//	 String TextRemaining=arrOfStr[2].substring(0,arrOfStr[2].indexOf(">"));
//	 
//	 String DoubleRemaining=arrOfStr[3].substring(0,arrOfStr[3].indexOf("/"));
//	 
//	 String UnitRemaining=arrOfStr[3].substring(arrOfStr[3].indexOf("/")+1,arrOfStr[3].indexOf(">"));
//	 
//	//outstandingbalance=ob 
//	 
//	 String TextOB=arrOfStr[4].substring(0,arrOfStr[4].indexOf(">"));
//	 
//	 String DoubleOB=arrOfStr[5].substring(0,arrOfStr[5].indexOf("/"));
//	 
//	 String UnitOB=arrOfStr[5].substring(arrOfStr[5].indexOf("/")+1,arrOfStr[5].indexOf(">"));
//	 
//	 String DueDate=arrOfStr[6].substring(0,arrOfStr[6].indexOf(">"));
//	 
//	 String DateCurrent=arrOfStr[7].substring(0,arrOfStr[7].indexOf(">"));
