package applog.parse;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import applog.entity.AppInfo;
import applog.entity.DeviceAppLog;

import com.google.gson.Gson;

/**
 * Input:
 * 2015-04-15 00:00:00     {"appList":[{"fileMD5":"603A3B62A3F07D6FCE7B94F8F05377CE","packageName":"com.lenovo.lsf.device","versionCode":403071184,"versionName":"V4.3.7.1184pi"},{"fileMD5":"0F45904D55A5B2F3FD612B5F185C0DEC","packageName":"com.lenovo.launcher.theme.age","versionCode":201,"versionName":"v3.0.0"}],"android":"4.2.2","source":"LSF","dataFree":2099.4140625,"sdFree":2049.4140625,"channel":"C_LSF","productName":"Lenovo A628t","deviceId":"863377021677016"}
 * Output:
 * key: deviceId
 * value: uploadTime\001pkgName1,pkgName2,.......
 * 
 * @author gulei2
 *
 */
public class ParseMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	private Gson g = new Gson();
	private String uploadTime = null;
	private String deviceId = null;
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] part = value.toString().trim().split("\t");
		if (part.length == 2 && part[1].startsWith("{")) {
			uploadTime = part[0];
			try {
				DeviceAppLog deviceAppLog = g.fromJson(part[1], DeviceAppLog.class);
				deviceId = deviceAppLog.getDeviceId();
				StringBuilder sb = new StringBuilder();
				sb.append(uploadTime);
				sb.append("\001");
 				for (AppInfo app : deviceAppLog.getAppList()) {
					sb.append(app.getPackageName());
					sb.append(",");
				}
 				sb.deleteCharAt(sb.length() - 1);
				context.write(new Text(deviceId), new Text(sb.toString()));
			} catch (Exception e) {}			
		} 
	}	
}
