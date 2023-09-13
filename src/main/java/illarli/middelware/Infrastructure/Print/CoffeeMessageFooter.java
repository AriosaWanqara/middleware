package illarli.middelware.Infrastructure.Print;

import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMessageFooter {

    @NotNull(message = "Footer messages is required")
    private Map<String, List<String>> footerMessages;
    @NotNull(message = "Footer font size is required")
    private String fontName;
    @NotNull(message = "Footer align is required")
    private String FontJustify;
    @Null
    private Style FooterStyle;

    public CoffeeMessageFooter(Map<String, List<String>> footerMessages) {
        this.footerMessages = footerMessages;
        this.FontJustify = "L";
        this.FooterStyle = new Style();
    }

    public CoffeeMessageFooter() {
        this.FooterStyle = new Style();
    }

    public void build(String fontName) {
        this.fontName = fontName;
        this.FooterStyle.setJustification(setAlign("L"))
                .setFontName(setCoffeeFontName(fontName));
    }

    private Style.FontName setCoffeeFontName(String fontName) {
        Map<String, Style.FontName> fontNameMap = new HashMap<>() {{
            put("A", Style.FontName.Font_A_Default);
            put("B", Style.FontName.Font_B);
            put("DW", Style.FontName.Font_B);
            put("DH", Style.FontName.Font_B);
        }};
        if (fontNameMap.containsKey(fontName)) {
            return fontNameMap.get(fontName);
        }
        return Style.FontName.Font_B;
    }

    private EscPosConst.Justification setAlign(String fontAlign) {
        Map<String, EscPosConst.Justification> allAvailableFontAlign = new HashMap<>() {{
            put("L", EscPosConst.Justification.Left_Default);
            put("R", EscPosConst.Justification.Right);
            put("C", EscPosConst.Justification.Center);
        }};
        if (allAvailableFontAlign.containsKey(fontAlign)) {
            return allAvailableFontAlign.get(fontAlign);
        }
        return EscPosConst.Justification.Left_Default;
    }

    public Map<String, List<String>> getFooterMessages() {
        return footerMessages;
    }

    public String getFontName() {
        return fontName;
    }

    public String getFontJustify() {
        return FontJustify;
    }

    public Style getFooterStyle() {
        return FooterStyle;
    }
}
