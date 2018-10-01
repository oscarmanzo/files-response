package com.fandango.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.util.stream.Collectors.*;

public class ParseFile {

    private static final String ROOT = "C:\\Users\\oscar\\Documents\\Proyectos\\Globant\\Fandango\\Master\\pos-service\\src\\wiremock\\resources\\com\\fandango\\commerce\\posresponse\\__files\\";

    private static final String LOG_FILE = "C:\\Users\\oscar\\Documents\\Proyectos\\Globant\\Fandango\\Master\\pos-service\\logs\\posservice-app-access.log";

    private static final String FIND_PERFORMANCE = "posPerformanceCode";
    private static final String FIND_FILE_NAME = "bodyFileName";

    private static final String CODE_XXX = "XXX";
    private static final String CODE_DEFAULT = "0000";
    private static final String DELIMITER = "####################################################";

    public static final void main(String[] args) {
        try {
            ParseFile app = new ParseFile();
            List<FileVO> files = app.run();

            //app.renameFile(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<FileVO> run() throws Exception {
        File file = new File(LOG_FILE);

        List<String> blocks = readFileDelimeter(file);
        List<TestVO> tests = parseBlocks(blocks);

        tests = tests.stream()
                .filter(vo -> vo.files.size()<50)
                .collect(toList());

        //tests.forEach(System.out::println);
        //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

        List<FileVO> files = tests.stream()
                .filter(vo -> vo.files.size()<50)
                .flatMap(vo -> listFiles(vo).stream())
                .collect(toList());

        //files.forEach(System.out::println);
        //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

        files = files.stream().distinct().collect(toList());
        files.forEach(System.out::println);

        return files;
    }

    private List<String> readFileDelimeter(File logFile) throws Exception {
        List<String> chunks = new ArrayList<>();

        Scanner sc = new Scanner(logFile);
        sc.useDelimiter(DELIMITER);

        while (sc.hasNext()){
            chunks.add(sc.next());
        }

        return chunks;
    }

    private List<TestVO> parseBlocks(List<String> blocks) {
        return blocks.stream()
                .map( b -> parseBlock(b))
                .collect(toList());
    }

    private TestVO parseBlock(String block) {
        List<String> codes = new ArrayList<>();
        List<String> files = new ArrayList<>();

        Scanner sc = new Scanner(block);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            Optional<String> performanceOpt = findText(line, FIND_PERFORMANCE);
            if (performanceOpt.isPresent())
                codes.add(performanceOpt.get());

            Optional<String> filenameOpt = findText(line, FIND_FILE_NAME);
            if (filenameOpt.isPresent())
                files.add(filenameOpt.get());
        }

        String code = getRightCode(codes);
        return new TestVO(code, files);
    }

    private String getRightCode(List<String> codes){
        if (codes==null || codes.isEmpty()) return CODE_DEFAULT;

        if (codes.size()==1) return codes.get(0);

        return codes.stream()
                .filter(c -> !c.trim().isEmpty())
                .reduce((a, b) -> b).orElse(CODE_DEFAULT);
    }

    private Optional<String> findText(String textline, String toFind) {
        int index = textline.indexOf(toFind);

        if (index >= 0) {
            //System.out.println("------------>"+ textline);
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
                value = CODE_DEFAULT;

            return Optional.of(value);
        } catch (Exception e) {
            System.out.print("TO_PARSE:" + text);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<FileVO> listFiles(TestVO vo){
        List<FileVO> files = new ArrayList<>();

        for (String filename : vo.files){
            files.add(new FileVO(vo.performanceCode, filename, replace(filename, vo.performanceCode)));
        }

        return files;
    }

    private String replace(String filename, String pc){

        int last = filename.lastIndexOf("/");
        if (last>0){
            filename = filename.substring(last + 1);
        }

        if (filename.contains(CODE_XXX)){
            return filename.replace(CODE_XXX, pc);
        }

        if (!filename.contains(pc)){
            return replaceCP(filename, pc);
        }

        return filename;
    }

    private String replaceCP(String file, String pc){
        int one = file.indexOf("_");
        int two = file.indexOf("_", one + 1);
        int three = file.indexOf("_", two + 1);

        if (three<0) three = file.indexOf(".");

        String a = file.substring(0, two + 1);
        String b = file.substring(three);
        String c = a + pc + b;

        return c;
    }

    private void renameFile(List<FileVO> vos){
        vos.forEach(v -> renameFile(v));
    }

    private void renameFile(FileVO vo){
        File file = new File(ROOT + vo.file);

        System.out.println(file.getPath());
        System.out.println(file.exists());
        System.out.println(file.getParent());

        File newfile = new File(file.getParent() +"\\"+ vo.newName);
        System.out.println(newfile.getPath());
        file.renameTo(newfile);
    }

    class TestVO {
        String performanceCode;
        List<String> files;

        private TestVO(String performanceCode, List<String> files){
            this.performanceCode = performanceCode;
            this.files = files;
        }

        @Override
        public String toString() {
            return performanceCode +":\n"+
                    files.stream()
                            .collect(toSet())
                            .stream()
                            .collect(joining(",'\n"));
        }
    }

    class FileVO {
        String code;
        String file;
        String newName;

        private FileVO(String code, String file, String newName){
            this.code = code;
            this.file = file;
            this.newName = newName;
        }

        public boolean isChanged() {
            return file==null || newName==null? false : !file.endsWith(newName);
        }

        @Override
        public String toString() {
            return this.code +"\t"+ this.file +"\t"+ this.newName +"\t"+ isChanged();
        }

        @Override
        public boolean equals(Object obj) {
            FileVO other = (FileVO)obj;
            return (this.code + this.file).equals((other.code + other.file));
        }

        @Override
        public int hashCode() {
            return (this.code + this.file).hashCode();
        }

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
