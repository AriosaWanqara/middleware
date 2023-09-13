package illarli.middelware.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Printers {
    @Id
    public String id;
    public String printerName;
    @OneToOne
    @JoinColumn(name = "documentTypes", referencedColumnName = "id")
    public DocumentType documentTypes;
    public String fontSize = "A";
    public String linedType = "1";
    public int copyNumber;
    public int characterNumber;


    public static String[] getPrinters(PrinterLibraryRepository printerLibraryRepository) {
        return printerLibraryRepository.getPrinterList();
    }

    public Printers() {
    }
}
