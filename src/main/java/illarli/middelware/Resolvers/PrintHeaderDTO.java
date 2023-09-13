package illarli.middelware.Resolvers;

import illarli.middelware.Infrastructure.Print.CoffeeMessageHeader;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;

import java.util.Optional;

public class PrintHeaderDTO {
    private Long documentType;
    private CoffeeMessageHeader coffeeMessageHeader;
    private String printerName;

    public PrintHeaderDTO(Long documentType, CoffeeMessageHeader coffeeMessageHeader) {
        this.documentType = documentType;
        this.coffeeMessageHeader = coffeeMessageHeader;
    }

    public void build(PrinterRepository printerRepository) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(this.documentType);
        if (ensurePrinterExist.isEmpty()) {
            throw new NullPointerException("La impresora no existe");
        }
        this.coffeeMessageHeader.build(ensurePrinterExist.get().fontSize);
        this.printerName = ensurePrinterExist.get().printerName;
    }

    public Long getDocumentType() {
        return documentType;
    }

    public CoffeeMessageHeader getCoffeeMessageHeader() {
        return coffeeMessageHeader;
    }

    public String getPrinterName() {
        return printerName;
    }
}
