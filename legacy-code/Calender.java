package Projindan.src;

import java.util.*;
import java.io.*;
import java.net.*;
/*import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;*/

public class Calender {

    public class event {
        //TODO
        // Complete elements of a event (Föreläsning, Övning, etc..)
        public String event_type;
        public String localization;
        public int date; //YYYYMMDD. Maybe split into seperate?
        public int start; //Time of day
        public int end;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        System.out.print("Enter iCalender link: ");  
        String str= sc.nextLine();              //reads string
        URL url = new URL(str);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            FileWriter writer = new FileWriter("Calender2.ics");
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write('\n');
            }
            writer.close();
        }
    }
}
