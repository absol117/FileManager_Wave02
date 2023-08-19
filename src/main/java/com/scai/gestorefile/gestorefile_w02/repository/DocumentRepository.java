package com.scai.gestorefile.gestorefile_w02.repository;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
