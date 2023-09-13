package illarli.middelware.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class TagPrinter {
    @Id
    @NotBlank(message = "El id de la impreso es null")
    private String id;
    @NotBlank(message = "El nombre de la impreso es null")
    private String printerName;

    public TagPrinter(String id, String printerName) {
        this.id = id;
        this.printerName = printerName;
    }

    protected TagPrinter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }
}
