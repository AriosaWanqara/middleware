package illarli.middelware.Resolvers;

import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;

import java.util.Optional;

public class CutDTO {
    private Long documentType;

    private String printerName;

    public CutDTO(Long documentType) {
        this.documentType = documentType;
    }

    public void build(PrinterRepository printerRepository) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(this.documentType);
        if (ensurePrinterExist.isEmpty()) {
            throw new NullPointerException("La impresora no existe");
        }
        this.printerName = ensurePrinterExist.get().printerName;
    }

    public String getPrinterName() {
        return printerName;
    }
}
