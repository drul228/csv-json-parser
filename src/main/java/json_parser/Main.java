//package json_parser;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.List;
//
//public class Main {
//    public static String readString (String file) {
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
//           JsonParser jsonParser = new JsonParser();
//           return jsonParser.parse(reader);
//        }catch (IOException e){
//            e.getMessage();
//        }
//    }
//    public static void main(String[] args) {
//        String json = readString ("new_data.json");
//    }
//}
