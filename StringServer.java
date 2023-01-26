import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> s = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String list = Arrays.toString(s.toArray()).replace("[", "").replace("]", "").replace(", ", "");
            return list;
        } 

        else if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                s.add(parameters[1]);
                s.add("\n");
                return "";
            }
            return "addError";
        }
        else {
            return "404 Not Found!";
        }
    }
}


