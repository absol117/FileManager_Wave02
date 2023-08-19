package com.scai.gestorefile.gestorefile_w02.controller;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import com.scai.gestorefile.gestorefile_w02.service.FileService;
import com.scai.gestorefile.gestorefile_w02.serviceDatabase.DocumentServiceDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentServiceDatabase documentServiceDatabase;

    @Autowired
    public DocumentController(DocumentServiceDatabase documentServiceDatabase) {
        this.documentServiceDatabase = documentServiceDatabase;
    }

    @GetMapping("/getAll")
    public List<Document> getAllDocuments() {
        return documentServiceDatabase.getAllDocuments();
    }





    @PostMapping("/add")
    public Document saveDocument(@RequestBody Document document) {
        return documentServiceDatabase.createDocument(document.getTag(), document.getPath());
    }


    @GetMapping("/search/{id}")
    public Optional<Document> searchDocument(@PathVariable int id) {
        return documentServiceDatabase.getDocumentById(id);
    }



    @DeleteMapping("/delete/{id}")
    public boolean deleteDocument(@PathVariable int id) {
        return documentServiceDatabase.deleteDocumentByIdTrueLord22(id);
    }





    @PutMapping("/fix/{id}")
    public Document fixDocument(@PathVariable int id, @RequestBody Document document) {
        return documentServiceDatabase.updateWithPath(id, document.getTag(), document.getPath());
    }



}
