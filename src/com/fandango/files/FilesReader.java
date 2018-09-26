package com.fandango.files;

import java.io.File;

public class FilesReader {

    private static final String ROOT_PATH = "C:\\Users\\oscar\\Documents\\Proyectos\\Globant\\Fandango\\Code\\pos-service\\src\\wiremock\\resources\\com\\fandango\\commerce";

    public static void main(String[] args){
        String path = "C:\\Users\\oscar\\Documents\\Proyectos\\Globant\\Fandango\\Code\\pos-service\\src\\wiremock\\resources\\com\\fandango\\commerce";
        FilesReader app = new FilesReader();
        app.read(new File(path));
    }

    public void read(File file){
        if (!file.exists()) return;

        if (file.isDirectory()){
            System.out.println();

            for (File f : file.listFiles()){
                read(f);
            }
        } else {
            print(file);
        }
    }

    private void print(File file){
        try {
            String name = file.getName();
            String path = file.getPath();
            path = path.replace(ROOT_PATH, "").replace(name, "");

            System.out.println(path +"\t"+ name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
