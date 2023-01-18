import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> s = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return s.toString();
        } 
        else if (url.getPath().contains("/add")) {
            
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                s.add(parameters[1]);
                return "";
            }

        }
        //Search 
         else {
    
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String s1 = "";
                    for (String string : s) {
                        if(string.indexOf(parameters[1]) != -1){
                            s1 += (string+" and ");
                        }
                            
                    }
                    if(s1.isEmpty())
                        return s1;
                    else 
                        return s1.substring(0,(s1.length() - 4));
                }
            }
            return "404 Not Found!";
        }
        return "";
    }
}


public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
