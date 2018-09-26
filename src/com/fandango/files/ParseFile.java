package com.fandango.files;

import java.io.File;
import java.util.*;

public class ParseFile {

    private static final String LOG_FILE = "C:\\Users\\oscar.manzo\\Globant\\Fandango\\Services\\TO_REBASE\\pos-service\\logs\\posservice-app-access.log";

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

        Map<List<String>, List<String>> groups = groupByPerformance(values);
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
            //System.out.println("------------>"+ textline);
            String aux = FIND_PERFORMANCE.equals(toFind) ? "\" : \"" : "\" : \"";
            return parse(textline.substring(index), toFind, aux);
        }

        return Optional.empty();
    }

    private Optional parse(String text, String toFind, String separator) {
        try {
            int indexAux = text.indexOf(separator);
            String subaux = text.substring(indexAux + separator.length());
            String value = subaux.substring(0, subaux.indexOf("\""));
            return Optional.of(toFind + " - " + value);
        } catch (Exception e) {
            System.out.print("TO_PARSE:" + text);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Map<List<String>, List<String>> groupByPerformance(List<String> values){
        Map<List<String>, List<String>> groups = new HashMap<>();

        List<String> codes = new ArrayList<>();
        List<String> files = new ArrayList<>();

        for (String v : values){
            if (v.contains(FIND_FILE_NAME)) {
                files.add(v);
            } else if (v.contains(FIND_PERFORMANCE)){
                codes.add(v);

                groups.put(codes, files);

                codes = new ArrayList<>();
                files = new ArrayList<>();
            }
        }

        return groups;
    }

    private void print(List<String> values){
        values.forEach(v -> System.out.println(v));
    }

    private void print(Map<List<String>, List<String>> groups){
        groups.forEach((ks,vs) -> {
            //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            for (String key : ks){
                for (String value : vs){
                    System.out.println(key +"\t"+ value);
                }
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
