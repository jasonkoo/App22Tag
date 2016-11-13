package user.ctr;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * input<br>
 * key: adid
 * value: deviceid\001eventtype<br><br>
 * 
 * @author Jason Koo
 *
 */
public class AdCTRReducer extends Reducer<Text, Text, Text, Text> {
	
	@Override
	protected void reduce(Text adid, Iterable<Text> vals, Context context) throws IOException, InterruptedException {
		double displayed = 0;
		double clicked = 0;
		for (Text val : vals) {
			String[] segs = val.toString().split("\001");
			if (segs.length == 2) {
				if (segs[1].equals("1")) {
					displayed += 1;
				} else if (segs[1].equals("2")) {
					clicked += 1;
				}
			}
		}
		if (clicked > 5000 && displayed > 0) {
			double crt = clicked / displayed;
			context.write(adid, new Text(String.valueOf(crt)));
		}
	}
}
