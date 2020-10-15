package com.cpfit.line.flex;

import static java.util.Arrays.asList;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.function.Supplier;

import com.iphayao.linebot.Configs;
import com.iphayao.linebot.Data;
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
import com.linecorp.bot.model.message.flex.container.FlexContainer;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class ListFlexPaymentQRCode implements Supplier<FlexMessage>{
	
	Configs configs=new Configs();
	String web=configs.getWeb();
	String  linkimage=configs.getLinkImage();
	public FlexMessage get(ArrayList<Data> a) throws URISyntaxException {
		
		
		
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();
        final Box footerBlock = createFooterBlock();

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .size(BubbleSize.KILO)
                .build();
        

        
        return new FlexMessage("QrCode" ,bubbleContainer);
        
    }
	
	public FlexMessage getd(ArrayList<Data> a) throws URISyntaxException {
		
		  final Image heroBlock = createHeroBlock();
	        final Box bodyBlock = createBodyBlock();
	        final Box footerBlock = createFooterBlock();

	        final Bubble bubbleContainer = Bubble.builder()
	                .hero(heroBlock)
	                .body(bodyBlock)
	                .footer(footerBlock)
	                .size(BubbleSize.KILO)
	                .build();
	        
//	        FlexContainer e=new FlexContainer() {bubbleContainer,bubbleContainer};
//	        e
		
	        return new FlexMessage("aaaa", bubbleContainer);
	
	}
	
	 private Image createHeroBlock() throws URISyntaxException {
	        return Image.builder()
	                .url(new URI("https://lh3.googleusercontent.com/n3OkmJox9IP4psvhbLSUP9Ztkr79OcQIYiizxUd6exTDfYwwOiRaQnz2AMxaE5TpKZ1D"))
	                .size(Image.ImageSize.FULL_WIDTH)
	                .aspectRatio(ImageAspectRatio.R16TO9)
	                .aspectMode(ImageAspectMode.Cover)
	                .build();
	    }
	 
	   private Box createBodyBlock() throws URISyntaxException {
	        final Text title = Text.builder()
	                .text("Invoice No : ")
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.LG)
	                .build();
	        
	        final Text amount = Text.builder()
	                .text("ยอดค้างชำระ  ฿")
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.LG)
	                .build();
	        
	        final Text duedate = Text.builder()
	                .text("ครบกำหนดชำระ  ")
	                //.weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.Md)
	                .build();
	        
	        	
	        	//final Box review = createInfoBox( InvNo ,  Price ,  ReceiptNo , ReDate,viewUrl);
		        
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
	   
	   private Box createInfoBox() {
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
	                            .text("")
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
	                            .text("")
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
