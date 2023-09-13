package illarli.middelware.Resolvers;

import illarli.middelware.Infrastructure.Print.CoffeeMessageBuilder;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;

import java.util.Optional;

public class PrintBuilderDTO {

    private Long documentType;
    private CoffeeMessageBuilder message;
    private String PrinterName;
    private int CopyNumber;

    public PrintBuilderDTO(Long documentType, CoffeeMessageBuilder message) {
        this.documentType = documentType;
        this.message = message;
    }

    public void build(PrinterRepository printerRepository) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(this.documentType);
        if (ensurePrinterExist.isEmpty()) {
            throw new NullPointerException("La impresora no existe");
        }
        this.PrinterName = ensurePrinterExist.get().printerName;
        this.CopyNumber = ensurePrinterExist.get().copyNumber;
        this.message.build(ensurePrinterExist.get().fontSize);
    }

    public Long getDocumentType() {
        return documentType;
    }

    public int getCopyNumber() {
        return CopyNumber;
    }

    public CoffeeMessageBuilder getMessage() {
        return message;
    }

    public String getPrinterName() {
        return PrinterName;
    }
}
