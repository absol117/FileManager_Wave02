package com.scai.gestorefile.gestorefile_w02.serviceDatabase;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import com.scai.gestorefile.gestorefile_w02.repository.DocumentRepository;
import com.scai.gestorefile.gestorefile_w02.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceDatabase {

    private final DocumentRepository documentRepository;
    private final FileService fileService;

    @Autowired
    public DocumentServiceDatabase(DocumentRepository documentRepository, FileService fileService) {
        this.documentRepository = documentRepository;
        this.fileService = fileService;

    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(int id) {
        return documentRepository.findById(id);
    }

    public boolean isPresentDoc(int id) {
        return documentRepository.findById(id).isPresent();
    }


    public Document createDocument(String tag, String path) {
        Document document = fileService.addDocument2(tag, path);
        return documentRepository.save(document);
    }

    public boolean deleteDocumentById(int id) {
        if (getDocumentById(id).isPresent()) {
            Document document = getDocumentById(id).get();
            this.fileService.delete(document);
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteDocumentByIdTrueLord(int id) {
        Optional<Document> documentById = getDocumentById(id);
        documentById.ifPresent(fileService::delete);
        if(getDocumentById(id).isPresent()) {
            documentRepository.deleteById(getDocumentById(id).get().getId());
        }
        return false;
    }


    public boolean deleteDocumentByIdTrueLord22(int id) {
        Optional<Document> documentById = getDocumentById(id);
        String path = documentById.get().getPath();
        fileService.delete(path);
        documentRepository.deleteById(id);
        return true;
    }




    public Document update(int id, String tag, String context) {
        Optional<Document> documentById = getDocumentById(id);
        Document document = documentById.get();
        document.setTag(tag);
        fileService.write(document,context);
        return documentRepository.save(document);

    }

    public Document updateWithPath(int id, String tag, String path) {
        if (getDocumentById(id).isPresent()) {
            try {
                Document document = getDocumentById(id).get();
                String originPath = document.getPath();
                document.setPath(path);
                document.setTag(tag);
                Files.move(Path.of(originPath), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
                documentRepository.save(document);
                return document;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    public boolean fixContent(int id, String content) {
        if(getDocumentById(id).isPresent()) {
            Document document = getDocumentById(id).get();
            fileService.write(document, content);
            return true;
        }
        return false;
    }

    public String read(int id) {
        if (getDocumentById(id).isPresent()) {
            return fileService.read(getDocumentById(id).get());
        }
        return null;
    }


}
