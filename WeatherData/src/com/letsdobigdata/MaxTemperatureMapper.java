package com.letsdobigdata;				// This wraps the modules into a single program package
import java.io.IOException;				// importing the classes to your program
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> { // mapping starts from here
    private static final int MISSING = 9999;
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException { // map is function used for mapping purpose
        String line = value.toString();			// coverting value to sring type and assigning to line
        String year = line.substring(15, 19);
        int airTemperature;
        if (line.charAt(87) == '+') { 					// parseInt doesn't like leading plus
            airTemperature = Integer.parseInt(line.substring(88, 92));	// extracting 88 to 92 columns values
        } else {
            airTemperature = Integer.parseInt(line.substring(87, 92));      // extracting 87 to 92 columns values
        }
        String quality = line.substring(92, 93);			// extracting 92 and 93 columns values
        if (airTemperature != MISSING && quality.matches("[01459]")) {
            context.write(new Text(year), new IntWritable(airTemperature));
        }
    }
}