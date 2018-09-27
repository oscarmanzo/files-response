package com.fandango.files;

import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.util.*;

public class ParseFile {

    private static final String LOG_FILE = "C:\\Users\\oscar.manzo\\Globant\\Fandango\\Services\\TO_REBASE\\pos-service\\logs\\posservice-client.log";

    private static final String FIND_PERFORMANCE = "posPerformanceCode";
    private static final String FIND_FILE_NAME = "bodyFileName";

    public static final void main(String[] args) {
        try {
            ParseFile app = new ParseFile();
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws Exception {
        List<String> values = parseLog(new File(LOG_FILE));
        print(values);

        System.out.println("####################################################");

        Map<String, List<String>> map = fillMap(values);
        map.forEach((k,v) -> System.out.println(k));

        Map<String, List<String>> groups = groupByPerformance(values, map);
        print(groups);
    }

    private List<String> parseLog(File logFile) throws Exception {
        List<String> values = new ArrayList<>();

        Scanner sc = new Scanner(logFile);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            Optional<String> performanceOpt = findText(line, FIND_PERFORMANCE);
            if (performanceOpt.isPresent())
                values.add(performanceOpt.get());

            Optional<String> filenameOpt = findText(line, FIND_FILE_NAME);
            if (filenameOpt.isPresent())
                values.add(filenameOpt.get());
        }

        return values;
    }

    private Optional<String> findText(String textline, String toFind) {
        int index = textline.indexOf(toFind);

        if (index >= 0) {
            System.out.println("------------>"+ textline);
            String aux = FIND_PERFORMANCE.equals(toFind) ? "\":\"" : "\" : \"";
            return parse(textline.substring(index), toFind, aux);
        }

        return Optional.empty();
    }

    private Optional parse(String text, String toFind, String separator) {
        try {
            int indexAux = text.indexOf(separator);
            String subaux = text.substring(indexAux + separator.length());
            String value = subaux.substring(0, subaux.indexOf("\""));

            if (FIND_PERFORMANCE.equals(toFind) && (value==null || value.trim().isEmpty()))
                value = "0";

            return Optional.of(value);
        } catch (Exception e) {
            System.out.print("TO_PARSE:" + text);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Map<String, List<String>> groupByPerformance(List<String> values, Map<String, List<String>> map){
        Map<List<String>, List<String>> groups = new HashMap<>();
        Stack<String> stack = new Stack<>();

        for (String value : values){
            if (!NumberUtils.isNumber(value)){
                stack.push(value);
            } else {
                List<String> files = map.get(value);

                while (!stack.isEmpty()){
                    files.add(stack.pop());
                }
            }
        }

        return map;
    }

    private Map<String, List<String>> fillMap(List<String> values) {
        Map<String, List<String>> map = new HashMap<>();

        values.stream()
                .filter(v -> NumberUtils.isNumber(v))
                .distinct()
                .forEach(s -> map.put(s, new ArrayList<String>()));

        return map;
    }

    private void print(List<String> values){
        values.forEach(v -> System.out.println(v));
    }

    private void print(Map<String, List<String>> groups){
        groups.forEach((key,vs) -> {
            //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            for (String value : vs){
                System.out.println(key +"\t"+ value);
            }
        });
    }

    /*private Optional parseCode(String text, String field){
        try{
            JsonReader jsonReader = new JsonReader(new StringReader(text));
            jsonReader.setLenient(true);

            JsonObject jsonObject = new JsonParser().parse(jsonReader).getAsJsonObject();
            return Optional.of(jsonObject.get(field).getAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }*/
}
