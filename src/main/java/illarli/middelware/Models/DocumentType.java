package illarli.middelware.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DocumentType {
    @Id
    public Long id;
    public String name;

    public DocumentType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DocumentType() {
    }
}
