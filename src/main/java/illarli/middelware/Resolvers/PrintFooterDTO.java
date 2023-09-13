package illarli.middelware.Resolvers;

import illarli.middelware.Infrastructure.Print.CoffeeMessageFooter;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;

import java.util.Optional;

public class PrintFooterDTO {

    private Long documentType;
    private CoffeeMessageFooter coffeeMessageFooter;
    private String PrinterName;

    public PrintFooterDTO(Long documentType, CoffeeMessageFooter coffeeMessageFooter) {
        this.documentType = documentType;
        this.coffeeMessageFooter = coffeeMessageFooter;
    }

    public void build(PrinterRepository printerRepository) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(this.documentType);
        if (ensurePrinterExist.isEmpty()) {
            throw new NullPointerException("La impresora no existe");
        }
        this.PrinterName = ensurePrinterExist.get().printerName;
        this.coffeeMessageFooter.build(ensurePrinterExist.get().fontSize);
    }

    public Long getDocumentType() {
        return documentType;
    }

    public CoffeeMessageFooter getCoffeeMessageFooter() {
        return coffeeMessageFooter;
    }

    public String getPrinterName() {
        return PrinterName;
    }
}
