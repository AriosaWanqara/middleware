package illarli.middelware.Resolvers;

import illarli.middelware.Models.CashDrawer;
import illarli.middelware.Models.Printers;
import illarli.middelware.Repositories.PrinterRepository;

public class CashDrawerDTO {
    public String id;
    public String port;
    public String printer;
    public boolean isUsb;

    public CashDrawerDTO(String id, String port, String printer, boolean isUsb) {
        this.id = id;
        this.port = port;
        this.printer = printer;
        this.isUsb = isUsb;
    }

    public CashDrawerDTO() {
    }

    public CashDrawer getCashDrawerFromCashDrawerDTO() {
        if (this.printer == null) {
            return new CashDrawer(this.id, this.port, null, this.isUsb);
        } else {
            return new CashDrawer(this.id, this.port, this.printer, this.isUsb);
        }
    }
}
