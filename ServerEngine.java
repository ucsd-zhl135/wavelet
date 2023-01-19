import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    HashSet<String> a;
    Handler(){
        a = new HashSet<String>();
    }
    public String handleRequest(URI url) {
        String path = url.getPath();
        if(path.equals("/add")){
            String[] params = url.getQuery().split("=");
            if(params.length < 2 || !params[0].equals("s")) return "Invalid Parameter";
            String s = params[1]; 
            a.add(s);
            return String.format("Added %s",s);
        } else if(path.equals("/search")){
            String[] params = url.getQuery().split("=");
            if(params.length < 2 || !params[0].equals("s")) return "Invalid Parameter";
            String s = params[1]; 
            ArrayList<String> res = new ArrayList<String>();
            for(String t : a){
                if(t.contains(s)){
                    res.add(t);
                }
            }   
            return res.toString();
        } 
        return "";
    }
}

class ServerEngine{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
