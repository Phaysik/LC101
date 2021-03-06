package TechJobsConsole;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

/**
 *  \author    Matthew Moore
 *  \date      16-05-2019
 *  \version   1.1.0
 *  \brief     Print out jobs that match input
 *  \details   Based on user input, call the appropriate methods from the JobData file that will return an ArrayList with the jobs that are available.
 */
public class TechJobs {

    /**
     * Scanner to get user input
     */
    private static Scanner in = new Scanner(System.in);

    /**
     * Main method of TechJobs Class
     * \param[args] Arguments to the main method
     */
    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    /**
     * Gets and returns the column that the user requested
     * \param menuHeader String the indicates the column requested
     * \param choices Hashmap with all of the columns inside
     * \return The column the user selects
     */
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    /**
     * Prints a list of jobs
     * \param someJobs ArrayList of jobs
     */
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        if (someJobs.size() == 0) {
            System.out.println("There were no jobs that match your query.");
        } else {
            for (HashMap<String, String> job : someJobs) {
                System.out.println("\n****");
                for (String key : job.keySet()) {
                    System.out.println(String.format("%s: %s", key, job.get(key)));
                }
                System.out.println("****");
            }
        }
    }
}