package datingsimulator.domain;

import datingsimulator.dao.StoryReader;

public class Logic {

    private StoryReader reader;
    private int points = 0;
    private int line = 1;
    private int finalLine = 1;
    private boolean continueGame;

    public Logic(StoryReader reader) {
        this.reader = reader;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayersReplyToButton(int buttonNumber) throws Exception {
        String[] replies = getPlayersReplies(line);
        return replies[buttonNumber - 1];
    }

    public String getDatesReply() throws Exception {
        String[] parts = getLine(line);
        String reply = parts[0];
        return reply;
    }

    public String getFinalReply() throws Exception {
        String finalAnswer = reader.getFinalAnswer(finalLine);
        return finalAnswer;
    }

    public boolean continueGame() {
        return continueGame;
    }

    public void findNextAndUpdate(int buttonNumber) throws Exception {
        if (line == 1) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                line = 2;
                continueGame = true;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                line = 3;
                continueGame = true;
            } else {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 1;
                continueGame = false;
            }
        } else if (line == 2 || line == 3) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                line = 4;
                continueGame = true;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                line = 5;
                continueGame = true;
            } else {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 1;
                continueGame = false;
            }
        } else if (line == 4) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 3;
                continueGame = false;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 4;
                continueGame = false;
            } else {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 2;
                continueGame = false;
            }
        } else if (line == 5) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 1;
                continueGame = false;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 5;
                continueGame = false;
            } else {
                points = points + getReplyPoints(buttonNumber);
                line = 6;
                continueGame = true;
            }
        } else {
            points = points + getReplyPoints(buttonNumber);
            finalLine = 6;
            continueGame = false;
        }
    }

    private String[] getPlayersReplies(int line) throws Exception {
        String[] parts = getLine(line);
        String[] replies = null;
        replies[0] = parts[1];
        replies[1] = parts[3];
        replies[2] = parts[5];
        return replies;
    }

    private int getReplyPoints(int buttonNumber) throws Exception {
        String[] parts = getLine(line);
        int[] points = null;
        points[0] = Integer.parseInt(parts[2]);
        points[1] = Integer.parseInt(parts[4]);
        points[2] = Integer.parseInt(parts[6]);
        return points[buttonNumber - 1];
    }

    private String[] getLine(int line) throws Exception {
        try {
            String[] part = reader.getPartOfStory(line);
            return part;
        } catch (Exception e) {

        }
        return null;
    }

}