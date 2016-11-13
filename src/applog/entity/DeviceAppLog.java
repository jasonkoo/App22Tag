package applog.entity;

import java.util.List;

import com.google.gson.Gson;

public class DeviceAppLog {
	private List<AppInfo> appList;
	private String android;
	private String source;
	private double dataFree;
	private double sdFree;
	private String channel;
	private String productName;
	private String deviceId;
	
	
	public List<AppInfo> getAppList() {
		return appList;
	}
	public void setAppList(List<AppInfo> appList) {
		this.appList = appList;
	}
	public String getAndroid() {
		return android;
	}
	public void setAndroid(String android) {
		this.android = android;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getDataFree() {
		return dataFree;
	}
	public void setDataFree(double dataFree) {
		this.dataFree = dataFree;
	}
	public double getSdFree() {
		return sdFree;
	}
	public void setSdFree(double sdFree) {
		this.sdFree = sdFree;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	public static void main(String[] args) {
		String line = "2015-04-09 00:00:00	{\"appList\":[{\"fileMD5\":\"36FCE60E9D4987302C8149410AC6274E\",\"packageName\":\"com.lenovo.lsf.device\",\"versionCode\":403071179,\"versionName\":\"V4.3.7.1179pi\"},{\"fileMD5\":\"2324C196411F2731519CFCFB32B1F835\",\"packageName\":\"com.android.browser\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"A8F88B97B33A02BE49299B789C3B6F0B\",\"packageName\":\"com.android.calculator2\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"2F863C33D9804A15096705D980C48204\",\"packageName\":\"com.android.calendar\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"CEEE7696723B7905892788452F77426C\",\"packageName\":\"com.android.contacts\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"8A9A016E3A0EB3F22B02F604C2967750\",\"packageName\":\"com.android.deskclock\",\"versionCode\":203,\"versionName\":\"2.0.3\"},{\"fileMD5\":\"D495E188BC400BEAFCA4797C005C834C\",\"packageName\":\"com.android.email\",\"versionCode\":410000,\"versionName\":\"4.1\"},{\"fileMD5\":\"726FC1895CCFA6E74196E58162D5FAD6\",\"packageName\":\"com.android.gallery3d\",\"versionCode\":40000,\"versionName\":\"1.1.40000\"},{\"fileMD5\":\"63B491254987C16DFF3AA677D77DD8DB\",\"packageName\":\"com.lenovo.launcher\",\"versionCode\":6400147,\"versionName\":\"6.4.147.150116.65238d8_ZM10088\"},{\"fileMD5\":\"DA9CEAF62B688F0D1F866B902019D445\",\"packageName\":\"com.lenovo.phonecity\",\"versionCode\":2000010,\"versionName\":\"2.0.10.0625.05d86(all)\"},{\"fileMD5\":\"9145B7B81C3C0F75EB48CBABB33DF3BD\",\"packageName\":\"com.lenovo.leos.appstore\",\"versionCode\":61610,\"versionName\":\"6.16.20.88\"},{\"fileMD5\":\"FCDD0878D77A684E29B12EA4B154E9D7\",\"packageName\":\"com.lenovo.launcher.theme.magic\",\"versionCode\":116,\"versionName\":\"v2.0.16\"},{\"fileMD5\":\"54FB6C4A097E5213E9F57BC6CB20007F\",\"packageName\":\"com.android.mms\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"AF59A312C4EF90E2DC345BCC206149EF\",\"packageName\":\"com.android.music\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"C6F993696C94809E023C87E94F1BA330\",\"packageName\":\"com.android.settings\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"9E3000AED314C695687E78700B28463D\",\"packageName\":\"com.android.soundrecorder\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"BB582A1FAB9F1345AEE01C353914108B\",\"packageName\":\"com.android.yepvieoplayer\",\"versionCode\":1,\"versionName\":\"1.1\"},{\"fileMD5\":\"C957787C7399F0CE9F81923A032A54AC\",\"packageName\":\"com.lenovo.anyshare\",\"versionCode\":4020752,\"versionName\":\"2.7.52\"},{\"fileMD5\":\"FF9FF4FF9170AD271564C1FDB71A77C4\",\"packageName\":\"com.yep.customer.service\",\"versionCode\":1,\"versionName\":\"1.0\"},{\"fileMD5\":\"FAFB3F14EAA4898953B43F58C05B54CA\",\"packageName\":\"com.android.providers.downloads.ui\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"502819929EE71BA3D2D117301058ECE4\",\"packageName\":\"com.lenovo.ota\",\"versionCode\":2040037,\"versionName\":\"2.4.37.1112.a7641_cn_rdv2key\"},{\"fileMD5\":\"78441C0F7C013753D866F50F0D2DE73E\",\"packageName\":\"com.lenovo.safecenter\",\"versionCode\":6223435,\"versionName\":\"v6.2.2.3435\"},{\"fileMD5\":\"4F89C55AC54E371285A40F2CDD4BE76B\",\"packageName\":\"com.lenovo.service\",\"versionCode\":2015021200,\"versionName\":\"2.9.16\"},{\"fileMD5\":\"B5A6F3C051CB01FC682808CAB3718D58\",\"packageName\":\"com.lenovo.leos.cloud.sync\",\"versionCode\":3942100,\"versionName\":\"4.2.2\"},{\"fileMD5\":\"618546A28500E023D87F97B459BA4CA7\",\"packageName\":\"com.yep.pppmanager\",\"versionCode\":1,\"versionName\":\"1.0\"},{\"fileMD5\":\"18282CED7EB3D84E990998639D8D9BD4\",\"packageName\":\"com.android.quicksearchbox\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"CF38CDEF17242A835F34CA6071104A01\",\"packageName\":\"com.android.stk\",\"versionCode\":16,\"versionName\":\"4.1.2-A385e_S110_131219\"},{\"fileMD5\":\"495E4ECAC7FB2E6464B9AF9DD0FFF5E1\",\"packageName\":\"com.yeptelecom.vnet\",\"versionCode\":1,\"versionName\":\"1.0\"},{\"fileMD5\":\"1A26881695DE39ECB3C15FE0F36BF269\",\"packageName\":\"com.mediatek.filemanager\",\"versionCode\":1,\"versionName\":\"1.0\"},{\"fileMD5\":\"149DBC94FDA6C01D8EFA31CA0B664419\",\"packageName\":\"com.tencent.mobileqq\",\"versionCode\":228,\"versionName\":\"5.5.0\"},{\"fileMD5\":\"0F113FF92F70A998D0DCF6E23AAD5457\",\"packageName\":\"com.sohu.inputmethod.sogou\",\"versionCode\":430,\"versionName\":\"7.3\"},{\"fileMD5\":\"D6DC9CD4F2FC2F108B7A39FA288FAE8E\",\"packageName\":\"com.dianxinos.superuser\",\"versionCode\":174,\"versionName\":\"3.0.3\"},{\"fileMD5\":\"D61DF5ED148D063EEBD7C24F4036F4D2\",\"packageName\":\"com.lenovo.onekeylock\",\"versionCode\":1,\"versionName\":\"1.0\"},{\"fileMD5\":\"1000C879F30A58E13A5F0FEC3C1C9A30\",\"packageName\":\"com.smile.gifmaker\",\"versionCode\":416,\"versionName\":\"4.16\"},{\"fileMD5\":\"84FC11335108D2F0935545DF44BFCCF8\",\"packageName\":\"com.snda.wifilocating\",\"versionCode\":652,\"versionName\":\"3.2.12\"},{\"fileMD5\":\"EAB156B0FA36EE0F18487FD6E0286148\",\"packageName\":\"com.adobe.flashplayer\",\"versionCode\":111115081,\"versionName\":\"11.1.115.81\"},{\"fileMD5\":\"86B903CE01DE41D47D7452415D5AE1B3\",\"packageName\":\"com.tencent.qqmusic\",\"versionCode\":215,\"versionName\":\"5.1.0.7\"},{\"fileMD5\":\"A3E1FBD8093CFBCDB365A0872326A0D5\",\"packageName\":\"com.nice.main\",\"versionCode\":43,\"versionName\":\"3.2.0\"}],\"android\":\"4.1.2\",\"source\":\"LSF\",\"dataFree\":233.94921875,\"sdFree\":1023.5,\"channel\":\"C_LSF\",\"productName\":\"LNV-Lenovo A385e\",\"deviceId\":\"A100003AA2A9C4\"}";
		String[] part = line.split("\t");
		Gson g = new Gson();
		DeviceAppLog deviceAppLog = g.fromJson(part[1], DeviceAppLog.class);
		System.out.println(deviceAppLog.appList.size());
		System.out.println(deviceAppLog.getDeviceId());
	}
}
