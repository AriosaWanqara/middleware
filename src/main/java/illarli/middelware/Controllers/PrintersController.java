package illarli.middelware.Controllers;

import illarli.middelware.Infrastructure.Print.Cancelable;
import illarli.middelware.Infrastructure.Print.EscposCoffee;
import illarli.middelware.Models.PrintTest;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;
import illarli.middelware.Resolvers.*;
import illarli.middelware.Services.PrinterService;
import illarli.middelware.Utils.ListPrinters;
import illarli.middelware.Utils.SetConfigToPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController()
@RequestMapping("/printer")
public class PrintersController {
    @Autowired
    private PrinterService printerService;
    @Autowired
    private PrinterRepository printerRepository;

    @GetMapping("/list")
    public String[] index() {
        EscposCoffee escposCoffee = new EscposCoffee();
        return printerService.getAllPrinters(escposCoffee);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@Validated @RequestBody PrintTest printTest) {
        System.out.println(printTest.printerName);
        System.out.println(printTest.fontSize);
        if (printTest.validate()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EscposCoffee escposCoffee = new EscposCoffee();
        SetConfigToPrint startConfig = new SetConfigToPrint(printTest);
        SetConfigToPrint config = startConfig.getSettingsByPrintTest();
        if (printerService.PrintTest(escposCoffee, config)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<?> savePrinters(@Validated @RequestBody ListPrinters printers) {
        if (printers.printers.size() > 0) {
            printers.printers.forEach(printers1 -> {
                System.out.println(printers1.documentTypes.id);
                System.out.println(printers1.documentTypes.name);
            });
            printerService.savePrinters(printers.printers);
        } else {
            return new ResponseEntity<>("No hay impresoras para guardar", HttpStatus.LENGTH_REQUIRED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/persist/list")
    public ResponseEntity<List<Printers>> getPersistedPrinters() {
        return new ResponseEntity<>(printerService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (id.isEmpty()) {
            return new ResponseEntity<>("El id de la impresora no puede ser null", HttpStatus.BAD_REQUEST);
        }
        if (!this.printerService.deletePrinter(id)) {
            return new ResponseEntity<>("No existe impresora con ese id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Printers printers) {
        this.printerService.savePrinter(printers);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/print-header")
    public ResponseEntity<?> printHeader(@RequestBody PrintHeaderDTO printHeaderDTO) {
        printHeaderDTO.build(this.printerRepository);
        EscposCoffee escposCoffee = new EscposCoffee();
        escposCoffee.printHeader(printHeaderDTO.getCoffeeMessageHeader(), printHeaderDTO.getPrinterName());
        return new ResponseEntity<>(printHeaderDTO, HttpStatus.OK);
    }

    @PostMapping("/print-body")
    public ResponseEntity<?> printBody(@RequestBody PrintBodyDTO printBodyDTO) {
        EscposCoffee escposCoffee = new EscposCoffee();
        printBodyDTO.build(this.printerRepository);
        escposCoffee.printBody(printBodyDTO.getBodyMessages(), printBodyDTO.getPrinterName());
        return new ResponseEntity<>(printBodyDTO, HttpStatus.OK);
    }

    @PostMapping("/print-details")
    public ResponseEntity<?> printDetails(@RequestBody PrintDetailsDTO printDetailsDTO) {
        EscposCoffee escposCoffee = new EscposCoffee();
        printDetailsDTO.build(this.printerRepository);
        escposCoffee.printDetails(printDetailsDTO.getCoffeeMessageDetails(), printDetailsDTO.getPrintName());
        return new ResponseEntity<>(printDetailsDTO, HttpStatus.OK);
    }

    @PostMapping("/print-footer")
    public ResponseEntity<?> printFooter(@RequestBody PrintFooterDTO printFooterDTO) {
        EscposCoffee escposCoffee = new EscposCoffee();
        printFooterDTO.build(this.printerRepository);
        escposCoffee.printFooter(printFooterDTO.getCoffeeMessageFooter(), printFooterDTO.getPrinterName());
        return new ResponseEntity<>(printFooterDTO, HttpStatus.OK);
    }

    @PostMapping("/print-full")
    public ResponseEntity<?> printFullDocument(@RequestBody PrintBuilderDTO printBuilderDTO) {
        EscposCoffee escposCoffee = new EscposCoffee();
        printBuilderDTO.build(this.printerRepository);
        for (int i = 0; i < printBuilderDTO.getCopyNumber(); i++) {
            escposCoffee.print(printBuilderDTO.getMessage(), printBuilderDTO.getPrinterName());
        }
        return new ResponseEntity<>(printBuilderDTO, HttpStatus.OK);
    }

    @PostMapping("/print-cut")
    public ResponseEntity<?> cut(Long id) {
        CutDTO cutDTO = new CutDTO(id);
        cutDTO.build(this.printerRepository);
        EscposCoffee escposCoffee = new EscposCoffee();
        escposCoffee.cut(cutDTO.getPrinterName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/open-cash-drawer")
    public ResponseEntity<?> openCashDrawer(Long id) {
        Optional<Printers> ensurePrinterExist = this.printerRepository.findByDocumentTypes_Id(id);
        if (ensurePrinterExist.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EscposCoffee escposCoffee = new EscposCoffee();
        escposCoffee.OpenCashDrawerFromPrinter(ensurePrinterExist.get().printerName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check-printers")
    public List<String> checkSOPrinters() {
        List<Printers> printers = this.printerService.getAll();
        EscposCoffee escposCoffee = new EscposCoffee();
        String[] SOPrinters = printerService.getAllPrinters(escposCoffee);
        Arrays.stream(SOPrinters).toList().forEach(System.out::println);
        List<String> deletePrinters = new ArrayList<>(Collections.emptyList());
        for (Printers p : printers) {
            if (!Arrays.stream(SOPrinters).toList().contains(p.printerName)) {
                deletePrinters.add(p.printerName);
            }
        }
        return deletePrinters;
    }

    @GetMapping("/clear-spooler")
    public void clearSpooler() {
        try {
            Cancelable.clearSpooler();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

