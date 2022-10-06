import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    List<String> SearchList = new ArrayList<String>();
    List<String> ReturnList = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Start Searching");
        } else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                SearchList.add(parameters[1]);
                return String.format("Object added!");
            }
        } else if(url.getPath().equals("/search")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for(int i = 0; i < SearchList.size(); i++){
                    if(SearchList.get(i).contains(parameters[1])){
                        ReturnList.add(SearchList.get(i));
                    }
                }
                return String.format("Searched Object: " + ReturnList);
            } 
        }
        return "404 Not Found!";
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
