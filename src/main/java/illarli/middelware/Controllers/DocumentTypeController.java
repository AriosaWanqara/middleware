package illarli.middelware.Controllers;

import illarli.middelware.Models.DocumentType;
import illarli.middelware.Services.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentTypeController {


    @Autowired
    DocumentTypeService documentTypeService;

    @GetMapping("/document-type/list")
    public List<DocumentType> index() {
        return documentTypeService.getAll();
    }
}
