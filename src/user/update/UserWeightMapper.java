package user.update;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * input<br>
 * user\u0001app(app1,app2,...,)\u0001type(download,install,use,uninstall)<br>
 * app,app对标签1的权重,app对标签2的权重,...<br><br>
 * 
 * @author hanxiaoten
 *
 */
public class UserWeightMapper extends Mapper<Object, Text, Text, Text> {
	public static int varNum = 33;

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] part = value.toString().trim().split(",");
		if (part.length == (varNum + 1)) { // app对标签的权重列表
			String weight = value.toString();
			weight = weight.substring(1 + weight.indexOf(','));// 取第一个逗号之后的
			context.write(new Text(part[0]), new Text(weight));
		} else {
			String[] word = value.toString().trim().split("\u0001");
			if (word.length == 3 && !word[1].equals("null,")) {
				String[] apps = word[1].split(",");
				for (String app : apps) {
					if (!app.equals("")) {
						context.write(new Text(app.trim()), new Text(word[0] + "," + word[2]));
					}
				}
			}
		}
	}
	
}
