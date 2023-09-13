package illarli.middelware.Services;

import illarli.middelware.Models.DocumentType;
import illarli.middelware.Repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    public List<DocumentType> getAll() {
        return this.documentTypeRepository.findAll();
    }
}
