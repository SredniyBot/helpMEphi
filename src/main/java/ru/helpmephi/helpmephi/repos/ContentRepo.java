package ru.helpmephi.helpmephi.repos;

import org.springframework.data.repository.CrudRepository;
import ru.helpmephi.helpmephi.entity.doc.Content;

public interface ContentRepo extends CrudRepository<Content,Long> {
}
