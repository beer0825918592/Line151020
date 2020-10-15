package com.iphayao.linebot;

public class Configs {
	/////
	private String Web="http://localhost:8082/";
	
	//private String Web="http://localhost:8082/";
	
	
	private String DeepLinkLogin="BU/LINE/stringtext?token=";
	
	private String DeeplinkOnlineOrder="";
	
	private String DeepLinkProduct="BU/LINEProduct/stringtext?token=";
	
	private String DeepLinkQrCode="BU/LINEQRCODE/stringtext?token=";
	
	private String DeepLinkOrderStatus="BU/LINEOrderStatus/stringtext?token=";
	
	private String DeepLinkOrderINVDetail="BU/LINEOrderINVDetail/stringtext?token=";
	
	private String DeepLinkOrderSODetail="BU/LINEOrderSODetail/stringtext?token=";
	
	private String WordUnknown="กรุณาใส่คำให้ถูกต้อง";
	
	
	//////use message no text 
	private String AutoReplyMessage ="กรุณาใส่ข้อมูลให้ถูกต้อง";
	
	private String TextPayment="กรุณาเลือกรูปแบบการจ่ายเงิน";
	
	private String TextError="ไม่สามารถดึงข้อมูลได้";
	
	private String TextNoConfigBU="ไม่พบ Config ของ BU";
	
	private String TextNoConfig="ไม่พบ Config ของ ระบบ";
	
	private String TextNoRest="ไม่สามารถเรียก Service ได้ ";
	
	private String TextNotSetupCv="ไม่สามารถใช้ระบบได้เนื่องจากยังไม่ได้ลงทะเบียน";
	
	private String TextNoToken="ไม่พบข้อมูล token";
			
	private String TextNoLink="ไม่พบข้อมูล link เบื้องต้น";	
	
	private String TextNoUserid="กรุณาลงทะเบียนก่อน";
	
	private String TextContact="โปรดติดต่อพนักงานขาย";
	
	//////call rest 
	private String OnlineOrder="rest/linerestonlineorder/v1/OnlineOrder";
	
	private String OrderStatus="";
	
	
	private String ProductPrice="rest/linerestproduct/v1/Product";
	
	private String CheckBalance="rest/linerestcheckbalance/v1/CheckBalance";
	
	private String Payment="";
	
	private String PaymentBarCode="rest/linerestbarcode/v1/BarCode";
	
	private String PaymentQrCode="rest/linerestqrcode/v1/QrCode";
	
	private String Contact="rest/linerestcontact/v1/Contact";
	
	private String TokenLogin ="rest/lineresttoken/v1/LineRestTokenLogin";
	
	private String TokenOther="rest/lineresttoken/v1/LineRestToken";
	
	private String CheckID="rest/linerestchecklineuserid/v1/Checkid";
	
	private String CVName="rest/linerestcvname/v1/getName";
	
	private String Follow="rest/linerestfollow/v1/LineRestFollow";
	
	private String Unfollow="rest/linerestunfollow/v1/LineRestUnfollow";
	
	private String ProfileName="rest/linerestprofilename/v1/LineRestProfileName";
	
	private String ImageFlexHero="rest/myservice/v1/LineRestImageFlexHero";
	
	private String StepTest="rest/lineresttest/v1/LineRestTest";
	
	///// link image mendix
	
	private String LinkImage="images/File/";
	
	///// link image flex
	
	private String ImageHerolink="https://i.ibb.co/51FmS3F/MFPC-LOGO.png";
	
	///link Support 
	
	private String LinkSupport="https://www.cpfreshmartfoodservice.com/Support/Complain";
	
	private String CallCenter="1788";
	
	private String strNewLine ="<NewLine>";
	
	
	public String getWeb() {
		return Web;
	}
	
	public String getAutoReplyMessage() {
		return AutoReplyMessage;
	}
	
	public String getWordUnknown() {
		return WordUnknown;
	}
	
	public String getTextPayment() {
		return TextPayment;
	}
	
	public String getTextError() {
		return TextError;
	}
	
	public String getDeepLinkLogin() {
		return DeepLinkLogin;
	}
	
	public String getDeepLinkProduct() {
		return DeepLinkProduct;
	}
	
	public String getDeepLinkQROCODE() {
		return DeepLinkQrCode;
	}
	
	public String getDeepLinkOrderStatus() {
		return DeepLinkOrderStatus;
	}
	
	public String getOnlineOrder() {
		return OnlineOrder;
	}
	
	public String getOrderStatus() {
		return OrderStatus;
	}
	
	public String getProductPrice() {
		return ProductPrice;
	}
	
	public String getCheckBalance() {
		return CheckBalance;
	}
	
	public String getPayment() {
		return Payment;
	}
	
	public String getPaymentBarCode() {
		return PaymentBarCode;
	}
	
	public String getPaymentQrCode() {
		return PaymentQrCode;
	}
	
	public String getContact() {
		return Contact;
	}
	
	public String getTokenLogin() {
		return TokenLogin;
	}
	
	public String getToken() {
		return TokenOther;
	}
	
	public String getCheckID() {
		return CheckID;
	}
	
	public String getCVName() {
		return CVName;
	}
	
	public String getLinkImage() {
		return LinkImage;
	}
	
	public String getFollow() {
		return Follow;
	}
	
	public String getUnFollow() {
		return Unfollow;
	}
	
	public String getProfileName() {
		return ProfileName;
	}
	
	public String getImageHeaderlink() {
		return ImageHerolink;
	}
	
	public String getLinkSupport() {
		return LinkSupport;
	}
	
	public String getCallCenter() {
		return CallCenter;
	}
	
	public String getDeeplinkOnlineOrder() {
		return DeeplinkOnlineOrder;
	}
	
	public String getTextNoConfigBU() {
		return TextNoConfigBU;
	}
	
	public String getTextNoConfig() {
		return TextNoConfig;
	}
	
	public String getTextNoRest() {
		return TextNoRest;
	}
	
	public String getTextNotSetUpCv() {
		return TextNotSetupCv;
	}
	
	public String getTextNoToken() {
		return TextNoToken;
	}
	
	public String getTextNoLink() {
		return TextNoLink;
	}
	
	public String getTextNoUserid() {
		return TextNoUserid;
	}
	
	public String getTextContact() {
		return TextContact;
	}
	
	public String getImageFlexHero() {
		return ImageFlexHero;
	} 
	
	public String getStepTest() {
		return StepTest;
	} 
	
	public String getStrNewLine() {
		return strNewLine;
	} 
	
	public String getDeepLinkOrderINVDetail() {
		return DeepLinkOrderINVDetail;
	}
	
	public String getDeepLinkOrderSODetail() {
		return DeepLinkOrderSODetail;
	}
	
	
}
