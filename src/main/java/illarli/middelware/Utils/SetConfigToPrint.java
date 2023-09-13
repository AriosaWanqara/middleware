package illarli.middelware.Utils;

import com.github.anastaciocintra.escpos.PrintModeStyle;
import illarli.middelware.Models.PrintTest;
import illarli.middelware.Models.Printers;

import java.util.HashMap;
import java.util.Map;

public class SetConfigToPrint {

    private Printers printer;
    private PrintTest printerTest;
    private PrintModeStyle.FontName fontName;
    private boolean isDoubleWight;
    private boolean isDoubleHeight;
    private Map<String, PrintModeStyle.FontName> fontNameMap;
    private String printerName;

    public SetConfigToPrint(Printers printer) {
        this.printer = printer;
        this.setFontNameMap();
    }

    public SetConfigToPrint(PrintTest printerTest) {
        this.printerTest = printerTest;
        this.setFontNameMap();
    }

    public PrintModeStyle.FontName getFontName() {
        return fontName;
    }

    public boolean isDoubleWight() {
        return isDoubleWight;
    }

    public boolean isDoubleHeight() {
        return isDoubleHeight;
    }

    public String getPrinterName() {
        return printerName;
    }

    public SetConfigToPrint() {
    }

    public SetConfigToPrint getSettingsByPrinter() {
        SetConfigToPrint setConfigToPrint = new SetConfigToPrint();
        if (this.getFontNameMap(this.printer.fontSize) == null) {
            setConfigToPrint.fontName = PrintModeStyle.FontName.Font_A_Default;
        } else {
            setConfigToPrint.fontName = this.getFontNameMap(this.printer.fontSize);
        }
        if (this.printer.fontSize.equals("DW")) {
            setConfigToPrint.isDoubleWight = true;
        }
        if (this.printer.fontSize.equals("DH")) {
            setConfigToPrint.isDoubleHeight = true;
        }
        setConfigToPrint.printerName = this.printer.printerName;
        return setConfigToPrint;
    }

    public SetConfigToPrint getSettingsByPrintTest() {
        SetConfigToPrint setConfigToPrint = new SetConfigToPrint();
        this.setFontNameMap();
        if (this.getFontNameMap(this.printerTest.fontSize) == null) {
            setConfigToPrint.fontName = PrintModeStyle.FontName.Font_A_Default;
        } else {
            setConfigToPrint.fontName = this.getFontNameMap(this.printerTest.fontSize);
        }
        if (this.printerTest.fontSize.equals("DW")) {
            setConfigToPrint.isDoubleWight = true;
        }
        if (this.printerTest.fontSize.equals("DH")) {
            setConfigToPrint.isDoubleHeight = true;
        }
        setConfigToPrint.printerName = this.printerTest.printerName;
        return setConfigToPrint;
    }

    private void setFontNameMap() {
        this.fontNameMap = new HashMap<>() {{
            put("A", PrintModeStyle.FontName.Font_A_Default);
            put("B", PrintModeStyle.FontName.Font_B);
            put("DW", PrintModeStyle.FontName.Font_B);
            put("DH", PrintModeStyle.FontName.Font_B);
        }};
    }

    private PrintModeStyle.FontName getFontNameMap(String type) {
        return this.fontNameMap.get(type);
    }
}
