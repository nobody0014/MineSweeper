package NetworkProgramming;
import java.io.*;
import java.net.Socket;

/**
 * Created by wit on 7/11/2016.
 */
public class DailyAdviceClient {
    public void go(){
        try{
            Socket s = new Socket("127.0.0.1",4242);
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today you should: " + advice);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
