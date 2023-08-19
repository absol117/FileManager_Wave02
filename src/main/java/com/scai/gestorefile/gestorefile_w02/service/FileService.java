package com.scai.gestorefile.gestorefile_w02.service;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {


    public String read(Document document) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(document.getFile().getPath()));
            StringBuilder stringBuilder = new StringBuilder();
            String ris = "";
            while ((ris = bufferedReader.readLine()) != null) {
                stringBuilder.append(ris).append("\n");
            }
            bufferedReader.close();
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void write(Document document, String content) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(document.getFile().getPath(), true));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public Document addDocument(int id, String tag, String path) {
        Document document = new Document(id,tag,path);
        try {
            if(document.getFile().exists()) {
               document.getFile().delete();
            }
            if (!document.getFile().exists()) {
                document.getFile().createNewFile();
                return document;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Document addDocument(String tag, String path) {
        Document document = new Document(tag,path);
        try {
            if(document.getFile().exists()) {
                document.getFile().delete();
            }
            if (!document.getFile().exists()) {
                document.getFile().createNewFile();
                return document;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    //mettere a posto dopo
    public Document addDocument2(String tag, String path) {
        Document document = new Document(tag,path);
        try {
            if(document.getFile().exists()) {
                document.getFile().delete();
            }
            if(document.getFile().createNewFile()) {
                System.out.println("file creato");
                return document;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    public boolean delete(String path) {
        if (Files.exists(Path.of(path))) {
            try {
                Files.delete(Path.of(path));
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean delete(Document document) {
        if (document.getFile().exists()) {
            return document.getFile().delete();

        }
        return false;
    }




    public boolean move(Document document, String destination) {
        if (document.getFile().exists()) {
            try {
                Path path = Paths.get(destination);
                Files.move(document.getFile().toPath(), path, StandardCopyOption.REPLACE_EXISTING);
                delete(document);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }





}
