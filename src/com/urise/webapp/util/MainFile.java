package com.urise.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        System.out.println(dir.list());

        for(String name: dir.list()){
            System.out.println(name);
        }
        // Т.к. dir.list() может бросить NPE, оборачиваем в Objects.requireNonNull:
        for(String name: Objects.requireNonNull(dir.list())){
            System.out.println(name);
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fis != null){
                try {

                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            // Даже в блоке Finally возможен выброс исключения. Например, файл может быть заблокирован и не может закрыться.
            // Чтобы уйти от данной "трехэтажной" логики, можно использовать сторонние библиотеки, Guava, Apache Commons,
            // где есть более лаконичные решения по закрытию ресурсов. Либо использовать try-with-resources для тех ресурсов,
            // которые наследуют интерфейс AutoClosable.
        }

        try (FileInputStream fis2 = new FileInputStream(filePath)) {
            System.out.println(fis2.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
