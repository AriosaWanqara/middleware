package illarli.middelware.Resolvers;

import illarli.middelware.Infrastructure.Print.CoffeeMessageDetails;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;

import java.util.Optional;

public class PrintDetailsDTO {

    private Long documentType;
    private CoffeeMessageDetails coffeeMessageDetails;
    private String printerName;

    public PrintDetailsDTO(Long documentType, CoffeeMessageDetails coffeeMessageDetails) {
        this.documentType = documentType;
        this.coffeeMessageDetails = coffeeMessageDetails;
    }

    public void build(PrinterRepository printerRepository) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(this.documentType);
        if (ensurePrinterExist.isEmpty()) {
            throw new NullPointerException("La impresora no existe");
        }
        this.printerName = ensurePrinterExist.get().printerName;
        this.coffeeMessageDetails.build(ensurePrinterExist.get().fontSize);
    }

    public Long getDocumentType() {
        return documentType;
    }

    public CoffeeMessageDetails getCoffeeMessageDetails() {
        return coffeeMessageDetails;
    }

    public String getPrintName() {
        return printerName;
    }
}
