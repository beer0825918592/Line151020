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
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class  FlexConfirmPayment implements Supplier<FlexMessage> {
	 Configs config=new Configs();
	String imageherolink=config.getImageHeaderlink();
	String web=config.getWeb();
	String linkimage=config.getLinkImage();
	String steptest=config.getStepTest();
	
	Callrest rest=new Callrest();
	
	public FlexMessage get(String Userid,String InvNo , String Price , String ReceiptNo , String ReDate , String viewUrl) throws URISyntaxException, ClientProtocolException, IOException {
        final Image heroBlock = createHeroBlock(Userid);
        final Box bodyBlock = createBodyBlock(InvNo,Price , ReceiptNo , ReDate,viewUrl);
        final Box footerBlock = createFooterBlock(viewUrl);


        
        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
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
	 
	   private Box createBodyBlock(String InvNo , String Price , String ReceiptNo , String ReDate, String viewUrl) {
	        final Text title = Text.builder()
	                .text("ยืนยันการชำระเงิน")
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.XL)
	                .build();
	        
	        	
	        	final Box review = createInfoBox( InvNo ,  Price ,  ReceiptNo , ReDate,viewUrl);
		        return Box.builder()
		                .layout(FlexLayout.VERTICAL)
		                .contents(asList(title,review))
		                .build();	  
	        

	    }
	   
	   private Box createInfoBox(String InvNo , String Price , String ReceiptNo , String ReDate , String viewUrl) {
	        final Box place = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)
	                .contents(asList(
	                        Text.builder()
	                            .text("Invoice No : ")
	                            .color("#aaaaaa")
	                            .size(FlexFontSize.SM)
	                            .flex(1)
	                            .build(),
	                        Text.builder()
	                            .text(InvNo)
	                            .wrap(true)
	                            .color("#666666")
	                            .flex(5)
	                            .build()
	                )).build();
	       
	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .margin(FlexMarginSize.LG)
	                .spacing(FlexMarginSize.SM)
	                .contents(asList(place))
	                .build();
	    }
	   
	   private Box createFooterBlock(String viewUrl) throws URISyntaxException {
	        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
	       
	        final Separator separator = Separator.builder().build();
	        final Button websiteAction = Button.builder()
	                .style(Button.ButtonStyle.LINK)
	                .height(ButtonHeight.SMALL)
	              
	                .action(new URIAction("View Order Details",new URI(viewUrl),new AltUri(new URI(viewUrl))))
	                .build();
	       

	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .spacing(FlexMarginSize.SM)
	                .contents(asList(spacer, separator, websiteAction))
	                .build();

	    }

	@Override
	public FlexMessage get() {
		// TODO Auto-generated method stub
		return null;
	}

}
