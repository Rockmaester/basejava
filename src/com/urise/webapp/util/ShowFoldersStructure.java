package com.urise.webapp.util;

import java.io.File;
import java.util.Objects;

public class ShowFoldersStructure {
    public static void main(String[] args) {
        String filePath = "./";
        String tab = "";
        File dir = new File(filePath);
        ShowFoldersStructure.readFolders(dir, tab);
    }

    private static void readFolders(File entity, String tab){
        tab += "|  ";
        for(File file: Objects.requireNonNull(entity.listFiles())){
            System.out.println(tab + file.getName());
            if(file.isDirectory() && !file.getName().startsWith(".git")){
                readFolders(file, tab);
            }
        }
    }
}