package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static List<Employee> jsonToList(String json) {
        List <Employee> list = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            for (Object obj : jsonArray
                    ) {
                Employee employee = gson.fromJson(String.valueOf(obj),Employee.class);
                list.add(employee);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
return list;
    }

    public static String readString(String fileName) {
        String jsonText;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            jsonText = bufferedReader.readLine();
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonText;
    }

    public static void main(String[] args) {
        String json = readString("data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employe:
             list) {
            System.out.println(employe);
        }

    }
}