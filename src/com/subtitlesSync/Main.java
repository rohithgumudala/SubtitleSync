package com.subtitlesSync;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int startTimeInSeconds = 0;
        int endTimeInSeconds = 0;

        int startSeconds = 0;
        int startMinutes = 0;
        int startHours = 0;

        int endSeconds = 0;
        int endMinutes = 0;
        int endHours = 0;

        int adjustTime = -67;

        try {
            File inputFile = new File("C:\\Users\\rohit\\Downloads\\1.srt");
            Scanner myReader = new Scanner(inputFile);
            File OutputFile = new File("C:\\Users\\rohit\\Downloads\\newSub.srt");
            FileWriter myWriter = new FileWriter(OutputFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains("-->")){

                   startTimeInSeconds = Integer. parseInt(data.substring(0,2))*60*60 + Integer.parseInt(data.
                           substring(3,5))*60 + Integer.parseInt(data.substring(6,8));
                   endTimeInSeconds = Integer. parseInt(data.substring(17,19))*60*60 + Integer.parseInt(data.
                           substring(20,22))*60 + Integer.parseInt(data.substring(23,25));

                   startTimeInSeconds += adjustTime;
                   endTimeInSeconds += adjustTime;

                   startSeconds = startTimeInSeconds%60;
                   startTimeInSeconds /= 60;
                   startMinutes = startTimeInSeconds%60;
                   startTimeInSeconds /= 60;
                   startHours = startTimeInSeconds%60;

                   endSeconds = endTimeInSeconds%60;
                   endTimeInSeconds /= 60;
                   endMinutes = endTimeInSeconds%60;
                   endTimeInSeconds /= 60;
                   endHours = endTimeInSeconds%60;

                   data = data.substring(0,1) + startHours + ":" + (startMinutes<10 ? "0" + startMinutes : startMinutes)
                           + ":" + (startSeconds<10 ? "0" + startSeconds : startSeconds) + data.substring(8,12) + " --> "
                           + data.substring(17,18) + endHours + ":" + (endMinutes<10 ? "0" + endMinutes : endMinutes)
                           + ":" + (endSeconds<10 ? "0" + endSeconds : endSeconds) + data.substring(25,29);
                }
                myWriter.write(data+"\n");
            }
            myReader.close();
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
