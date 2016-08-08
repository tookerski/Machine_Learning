package com.trendwise.java;

//This wraps the modules into a single programpackage com.trendwise.java; //Package name
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class LineCount {
    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable obj = new IntWritable(1);
        private Text words = new Text("Total Lines");
        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                        Reporter reporter) throws IOException {
            output.collect(words, obj);
        }
    }

    public static class Reduce extends MapReduceBase implements
            Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values,
                           OutputCollector<Text, IntWritable> output, Reporter reporter)
                throws IOException {
            int sum1 = 0;
            while (values.hasNext()) {
                sum1 += values.next().get();
            }
            output.collect(key, new IntWritable(sum1));
        }
    }

    public static void main(String[] args) throws Exception {
        JobConf config = new JobConf(LineCount.class);
        config.setJobName("LineCount");
        config.setOutputKeyClass(Text.class);
        config.setOutputValueClass(IntWritable.class);
        config.setMapperClass(Map.class);
        config.setCombinerClass(Reduce.class);
        config.setReducerClass(Reduce.class);
        config.setInputFormat(TextInputFormat.class);
        config.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(config, new Path(args[0]));
        FileOutputFormat.setOutputPath(config, new Path(args[1]));
        JobClient.runJob(config);
    }
}
