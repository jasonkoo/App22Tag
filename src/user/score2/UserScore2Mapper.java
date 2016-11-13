package user.score2;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * input<br>
 * user,user对标签1的权重,user对标签2的权重,...,总计数<br>
 * user,user对标签1的权重,user对标签2的权重<br>
 * 
 * @author hanxiaoten
 *
 */
public class UserScore2Mapper extends Mapper<Object, Text, Text, Text> {

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] part = value.toString().trim().split(",");
		String weight = value.toString();
		weight = weight.substring(weight.indexOf(',') + 1);
		context.write(new Text(part[0]), new Text(weight));
	}
	
}
