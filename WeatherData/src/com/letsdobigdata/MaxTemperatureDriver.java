package com.letsdobigdata;// This wraps the modules into a single program package
import org.apache.hadoop.conf.Configured;		// importing the classes to your program
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/*This class is responsible for running map reduce job*/
public class MaxTemperatureDriver extends Configured implements Tool{
    public int run(String[] args) throws Exception
    {
        if(args.length !=2) {
            System.err.println("Usage: MaxTemperatureDriver <input path> <output path>");
            System.exit(-1);
        }

        Job job = new Job();				// configurations starts from here
        job.setJarByClass(MaxTemperatureDriver.class);
        job.setJobName("Max Temperature");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0:1);
        boolean success = job.waitForCompletion(true);		// setting success variable to true
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {		//execution starts from here
        MaxTemperatureDriver driver = new MaxTemperatureDriver();		//creating driver object of MaxTemperatureDriver class and assigning a memory using new keyword
        int exitCode = ToolRunner.run(driver, args);
        System.exit(exitCode);

    }

}