package user.update;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class UserWeightUpdateDriver {

	private static final String INPUT = "input";
	private static final String INPUT2 = "input2";
	private static final String APPMODEL = "model";
	private static final String TEMP = "temp";
	private static final String OUTPUT = "output";
	private static final String REDUCE_TASK_NUM = "mapred.reduce.tasks";

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		new GenericOptionsParser(conf, args);
		String input = conf.get(INPUT);
		String input2 = conf.get(INPUT2);
		String model = conf.get(APPMODEL);
		String temp = conf.get(TEMP);
		String output = conf.get(OUTPUT);
		String reduceTaskNums = conf.get(REDUCE_TASK_NUM, "4");
        
		
        System.out.println("--------------------------------------------------");
        System.out.println("Arguments passed in: ");
        System.out.println("input: " + input);
        System.out.println("input2: " + input2);
        System.out.println("model: " + model);
        System.out.println("temp: " + temp);
        System.out.println("output: " + output);
        System.out.println("reduceTaskNums: " + reduceTaskNums);
        System.out.println("--------------------------------------------------");
		
		FileSystem fs = FileSystem.get(conf);
		if (fs.exists(new Path(output))) {
			fs.delete(new Path(output), true);
		}
		if (fs.exists(new Path(temp))) {
			fs.delete(new Path(temp), true);
		}

		Job job1 = new Job(conf);
		job1.setJobName("App-Tag-User");
		job1.setJarByClass(UserWeightUpdateDriver.class);
		job1.setMapperClass(UserWeightMapper.class);
		job1.setReducerClass(UserWeightReducer.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(Text.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		job1.setNumReduceTasks(Integer.parseInt(reduceTaskNums));
		
		FileInputFormat.setInputPaths(job1, input + "," + model);
		FileOutputFormat.setOutputPath(job1, new Path(temp));
		job1.waitForCompletion(true);

		Job job2 = new Job(conf);
		job2.setJobName("User-Tag");
		job2.setJarByClass(UserWeightUpdateDriver.class);
		job2.setMapperClass(UserWeightUpdateMapper2.class);
		job2.setCombinerClass(UserWeightUpdateCombiner.class);
		job2.setReducerClass(UserWeightUpdateReducer2.class);

		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		job2.setNumReduceTasks(Integer.parseInt(reduceTaskNums));
		
		Path out = new Path(output);
		FileInputFormat.setInputPaths(job2, temp + "," + input2);
		FileOutputFormat.setOutputPath(job2, out);
		int res  = job2.waitForCompletion(true) ? 0 : 1;
		if (res == 0) {
			System.out.println("done--->");
			fs.delete(new Path(temp), true); // 删除第一个Reduce的输出目录
			fs.delete(new Path(input2), true); // 删除原有累积用户打标目录
			fs.rename(out, new Path(input2)); // 重命名新的用户打标目录
		} else {
			System.out.println("failed.");
		}
	}

}
