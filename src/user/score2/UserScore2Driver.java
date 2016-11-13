package user.score2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class UserScore2Driver {

	private static final String INPUT = "input";
	private static final String INPUT2 = "input2";
	private static final String OUTPUT = "output";
	private static final String REDUCE_TASK_NUM = "mapred.reduce.tasks";

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		new GenericOptionsParser(conf, args);
		String input = conf.get(INPUT);
		String input2 = conf.get(INPUT2);
		String output = conf.get(OUTPUT);
		String reduceTaskNums = conf.get(REDUCE_TASK_NUM, "4");
		
		System.out.println("--------------------------------------------------");
	    System.out.println("Arguments passed in: ");
	    System.out.println("input: " + input);
	    System.out.println("input2: " + input2);
	    System.out.println("output: " + output);
	    System.out.println("reduceTaskNums: " + reduceTaskNums);
	    System.out.println("--------------------------------------------------");
        
		Job job1 = new Job(conf);
		job1.setJobName("User-score");
		job1.setJarByClass(UserScore2Driver.class);
		job1.setMapperClass(UserScore2Mapper.class);
		job1.setReducerClass(UserScore2Reducer.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(Text.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		job1.setNumReduceTasks(Integer.parseInt(reduceTaskNums));
		
		FileInputFormat.setInputPaths(job1, input + "," + input2);
		FileOutputFormat.setOutputPath(job1, new Path(output));
		job1.waitForCompletion(true);
		
	}

}
