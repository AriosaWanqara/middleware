package illarli.middelware.Controllers;

import illarli.middelware.Models.TagPrinter;
import illarli.middelware.Services.TagPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tag-printers")
public class TagPrintersController {
    @Autowired
    private TagPrinterService tagPrinterService;

    @GetMapping("/list")
    public ResponseEntity<List<TagPrinter>> list() {
        return new ResponseEntity<>(this.tagPrinterService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody TagPrinter tagPrinter) {
        this.tagPrinterService.save(tagPrinter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody TagPrinter tagPrinter) {
        if (this.tagPrinterService.update(tagPrinter)) {
            return new ResponseEntity<>(tagPrinter, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Los datos no son correctos", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(String id) {
        if (id.isEmpty()) {
            return new ResponseEntity<>("El id no pertenece a ninguna impresora de Tickets", HttpStatus.NOT_ACCEPTABLE);
        }
        if (this.tagPrinterService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("El id no pertenece a ninguna impresora de Tickets", HttpStatus.CONFLICT);
        }
    }
}
