package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Reads the part of the story that is needed.
public class StoryReader {

    private File story;
    private File finalAnswers;
    private Scanner reader;

    public StoryReader(String storyFile, String finalAnswersFile) {
        story = new File(storyFile);
        finalAnswers = new File(finalAnswersFile);
    }

    public String getFinalAnswer(int line) throws Exception {
        String answer = null;
        int i = 1;
        try {
            reader = new Scanner(finalAnswers);
            while (reader.hasNextLine()) {
                answer = reader.nextLine();
                if (i == line) {
                    return answer;
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(finalAnswers);
            writer.close();
        }
        return null;
    }

    public String[] getPartOfStory(int line) throws Exception {
        String[] part = null;
        int i = 1;
        try {
            reader = new Scanner(story);
            while (reader.hasNextLine()) {
                part = reader.nextLine().split(";");
                if (i == line) {
                    return part;
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(story);
            writer.close();
        }
        return null;
    }

}
