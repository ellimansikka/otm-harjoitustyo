package datingsimulator.domain;

import datingsimulator.dao.StoryReader;

/**
 * Game logic class
 *
 * @author ellimans
 */
public class Logic {

    private StoryReader reader;
    private int points;
    private int line;
    private int finalLine;
    private boolean continueGame;
    private int imageNumber;

    /**
     * Sets the starting parameters.
     *
     * @param reader StoryReader
     */
    public Logic(StoryReader reader) {
        this.reader = reader;
        continueGame = true;
        line = 1;
        finalLine = 1;
        points = 0;
        imageNumber = 1;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Returns correct player's reply option to the button.
     *
     * @param buttonNumber 1-3
     * @return reply option to the button
     * @throws Exception
     */
    public String getPlayersReplyToButton(int buttonNumber) throws Exception {
        String[] replies = getPlayersReplies(line);
        return replies[buttonNumber - 1];
    }

    /**
     * Returns correct date's reply
     *
     * @return date's reply
     * @throws Exception
     */
    public String getDatesReply() throws Exception {
        String[] parts = getLine(line);
        String reply = parts[0];
        return reply;
    }

    /**
     * Returns the correct final reply from the date. The game ends after.
     *
     * @return final reply
     * @throws Exception
     */
    public String getFinalReply() throws Exception {
        String finalAnswer = reader.getFinalAnswer(finalLine);
        return finalAnswer;
    }

    /**
     * Returns the number of the image that needs to be shown
     *
     * @return imageNumber
     */
    public int getImageNumber() {
        return imageNumber;
    }

    /**
     * Tells if the game should continue
     *
     * @return true if the game should continue, false if not
     */
    public boolean continueGame() {
        return continueGame;
    }

    /**
     * Actual logic method. Determines whether the game continues and which line
     * comes next based on the button that the player pushed and the current
     * point of the story. Also adds points based on the button and line.
     *
     * @param buttonNumber 1-3
     * @throws Exception
     */
    public void findNextAndUpdate(int buttonNumber) throws Exception {
        if (line == 1) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                line = 2;
                imageNumber = 1;
                continueGame = true;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                line = 3;
                imageNumber = 2;
                continueGame = true;
            } else {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 1;
                line = 0;
                imageNumber = 3;
                continueGame = false;
            }
        } else if (line == 2 || line == 3) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                line = 4;
                imageNumber = 1;
                continueGame = true;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                line = 5;
                imageNumber = 2;
                continueGame = true;
            } else {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 1;
                line = 0;
                imageNumber = 3;
                continueGame = false;
            }
        } else if (line == 4) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 3;
                line = 0;
                imageNumber = 2;
                continueGame = false;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 4;
                line = 0;
                imageNumber = 3;
                continueGame = false;
            } else {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 2;
                line = 0;
                imageNumber = 1;
                continueGame = false;
            }
        } else if (line == 5) {
            if (buttonNumber == 1) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 1;
                line = 0;
                imageNumber = 3;
                continueGame = false;
            } else if (buttonNumber == 2) {
                points = points + getReplyPoints(buttonNumber);
                finalLine = 5;
                line = 0;
                imageNumber = 1;
                continueGame = false;
            } else {
                points = points + getReplyPoints(buttonNumber);
                line = 6;
                imageNumber = 1;
                continueGame = true;
            }
        } else {
            points = points + getReplyPoints(buttonNumber);
            finalLine = 6;
            line = 0;
            imageNumber = 2;
            continueGame = false;
        }
    }

    private String[] getPlayersReplies(int line) throws Exception {
        String[] parts = getLine(line);
        String[] replies = new String[3];
        replies[0] = parts[1];
        replies[1] = parts[3];
        replies[2] = parts[5];
        return replies;

    }

    private int getReplyPoints(int buttonNumber) throws Exception {
        if (buttonNumber > 0 && buttonNumber < 4) {
            String[] parts = getLine(line);
            int[] points = new int[3];
            points[0] = Integer.parseInt(parts[2]);
            points[1] = Integer.parseInt(parts[4]);
            points[2] = Integer.parseInt(parts[6]);
            return points[buttonNumber - 1];
        } else {
            return 0;
        }

    }

    private String[] getLine(int line) throws Exception {
        String[] part = reader.getPartOfStory(line);
        return part;

    }

}
