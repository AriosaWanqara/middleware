package illarli.middelware.Services;

import illarli.middelware.Models.PrinterLibraryRepository;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import illarli.middelware.Utils.SetConfigToPrint;

import java.util.List;
import java.util.Optional;

@Service
public class PrinterService {

    @Autowired
    PrinterRepository printerRepository;

    public String[] getAllPrinters(PrinterLibraryRepository printerLibraryRepository) {
        return Printers.getPrinters(printerLibraryRepository);
    }

    public boolean PrintTest(PrinterLibraryRepository printerLibraryRepository, SetConfigToPrint PrinterName) {
        return printerLibraryRepository.printTest(PrinterName);
    }

    public void savePrinters(List<Printers> printersList) {
        printersList.forEach(printers -> {
            Optional<Printers> printer = printerRepository.findByDocumentTypes_Id(printers.documentTypes.id);
            if (printer.isPresent()) {
                printers.id = printer.get().id;
                this.printerRepository.save(printers);
            } else {
                this.printerRepository.save(printers);
            }
        });
    }

    public void savePrinter(Printers printer) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(printer.documentTypes.id);
        if (ensurePrinterExist.isPresent()) {
            this.printerRepository.deleteById(printer.id);
            printer.id = ensurePrinterExist.get().id;
            this.printerRepository.save(printer);
        } else {
            this.printerRepository.save(printer);
        }
    }

    public boolean deletePrinter(String id) {
        Optional<Printers> printer = this.printerRepository.findById(id);
        if (printer.isPresent()) {
            this.printerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Printers> getAll() {
        return this.printerRepository.findAll();
    }
}
