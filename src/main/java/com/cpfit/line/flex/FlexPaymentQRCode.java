package com.cpfit.line.flex;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Supplier;

import org.apache.http.client.ClientProtocolException;

import com.iphayao.linebot.Callrest;
import com.iphayao.linebot.Configs;
import com.linecorp.bot.model.action.URIAction;
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
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class FlexPaymentQRCode implements Supplier<FlexMessage>{
	
	Configs config=new Configs();
	 String imageherolink=config.getImageHeaderlink();
	 String linksupport=config.getLinkSupport();
		String callcenter=config.getCallCenter();
		String web=config.getWeb();
		String linkimage=config.getLinkImage();
		String steptest=config.getStepTest();
		
		
		String step="";
		
		Callrest rest=new Callrest();
		
		
	public FlexMessage get(String Userid,String InvNo , String Price , String ReceiptNo , String ReDate ,int FileId) throws URISyntaxException, ClientProtocolException, IOException {
        final Image heroBlock = createHeroBlock(Userid);
        final Box bodyBlock = createBodyBlock(InvNo,Price , ReceiptNo , ReDate);
        final Box footerBlock = createFooterBlock();

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .size(BubbleSize.KILO)
                .build();
        
        return new FlexMessage("QrCode" ,bubbleContainer);
        
    }
	
	 private Image createHeroBlock(String Userid) throws URISyntaxException, ClientProtocolException, IOException {
String imagehero=rest.getImageFlexHero(Userid);
step=rest.getStepTest(Userid);

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
	 
	   private Box createBodyBlock(String InvNo , String Price , String ReceiptNo , String ReDate) throws URISyntaxException {
	        final Text title = Text.builder()
	                .text("Invoice No : "+InvNo)
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.LG)
	                .build();
	        
	        final Text amount = Text.builder()
	                .text("ยอดค้างชำระ  ฿"+Price)
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.LG)
	                .build();
	        
	        final Text duedate = Text.builder()
	                .text("ครบกำหนดชำระ  "+ReDate)
	                //.weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.Md)
	                .build();
	        
	        	
	        	//final Box review = createInfoBox( InvNo ,  Price ,  ReceiptNo , ReDate,viewUrl);
//	        String url="";
//	        if(step.equals("true")||step.equals("!Config")) {
//	        	url="https://km.mhesi.go.th/sites/default/files/Barcode_%E0%B8%9A%E0%B8%B2%E0%B8%A3%E0%B9%8C%E0%B9%82%E0%B8%84%E0%B9%89%E0%B8%94.jpg";
//	        }else{
//	        	url=web+linkimage+FileId;
//	        }
	        
	        
	        	final Image qrcode=Image.builder()
	        			// .url(new URI(web+linkimage+FileId))
	        			// .url(new URI("https://image.shutterstock.com/image-vector/sample-qr-code-ready-scan-600w-92618269.jpg"))
	        			 .url(new URI("https://txt.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5LbXh2WjJsMGIyZHZLaUF0TXprdFRHOW5hWFJ2WjI4LC4w/code.39-logitogo.png"))
	        			
	        			 .size(Image.ImageSize.FULL_WIDTH)
		                .aspectRatio(ImageAspectRatio.R20TO13)
		                .aspectMode(ImageAspectMode.Fit)
		                .build();
	        	
	        	
	        	return Box.builder()
		                .layout(FlexLayout.VERTICAL)
		               // .contents(asList(title,review,qrcode))
		                .contents(asList(title,amount,duedate,qrcode))
		                .build();	  
	        

	    }
	   
	   private Box createInfoBox(String InvNo , String Price , String ReceiptNo , String ReDate) {
	        final Box place = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)
	                .contents(asList(
	                        Text.builder()
	                            .text("ยอดค้างชำระ : ฿ ")
	                            .color("#aaaaaa")
	                            .size(FlexFontSize.XL)
	                         //   .flex(1)
	                            .build(),
	                        Text.builder()
	                            .text(Price)
	                         //   .wrap(true)
	                            .color("#666666")
	                          //  .flex(5)
	                            .build()
	                )).build();
	        
	        final Box place2 = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)
	                .contents(asList(
	                        Text.builder()
	                            .text("ครบกำหนดชำระ  ")
	                            .color("#aaaaaa")
	                            .size(FlexFontSize.Md)
	                           // .flex(1)
	                            .build(),
	                        Text.builder()
	                            .text(ReDate)
	                          //  .wrap(true)
	                            .color("#666666")
	                           // .flex(5)
	                            .build()
	                )).build();
	        
	        
	        
	       
	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .margin(FlexMarginSize.LG)
	                .spacing(FlexMarginSize.SM)
	                .contents(asList(place,place2))
	                .build();
	    }
	   
	   private Box createFooterBlock() {
	        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
	       
	        final Separator separator = Separator.builder().build();
//	        final Button websiteAction = Button.builder()
//	                .style(Button.ButtonStyle.LINK)
//	                .height(ButtonHeight.SMALL)
//	                .action(new URIAction("View Order Details", viewUrl))
//	                .build();
	        
	        final Box place = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)
	                .contents(asList(
	                        Text.builder()
	                            .text("BarCode ใช้ชำระผ่าน ")
	                            .weight(Text.TextWeight.BOLD)
	                            //.color("#aaaaaa")
	                            .size(FlexFontSize.Md)
	                            .align(FlexAlign.CENTER)
	                            .flex(1)
	                            .build()
	                )).build();
	        
	        final Box place2 = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)
	                .contents(asList(
	                        	 Text.builder()
	                            .text("Counter Service ")
	                           // .color("#aaaaaa")
	                            .size(FlexFontSize.Md)
	                            .weight(Text.TextWeight.BOLD)
	                            .align(FlexAlign.CENTER)
	                            .flex(1)
	                            .build()
	                )).build();
	       

	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .spacing(FlexMarginSize.SM)
	               // .contents(asList(spacer, separator, websiteAction))
	                .contents(asList(spacer, separator,place,place2))
	                .build();

	    }

	
	public FlexMessage get() {
		// TODO Auto-generated method stub
		return null;
	}
}
