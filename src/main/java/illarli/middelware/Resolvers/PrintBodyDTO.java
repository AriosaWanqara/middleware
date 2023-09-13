package illarli.middelware.Resolvers;

import illarli.middelware.Infrastructure.Print.CoffeeMessageBody;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PrintBodyDTO {
    private Long documentType;
    private CoffeeMessageBody bodyMessages;
    private String printerName;

    public PrintBodyDTO(Long documentType, CoffeeMessageBody bodyMessages) {
        this.documentType = documentType;
        this.bodyMessages = bodyMessages;
    }

    public void build(PrinterRepository printerRepository) {
        Optional<Printers> ensurePrinterExist = printerRepository.findByDocumentTypes_Id(this.documentType);
        if (ensurePrinterExist.isEmpty()) {
            throw new NullPointerException("La impresora no existe");
        }
        this.printerName = ensurePrinterExist.get().printerName;
        this.bodyMessages.build(this.printerName);
    }

    public Long getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Long documentType) {
        this.documentType = documentType;
    }

    public CoffeeMessageBody getBodyMessages() {
        return bodyMessages;
    }

    public String getPrinterName() {
        return printerName;
    }
}
