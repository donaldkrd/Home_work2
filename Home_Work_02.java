import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Home_Work_02 {
    public static void main(String[] args) throws SecurityException, IOException {
        task_01();
        Logger log = Logger.getLogger(Home_Work_02.class.getName());
        FileHandler fh = new FileHandler("log.xml");
        log.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
        log.log(Level.WARNING, "Log level WARNING");
    }
    static void task_01() {
        String json = "{[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}, " +
        "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}, " +
        "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]}";
        json = json.substring(3, json.length() - 3);
        json = json.replace("{", "");
        json = json.replace("}", "");
        json = json.replace("\"", "");
        String[] string = json.split(", ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < string.length; i++) {
            String[] str = string[i].split(",");
            for (int j = 0; j < str.length; j++) {
                String[] data = str[j].split(":");
                for (int k = 0; k < data.length; k++) {
                    list.add(data[k]);
                }
            }
        }       
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("фамилия")) {
                result.append("Студент");
            } else if (list.get(i).equals("оценка")) {
                if (list.get(i - 1).equals("Петрова")) {
                    result.append("получила");
                } else {
                    result.append("получил");
                }                
            } else if (list.get(i).equals("предмет")) {
                result.append("по предмету");
            } else {
                if (list.get(i).equals("Математика")) {
                    result.append(" " + list.get(i) + ". ");
                } else if (list.get(i).equals("Информатика")) {
                    result.append(" " + list.get(i) + ". ");
                } else if (list.get(i).equals("Физика")) {
                    result.append(" " + list.get(i) + ". ");
                } else {
                    result.append(" " + list.get(i) + " ");
                }
            }
        }
        System.out.println(result);
        task_02(result);
    }
    
    static void task_02(StringBuilder str) {
        try (FileWriter fw = new FileWriter("file.txt", false)) {
            fw.append(str);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}