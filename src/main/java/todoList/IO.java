package todoList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IO {

    static final String path = "src/main/java/todoList/savefile.json";

    static boolean save(JList<String> list) {

        JSONObject obj = new JSONObject();
        ListModel<String> listModel = list.getModel();
        JSONArray jsonArray = new JSONArray();
        List<String> l = IntStream.range(0, listModel.getSize()).mapToObj(listModel::getElementAt).collect(Collectors.toList());
        jsonArray.addAll(l);

        obj.put("list", jsonArray);

        try {
            FileWriter fw = new FileWriter(path);
            fw.write(obj.toJSONString());
            fw.flush();
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static DefaultListModel<String> read() throws Exception {
        String f = readFile();
        System.out.println(f);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject((Map) parser.parse(f));
        JSONArray jsonArray = (JSONArray) jsonObject.get("list");
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (Object o : jsonArray) {
            lm.addElement(o.toString());
        }
        return lm;
    }

    private static String readFile() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            java.lang.String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

