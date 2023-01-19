import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Client {

    public static void main(String[] args) {


        String hostname = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(hostname, port)) {

            InputStream input = socket.getInputStream();
            Scanner scn= new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuilder sb=new StringBuilder();
            System.out.println("Do you need to request the islands_in_the_stream.txt? (Enter 1)");
            int answer=scn.nextInt();
            if(answer==1) {
                while ((line = reader.readLine()) != null && reader.ready()) {
                    if (line.equals(""))
                        continue;
                    else if (line.length() == 0)
                        break;
                    sb.append(line).append(" ");
                }
                if (line != null)
                    sb.append(line);
            String str = sb.toString().replace(",","");
//            System.out.println(str);


            most_frequent_occurrences(str);//list and prints top 10 frequently occurred words and count
            }else {
                System.out.println("please run the client file again to request the islands_in_the_stream.txt");
            }

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }

    }

    private static void most_frequent_occurrences(String str) {
        HashMap<String,Integer>map=new HashMap<>();
        PriorityQueue<Pair>pq=new PriorityQueue<>();
        String[] words=str.split(" ");
        for(String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }

        //get top 10 most common occurrences
        for(String key:map.keySet()){
            pq.add(new Pair(key,map.get(key)));
        }

        System.out.println("Most Common words and their frequency:");
        int k=10;
        while (pq.size()>0 && k>0){
            k--;
            Pair rem=pq.remove();
            System.out.println(rem.word+"   --> "+rem.freq);
        }


    }

   static class Pair implements Comparable<Pair>{
        String word;
        int freq;
        Pair(String word,int freq){
            this.word=word;
            this.freq=freq;
        }

        @Override
        public int compareTo(Pair other) {
            return other.freq-this.freq;
        }

    }

}
