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
import com.linecorp.bot.model.message.flex.component.Button.ButtonHeight;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Spacer;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class FlexMsgProductPrice  implements Supplier<FlexMessage>{
	 Configs config=new Configs();
	 String web =config.getWeb();
	 String linkproduct=config.getDeepLinkProduct();
	 String imageherolink=config.getImageHeaderlink();
	 String steptest=config.getStepTest();
	
		String linkimage=config.getLinkImage();
		
		Callrest rest=new Callrest();
	public FlexMessage get(String Userid,String token,String cvname) throws URISyntaxException, ClientProtocolException, IOException {
        final Image heroBlock = createHeroBlock(Userid);
        final Box bodyBlock = createBodyBlock(cvname);
        final Box footerBlock = createFooterBlock(token);

        final Bubble bubbleContainer = Bubble.builder()
        		.hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .size(BubbleSize.KILO)
                .build();
        return new FlexMessage("ProductPrice", bubbleContainer);
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
	 
	   private Box createBodyBlock(String cvname) {
	        final Text title = Text.builder()
	                .text("รายการสินค้าย้อนหลัง")
	                .weight(Text.TextWeight.BOLD)
	                .size(FlexFontSize.LG)
	                .build();
	        
	        if(cvname != null && cvname != "") {
	        	
	        	final Box review = createInfoBox(cvname);
		        return Box.builder()
		                .layout(FlexLayout.VERTICAL)
		                .contents(asList(title,review))
		                //.contents(asList(title))
		                .build();
		        
	        }else {

		        return Box.builder()
		                .layout(FlexLayout.VERTICAL)
		                .contents(asList(title))
		                .build();
	        }
	        

	    }
	   
	   private Box createInfoBox(String cvname) {
//	        final Box place = Box.builder()
//	                .layout(FlexLayout.BASELINE)
//	                .spacing(FlexMarginSize.MD)
//	                
//	                .contents(asList(
//	                        Text.builder()
//	                            .text("ชื่อ : ")
//	                            .color("#aaaaaa")
//	                            .size(FlexFontSize.Md)
//	                            .flex(1)
//	                            .build(),
//	                        Text.builder()
//	                            .text(cvname)
//	                            .wrap(true)
//	                            .color("#666666")
//	                            .flex(5)
//	                            .build()
//	                )).build();
//	       
//	        return Box.builder()
//	                .layout(FlexLayout.VERTICAL)
//	                .margin(FlexMarginSize.LG)
//	                .spacing(FlexMarginSize.SM)
//	                .contents(asList(place))
//	                .build();
		   
		   final Box place1 = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)	                
	                .contents(asList(
	                        Text.builder()
	                            .text("    "+"สำหรับคุณลูกค้า")
	                            .color("#aaaaaa")
	                            .size(FlexFontSize.Md)
	                            .flex(1)
	                            .build()
	                )).build();
	        
	        final Box place2 = Box.builder()
	                .layout(FlexLayout.BASELINE)
	                .spacing(FlexMarginSize.MD)	                
	                .contents(asList(
	                        Text.builder()
	                            .text("    "+cvname)
	                            .wrap(true)
	                            .color("#aaaaaa")
	                            .flex(5)
	                            .build()
	                )).build();
	       
	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .margin(FlexMarginSize.LG)
	                .spacing(FlexMarginSize.SM)
	                .contents(asList(place1,place2))
	                .build();
		   
	    }
	   
	   private Box createFooterBlock(String token) throws URISyntaxException {
	        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
	        final Button websiteAction = Button.builder()
	                .style(Button.ButtonStyle.LINK)
	                .height(ButtonHeight.SMALL)
	              //  .action(new URIAction("ข้อมูลเพิ่มเติม", web+linkproduct+token))
	                .action(new URIAction("รายละเอียด",new URI(web+linkproduct+token),new AltUri(new URI(web+linkproduct+token))))
	                .build();
	        final Separator separator = Separator.builder().build();
//	        final Button callAction = Button.builder()
//	                .style(Button.ButtonStyle.LINK)
//	                .height(ButtonHeight.MEDIUM)
//	                .action(new URIAction("Call Center", "tel:1788"))
//	                .build();
	       

	        return Box.builder()
	                .layout(FlexLayout.VERTICAL)
	                .spacing(FlexMarginSize.SM)
	                //.contents(asList(spacer, callAction, separator, websiteAction))
	                .contents(asList(spacer, separator, websiteAction))
	                .build();

	    }

	@Override
	public FlexMessage get() {
		// TODO Auto-generated method stub
		return null;
	}

}
