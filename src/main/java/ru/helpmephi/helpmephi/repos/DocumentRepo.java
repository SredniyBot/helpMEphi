package ru.helpmephi.helpmephi.repos;

import org.springframework.data.repository.CrudRepository;
import ru.helpmephi.helpmephi.entity.doc.Document;

public interface DocumentRepo extends CrudRepository<Document, Long> {
}
