package user.ctr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class AdCTRDriver {
	
	private static final String INPUT = "input";
	private static final String OUTPUT = "output";
	private static final String REDUCE_TASK_NUM = "mapred.reduce.tasks";
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();
		new GenericOptionsParser(conf, args);
		String input = conf.get(INPUT);
		String output = conf.get(OUTPUT);
		String reduceTaskNums = conf.get(REDUCE_TASK_NUM);
		
		
		System.out.println("--------------------------------------------------");
        System.out.println("Arguments passed in: ");
        System.out.println("input: " + input);
        System.out.println("output: " + output);
        System.out.println("reduceTaskNums: " + reduceTaskNums);
        System.out.println("--------------------------------------------------");
        
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(new Path(output))) {
			fs.delete(new Path(output), true);
		}
        
        Job job = new Job(conf);
        job.setJobName("Ad CTR computation");
        job.setJarByClass(AdCTRDriver.class);
        job.setMapperClass(AdCTRMapper.class);
		job.setReducerClass(AdCTRReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(Integer.parseInt(reduceTaskNums));
		
		
		FileInputFormat.setInputPaths(job, input);
		FileOutputFormat.setOutputPath(job, new Path(output));
		boolean suc = job.waitForCompletion(true);
		if (suc) {
			System.out.println("job done!");
		}
	}
}
