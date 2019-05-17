package TechJobsConsole;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 *  \author    Matthew Moore
 *  \date      16-05-2019
 *  \version   1.1.0
 *  \brief     Get data from CSV
 *  \details   Parse and Format CSV, and the class can also return ArrayLists that contain jobs that match user input.
 */
public class JobData {

    /**
     * Loads the csv file
     */
    private static final String DATA_FILE = "resources/job_data.csv";
    /**
     * Determines if the data is already loaded
     */
    private static Boolean isDataLoaded = false;

    /**
     * Contains all the jobs
     */
    private static ArrayList<HashMap<String, String>> allJobs;

    /**
     * Fetch list of all values from loaded data,
     * without duplicates, for a given column.
     *
     * \param field The column to retrieve values from
     * \return List of all of the values of the given field
     */
    public static ArrayList<String> findAll(String field) {

        // load data, if not already loaded
        loadData();

        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {
            String aValue = row.get(field);

            if (!values.contains(aValue)) {
                values.add(aValue);
            }
        }

        // Sort values alphabetically
        Collections.sort(values);
        return values;
    }

    /**
     * Return all jobs from the csv file
     * \return all jobs
     */
    public static ArrayList<HashMap<String, String>> findAll() {

        // load data, if not already loaded
        loadData();

        return new ArrayList(allJobs);
    }

    /**
     * Returns results of search the jobs data by key/value, using
     * inclusion of the search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * \param column   Column that should be searched.
     * \param value Value of teh field to search for
     * \return List of all jobs matching the criteria
     */
    public static ArrayList<HashMap<String, String>> findByColumnAndValue(String column, String value) {

        // load data, if not already loaded
        loadData();

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {

            String aValue = row.get(column);

            if (aValue.toLowerCase().contains(value.toLowerCase())) {
                jobs.add(row);
            }
        }

        return jobs;
    }

    /**
     * Read in data from a CSV file and store it in a list
     */
    private static void loadData() {

        // Only load data once
        if (isDataLoaded) {
            return;
        }

        try {

            // Open the CSV file and set up pull out column header info and records
            Reader in = new FileReader(DATA_FILE);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            allJobs = new ArrayList<>();

            // Put the records into a more friendly format
            for (CSVRecord record : records) {
                HashMap<String, String> newJob = new HashMap<>();

                for (String headerLabel : headers) {
                    newJob.put(headerLabel, record.get(headerLabel));
                }

                allJobs.add(newJob);
            }

            // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load job data");
            e.printStackTrace();
        }
    }

    /**
     * Returns results of search the jobs data by key/value, using
     * inclusion of the search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * \param[value] Value of the field to search for
     * \return List of all jobs matching the criteria
     */
    public static ArrayList<HashMap<String, String>> findByValue(String value) {

        loadData();

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {

            for (String key : row.keySet()) {
                if (row.get(key).toLowerCase().contains(value.toLowerCase())) {
                    jobs.add(row);
                }
            }
        }

        // Remove all duplicate values from ArrayList by converting the ArrayList to a HashSet
        HashSet jobsSet = new HashSet(jobs);

        // Reset the jobs variable to have the jobs that are not-duplicates from the HashSet
        jobs = new ArrayList(jobsSet);

        return jobs;
    }

}
