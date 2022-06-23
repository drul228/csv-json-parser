package parsers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XmlJsonParser {
    public static void writeString (String json,String file) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(json);
            bufferedWriter.flush();


        }catch (IOException e){
            e.getMessage();
        }
        }

        public static List<Employee> parseXML(File file) {
        List<Employee> list = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Node root = document.getDocumentElement();
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            hashMap.put(bookProp.getNodeName(), bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    Employee employee = new Employee();
                    employee.setAge(Integer.parseInt(hashMap.get("age")));
                    employee.setCountry((String) hashMap.get("country"));
                    employee.setId((Long.parseLong(hashMap.get("id"))));
                    employee.setFirstName((String) hashMap.get("firstName"));
                    employee.setLastName((String) hashMap.get("lastName"));
                    list.add(employee);
                }


            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public static void main(String[] args) {
        File file = new File("data.xml");
        List<Employee> list = parseXML(file);
        String jsonStr = CsvJsonParser.listToJson(list);
        writeString(jsonStr,"data.json");

    }

}

