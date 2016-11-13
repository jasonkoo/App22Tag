package user.update;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserWeightUpdateReducer2 extends Reducer<Text, Text, Text, Text> {
	public static int varNum = 33;

	@Override
	public void reduce(Text key, Iterable<Text> valueSet, Context context) throws IOException, InterruptedException {
		float[] sum = new float[varNum];
		Arrays.fill(sum, 0.0f);
		int count = 0;
		for (Text value : valueSet) {
			String[] seg = value.toString().split(",");
			if (seg.length == varNum + 1) {
				for (int i = 0; i < varNum; i++) {
					sum[i] += Float.parseFloat(seg[i]);
				}
				count += Integer.parseInt(seg[varNum]); // 提取出map中最后添加的计数
			} else {
				context.write(null, new Text("wrong format!"));
			}
		}

		// scale up sum
		StringBuilder result = new StringBuilder();
		result.append(key.toString() + ",");
		result.append(sum[0] / (float) count);
		for (int i = 1; i < varNum; i++) {
			sum[i] = sum[i] / (float) count;
			if (sum[i] == Float.NaN) { // 一旦遇到非数值记录，直接跳过该用户
				return;
			}
			result.append(",");
			result.append(sum[i]);
		}
		result.append("," + count); // 保存总数，用于增量更新
		context.write(null, new Text(result.toString()));
	}
	
}
