import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List <String> list = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Jason and George's Search Engine" + "\n" + "Search Engine:" + "\n" + list.toString();
        } 
        else if (url.getPath().equals("/add")) {

            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list.add(parameters[1]);
                    return String.format("%s was added to the search engine!", parameters[1]);
                }
            }
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                List <String> list2 = new ArrayList<String>();
                String retS = "";
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).indexOf(parameters[1])>=0) {
                            list2.add(list.get(i));
                            retS = retS + list.get(i) + "\n";
                        }

                    }
                    return retS;
                }
            }

            
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
