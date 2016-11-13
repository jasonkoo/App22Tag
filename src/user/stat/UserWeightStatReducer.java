package user.stat;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserWeightStatReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	@Override
	protected void reduce(Text label, Iterable<LongWritable> vals, Context context) throws IOException, InterruptedException {
		long sum = 0;
		for (LongWritable val : vals) {
			sum += val.get();
		}
		context.write(label, new LongWritable(sum));
	}
}
