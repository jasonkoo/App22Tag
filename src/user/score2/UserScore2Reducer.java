package user.score2;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserScore2Reducer extends Reducer<Text, Text, Text, Text> {
	public static int varNum = 33;

	@Override
	public void reduce(Text key, Iterable<Text> valueSet, Context context) throws IOException, InterruptedException {
		String[] score = null;
		String[] oldScore = null;
		float newScore = 0.0f;
		for (Text value : valueSet) {
			String[] seg = value.toString().split(",");
			if (seg.length == 33) {
				oldScore = seg;
			} else {
				score = seg;
			}
		}
		float t0 = Float.parseFloat(score[0]) - Float.parseFloat(oldScore[0]);
		if (t0 > 0) {
			newScore += t0;
		}
		for (int i = 1; i < 33; i++) {
			Float t = Float.parseFloat(score[i]) - Float.parseFloat(oldScore[i]);
			if (t > 0) {
				newScore += t;
			}
		}
		context.write(null, new Text(String.valueOf(newScore)));
	}
	
}
