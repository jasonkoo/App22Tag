package applog.parse;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * Input:
 * uploadTime\001pkgName1,pkgName2,.......
 * Output:
 * key: nullWritable
 * value: deviceId\001pkgName1,pkgName2,.......\001install\001maxUploadTime
 * 
 * @author gulei2
 *
 */
public class ParseReducer extends Reducer<Text, Text, NullWritable, Text> {
	
	@Override
	public void reduce(Text key, Iterable<Text> valueSet, Context context) throws IOException, InterruptedException {
		
		String deviceId = key.toString();
		String maxUploadTime = "0000-00-00 00:00:00";
		String pkgList = null;
		
		for (Text val : valueSet) {
			String[] part = val.toString().split("\001");
			if (part.length == 2 && part[0].compareTo(maxUploadTime) > 0) {
				maxUploadTime = part[0];
				pkgList = part[1];
			}
		}
		
		context.write(NullWritable.get(), new Text(deviceId + "\001" + pkgList + "\001" + "install" + "\001" + maxUploadTime));
	}
	
}
