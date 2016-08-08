package com.letsdobigdata;					// This wraps the modules into a single program package
import java.io.IOException;					// importing the classes to your program
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {		// reducing starts from here
    public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        int maxValue = Integer.MIN_VALUE;				// reduce is function used for reducing purpose
        for (IntWritable value : values) {
            maxValue = Math.max(maxValue, value.get());
        }
        context.write(key, new IntWritable(maxValue));			// copying the max value to context class using write function.
    }
}
