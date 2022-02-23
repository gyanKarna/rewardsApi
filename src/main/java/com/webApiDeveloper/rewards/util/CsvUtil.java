package com.webApiDeveloper.rewards.util;

import com.opencsv.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CsvUtil {
    private static final Long miliOfNovemberFirst = 1635742800000L;
    private static final Long miliOfJanuaryLast = 1643608800000L;
    private static final Long threeDaysToMili = 259200000L;
    private static final String sampleDataFile = "sample-data.csv";

    public static String getRandomAmount() {
        Random rd = new Random(); // creating Random object
        return String.valueOf(rd.nextInt(250));
    }

    //Method to create a sample data set on application startup
    @PostConstruct
    public void writeData() {

        File file = new File(sampleDataFile);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter with ',' as separator
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // create a List which contains Data
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{"Customer Name", "Amount", "Date"});
            long date = miliOfNovemberFirst;

            while (date<=miliOfJanuaryLast){
                data.add(new String[]{"John Doe", getRandomAmount(), String.valueOf(date)});
                date+=threeDaysToMili;
            }

            writer.writeAll(data);
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //Method to return all data from sample csv file
    public List<String[]> getData(){

        List<String[]> data = new ArrayList<String[]>();
        try {
            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(sampleDataFile);

            // create csvParser object with
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            // Read all data at once
            data = csvReader.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
