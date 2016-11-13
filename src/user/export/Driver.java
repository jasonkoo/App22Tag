package user.export;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import applog.merge.MergeMapper;
import applog.merge.MergeReducer;

public class Driver extends Configured implements Tool {	
	
	private static final String INPUTPATHS = "inputPath";
	private static final String NUMREDUCERS = "numReducers";
		

	public int run(String[] args) throws Exception {
		
		Configuration conf = getConf();		
		
		String inputPaths = conf.get(INPUTPATHS);
		int numReducers = conf.getInt(NUMREDUCERS, 10);
		
		System.out.println("inputPaths: " + inputPaths);
		System.out.println("numReducers: " + numReducers);
	
		Job job = new Job(conf);
		
		job.setJobName("Merge applogs at " + new Date() + ": " + inputPaths);
		job.setJarByClass(Driver.class);
		
		FileInputFormat.setInputPaths(job, inputPaths);
		
		job.setMapperClass(MergeMapper.class);
		job.setReducerClass(MergeReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setNumReduceTasks(numReducers);
		job.waitForCompletion(true);
		
		return 0;
	}	
	
	public static void main(String[] args) throws Exception {		
		ToolRunner.run(new Configuration(), new Driver(), args);
	}
}
