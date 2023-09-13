package illarli.middelware.Models;

public class PrintTest {

    public String printerName;
    public String fontSize;

    public PrintTest(String printerName, String fontName) {
        this.printerName = printerName;
        fontSize = fontName;
    }

    public PrintTest() {

    }

    public boolean validate() {
        return this.fontSize == null || this.printerName == null;
    }
}
