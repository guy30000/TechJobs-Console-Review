package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);
    private static ArrayList<HashMap<String, String>> searchjob; //added this line //working on moving this to Jobdaya

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

                    // Print list of skills, employers, etc   //prints the list
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"      /////////////////Begining Search

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                    /////This should be removed and replaced by the line above
//                    searchjob = new ArrayList<>();
//                    for (HashMap<String, String> single_JobV : JobData.findAll()) {
//                        int stop = 0;
//                        for (Map.Entry<String, String> single_EntryV : single_JobV.entrySet()) {
//                            if (stop == 1) {
//                                continue;
//                            }
//                            if (single_EntryV.getValue().toUpperCase().contains(searchTerm.toUpperCase())) {
//                                stop = 1;
//                                searchjob .add(single_JobV);
//
//                            }
//                        }
//                    }
//                    printJobs(searchjob);
                    /////////////////End Search

                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                    //System.out.println(searchTerm + " Column " + searchField); /////////////////////////
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
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

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
    int n = 0;

    if (someJobs.isEmpty()) {
        System.out.println("*No results*");
    }
        for (HashMap<String, String> single_JobV : someJobs) {
            n = n + 1;
            System.out.println("***************" + n + "***************");
            for (Map.Entry<String, String> single_EntryV : single_JobV.entrySet()) {
                System.out.println(single_EntryV.getKey() + ":  " + single_EntryV.getValue() );
            }
            System.out.println("***************" + n + "***************\n");
        }

    }
}