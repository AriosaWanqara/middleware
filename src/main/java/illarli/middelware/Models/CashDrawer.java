package illarli.middelware.Models;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CashDrawer {
    @Id
    @NotBlank(message = "El id no puede ser null")
    private String id;
    @Nullable
    private String port;
    @Nullable
    private String printer;
    @NotNull(message = "Debe seleccionar entre usb o Rj11")
    private boolean isUsb;

    public CashDrawer(String id, @Nullable String port, @Nullable String printer, boolean isUsb) {
        this.id = id;
        this.port = port;
        this.printer = printer;
        this.isUsb = isUsb;
    }

    protected CashDrawer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    public String getPort() {
        return port;
    }

    public void setPort(@Nullable String port) {
        this.port = port;
    }

    @Nullable
    public String getPrinter() {
        return printer;
    }

    public void setPrinter(@Nullable String printer) {
        this.printer = printer;
    }

    public boolean isUsb() {
        return isUsb;
    }

    public void setUsb(boolean usb) {
        isUsb = usb;
    }
}
