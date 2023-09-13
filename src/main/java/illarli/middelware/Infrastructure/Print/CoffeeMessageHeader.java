package illarli.middelware.Infrastructure.Print;

import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMessageHeader {
    @NotNull(message = "Header title is required")
    private List<String> title;
    @NotNull(message = "Header title size is required")
    private String titleSize;
    @NotNull(message = "Footer title align is required")
    private String titleAlign;
    @NotNull(message = "Header subtitle is required")
    private List<String> subtitle;
    @NotNull(message = "Header subtitle align is required")
    private String subtitleAlign;
    @Null
    private Style headerTitleCoffeeStyle;
    @Null
    private Style headerSubtitleCoffeeStyle;

    public Style getHeaderTitleCoffeeStyle() {
        return headerTitleCoffeeStyle;
    }

    public Style getHeaderSubtitleCoffeeStyle() {
        return headerSubtitleCoffeeStyle;
    }

    public CoffeeMessageHeader(List<String> title, String titleAlign, List<String> subtitle, String titleSize, String subtitleAlign) {
        this.title = title;
        this.titleAlign = titleAlign;
        this.subtitle = subtitle;
        this.headerTitleCoffeeStyle = new Style();
        this.headerSubtitleCoffeeStyle = new Style();
        this.titleSize = titleSize;
        this.subtitleAlign = subtitleAlign;
        this.headerTitleCoffeeStyle.setJustification(getCoffeeAlign(titleAlign))
                .setFontSize(getCoffeeFontSize(this.titleSize), getCoffeeFontSize(this.titleSize));
        this.headerSubtitleCoffeeStyle.setJustification(getCoffeeAlign(subtitleAlign));
    }

    public void build(String fontName) {
        Map<String, Style.FontName> fontNameMap = new HashMap<>() {{
            put("A", Style.FontName.Font_A_Default);
            put("B", Style.FontName.Font_B);
            put("DW", Style.FontName.Font_B);
            put("DH", Style.FontName.Font_B);
        }};
        if (fontNameMap.containsKey(fontName)) {
            this.headerSubtitleCoffeeStyle.setFontName(fontNameMap.get(fontName));
        }
        this.headerSubtitleCoffeeStyle.setFontName(Style.FontName.Font_B);
    }

    public List<String> getTitle() {
        return title;
    }

    public List<String> getSubtitle() {
        return subtitle;
    }

    public String getTitleSize() {
        return titleSize;
    }

    public String getTitleAlign() {
        return titleAlign;
    }

    public String getSubtitleAlign() {
        return subtitleAlign;
    }

    private Style.FontSize getCoffeeFontSize(String fontSize) {
        Map<String, Style.FontSize> allAvailableFontSize = new HashMap<>() {{
            put("1", Style.FontSize._1);
            put("2", Style.FontSize._2);
            put("3", Style.FontSize._3);
        }};
        if (allAvailableFontSize.containsKey(fontSize)) {
            return allAvailableFontSize.get(fontSize);
        }
        return Style.FontSize._2;
    }

    private EscPosConst.Justification getCoffeeAlign(String fontAlign) {
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
}
