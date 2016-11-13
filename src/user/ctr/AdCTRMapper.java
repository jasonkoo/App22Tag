package user.ctr;


import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * input<br>
 * pid\001deviceid\001eventtype\001adid\001apppkgname<br><br>
 * 
 * @author Jason Koo
 *
 */
public class AdCTRMapper extends Mapper<Object, Text, Text, Text> {
	
	
	@Override
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] segs = value.toString().trim().split("\001");
		String adid = segs[3];
		String deviceid = segs[1];
		String eventtype = segs[2];
		context.write(new Text(adid), new Text(deviceid + "\001" + eventtype));
	}
}
