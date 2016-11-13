package user.update;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserWeightReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> valueSet, Context context) throws IOException, InterruptedException {
		String weight = null;
		Set<String> user = new HashSet<String>();
		for (Text value : valueSet) {
			String[] seg = value.toString().split(",");
			if (seg.length == 2) {
				user.add(value.toString());
			} else { // 找到app对标签的权重列表
				weight = value.toString();
			}
		}
		if (weight != null) {
			for (String u : user) {
				String[] ss = u.split(",");
				if (ss[1].equals("uninstall")) { // 如果是卸载，则判负分
					context.write(new Text(ss[0]), new Text(negative(weight)));
				} else { // 如果是下载、安装、使用，则判正分
					context.write(new Text(ss[0]), new Text(weight));
				}
			}
		} else {
			return;
		}
	}
	
	/**
	 * 正数转负数
	 * 
	 * @param s
	 * @return
	 */
	private static String negative(String s) {
		String[] ss = s.split(",");
		StringBuffer sb = new StringBuffer();
		sb.append("-" + ss[0]);
		for (int i = 1; i < ss.length; i++) {
			sb.append("," + "-" + ss[i]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(negative("1.1,4.4,2,3.0"));
	}
}
