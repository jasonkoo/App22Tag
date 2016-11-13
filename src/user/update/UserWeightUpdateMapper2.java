package user.update;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * input<br>
 * user\tapp对标签1的权重,app对标签2的权重,...<br>
 * user,user对标签1的权重,user对标签2的权重,...,总计数<br><br>
 * 
 * @author hanxiaoten
 *
 */
public class UserWeightUpdateMapper2 extends Mapper<Object, Text, Text, Text> {
	public static int varNum = 33;
	
	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] part = value.toString().trim().split("\\t");
		if (part.length == 2) {
			context.write(new Text(part[0]), new Text(part[1] + ",1")); // 1是用作reduce中计数的
		} else if (part.length == 1 && value.toString().trim().contains(",")) { 
			String temp = value.toString().trim();
			String user = temp.substring(0, temp.indexOf(","));
			int count = Integer.parseInt(temp.substring(temp.lastIndexOf(",") + 1));
			String[] cs = temp.substring(temp.indexOf(",") + 1, temp.lastIndexOf(",")).split(",");
			if (cs.length == varNum) {
				StringBuffer sb = new  StringBuffer();
				sb.append(Float.parseFloat(cs[0]) * count);
				for (int i = 1; i < varNum; i++) {
					sb.append("," + Float.parseFloat(cs[i]) * count);
				}
				sb.append("," + count);
				context.write(new Text(user), new Text(sb.toString()));
			} else {
				return;
			}
		} else {
			return;
		}
	}
	
	public static void main(String[] args) {
//		String value = "3gwUID8530644fbc9f51ee71d737ee20	0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0,0,0,0,0,0,0,0,1,0,0";
//		String value2 = "00013647572,0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.005693423,0.0013438404,0.018525766,0.02599994,0.0,0.0,0.0072660744,0.0,0.0,0.0,0.0,0.0,0.038771186,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0014247685,0.0,9.820134E-4,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.021138513,0.0,0.042331573,0.0,0.0,0.0,0.0,0.0,0.0,0.0070700855,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.00705695,0.0,0.0,0.0,0.0,0.0,0.0,0.053061843,0.0,0.0,0.0147778,0.0,0.08105408,0.02608433,0.0,0.0,0.0,0.08379938,0.024942752,0.0,0.0,0.0,0.025085697,0.004678537,0.0021898982,0.0,0.0,0.01199664,0.13154355,0.1305206,0.16093946,0.045149777,0.010436198,0.0,0.008387892,0.0,0.0,0.0,0.0,0.0,0.0,0.007747464,0,0,0,0,0,0,0,0,0,0,0,10";
//		String temp = value2.toString().trim();
//		String user = temp.substring(0, temp.indexOf(","));
//		int count = Integer.parseInt(temp.substring(temp.lastIndexOf(",") + 1));
//		System.out.println(count);
//		String[] cs = temp.substring(temp.indexOf(",") + 1, temp.lastIndexOf(",")).split(",");
//		StringBuffer sb = new  StringBuffer();
//		sb.append(Float.parseFloat(cs[0]) * count);
//		for (int i = 1; i < 154; i++) {
//			sb.append("," + Float.parseFloat(cs[i]) * count);
//		}
//		System.out.println(user + "::::" + sb.toString());
	}
}