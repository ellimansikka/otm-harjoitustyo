package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads the part of the story that is needed.
 *
 * @author ellimans
 */
public class StoryReader {

    private File story;
    private File finalAnswers;
    private Scanner reader;

    public StoryReader(String storyFile, String finalAnswersFile) {
        story = new File(storyFile);
        finalAnswers = new File(finalAnswersFile);
    }

    /**
     * Finds the date's last answer in the game from the finalAnswers-file. The
     * game ends after this answer.
     *
     * @param line number from the file
     * @return date's last answer in the game
     * @throws Exception
     */
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

    /**
     * Finds the part of the story from the story-file from the line that is
     * given.
     *
     * @param line number from the file
     * @return part of the story
     * @throws Exception
     */
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
