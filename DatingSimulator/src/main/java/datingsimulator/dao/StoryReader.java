
package datingsimulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Reads the part of the story that is needed. Also stores temporarily the points for each answer and can return them.

public class StoryReader {
    private File story;
    private File finalAnswers;
    private Scanner reader;
    private int points1;
    private int points2;
    private int points3;
    
    public StoryReader(String storyFile, String finalAnswersFile) {
        story = new File(storyFile);
        finalAnswers = new File(finalAnswersFile);
    }
    
    public String getDatesReply(int line) throws Exception {
        String[] parts = getPartOfStory(line);
        String reply = parts[0];
        return reply;
    }
    
    public String[] getPlayersReplies(int line) throws Exception {
        String[] parts = getPartOfStory(line);
        String[] replies = null;
        replies[0] = parts[1];
        replies[1] = parts[3];
        replies[2] = parts[5];
        return replies;
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
    
    private String[] getPartOfStory(int line) throws Exception {
        String[] part = null;
        int i = 1;
        try {
            reader = new Scanner(story);
            while (reader.hasNextLine()) {
                part = reader.nextLine().split(";");
                if (i == line) {
                    points1 = Integer.parseInt(part[2]);
                    points2 = Integer.parseInt(part[4]);
                    points3 = Integer.parseInt(part[6]);
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
