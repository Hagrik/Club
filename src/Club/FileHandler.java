package Club;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {


    public LinkedList<Member> readFile(){
        LinkedList<Member> members = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Member member;
        try(BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            lineRead = reader.readLine();
            while (lineRead!=null){
                splitLine = lineRead.split(" ");
                if (splitLine[0].equals("S")){
                    member = new SingleClubMember('S', Integer.parseInt(splitLine[1]),
                            splitLine[2], Double.parseDouble(splitLine[3]),Integer.parseInt(splitLine[4]));
                }else {
                    member = new MultiClubMember('M', Integer.parseInt(splitLine[1]),
                            splitLine[2], Double.parseDouble(splitLine[3]),Integer.parseInt(splitLine[4]));
                }
                members.add(member);
                lineRead = reader.readLine();
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }


    public void appendFile(String member){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(member + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void overwriteFile(LinkedList<Member> member){
        String string;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))){
            for (Member value : member) {
                string = value.toString();
                writer.write(string + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            File file = new File("members.csv");
            File tfile = new File("members.temp");
            file.delete();
            tfile.renameTo(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
