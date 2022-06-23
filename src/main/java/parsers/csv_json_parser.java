package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class csv_json_parser {

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        System.out.println(json);
    }
public static String listToJson(List<Employee> list){
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    Type listType = new TypeToken<List>() {}.getType();
    StringBuilder stringBuilder = new StringBuilder();
    var result = gson.toJson(list,listType);
        return result;
}

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> cpms = new ColumnPositionMappingStrategy();
            cpms.setType(Employee.class);
            cpms.setColumnMapping(columnMapping);
            CsvToBean csv = new CsvToBeanBuilder<>(csvReader)
                    .withMappingStrategy(cpms)
                    .build();
            List<Employee> employees = csv.parse();
            return employees;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}