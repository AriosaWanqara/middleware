package illarli.middelware.Models;

import illarli.middelware.Infrastructure.Print.*;
import illarli.middelware.Utils.SetConfigToPrint;

public interface PrinterLibraryRepository {
    void print(CoffeeMessageBuilder coffeeMessageBuilder, String printerName);

    void printHeader(CoffeeMessageHeader coffeeMessageHeader, String printerName);

    void printBody(CoffeeMessageBody coffeeMessageBody, String printerName);

    void printDetails(CoffeeMessageDetails coffeeMessageDetails, String printerName);

    void printFooter(CoffeeMessageFooter coffeeMessageFooter, String printerName);

    void cut(String printerName);

    boolean printTest(SetConfigToPrint config);

    String[] getPrinterList();

    void OpenCashDrawerFromPrinter(String printerName);
}
