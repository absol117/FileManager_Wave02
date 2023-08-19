package com.scai.gestorefile.gestorefile_w02.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

import java.io.File;
import java.nio.file.Path;
@Entity
public class Document {

    @GeneratedValue
    @Id
    private int id;
    private String tag;
    private String path;

    // elimina solo il contenuto, non la proprietà
    @Transient
    private File file;



    // vedere bene i costruttori
    // problema con il content (path = file)
    public Document(int id, String tag, String path) {
        this.id = id;
        this.tag = tag;
        this.path = path;
        this.file = new File(path);

    }

    public Document(String path) {
        this.path = path;
        this.file = new File(path);

    }

    public Document(String tag, String path) {
        this.tag = tag;
        this.path = path;
        this.file = new File(path);

    }

    public Document() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
