package illarli.middelware.Infrastructure.Print;

import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMessageDetails {

    @NotNull(message = "Detail messages is required")
    private List<String> detailsMessage;

    @NotNull(message = "Detail font size is required")
    private String fontName;
    @Null
    private Style DetailsStyle;

    public CoffeeMessageDetails(List<String> detailsMessage) {
        this.detailsMessage = detailsMessage;
        this.DetailsStyle = new Style();
    }

    public CoffeeMessageDetails() {
        this.DetailsStyle = new Style();
    }

    public void build(String fontName) {
        this.fontName = fontName;
        this.DetailsStyle.setFontName(setFontStyle(fontName))
                .setJustification(EscPosConst.Justification.Right);
    }

    private Style.FontName setFontStyle(String fontSize) {
        Map<String, Style.FontName> fontNameMap = new HashMap<>() {{
            put("A", Style.FontName.Font_A_Default);
            put("B", Style.FontName.Font_B);
            put("DW", Style.FontName.Font_B);
            put("DH", Style.FontName.Font_B);
        }};
        if (fontNameMap.containsKey(fontSize)) {
            return fontNameMap.get(fontSize);
        }
        return Style.FontName.Font_B;
    }

    public List<String> getDetailsMessage() {
        return detailsMessage;
    }

    public String getFontName() {
        return fontName;
    }

    public Style getDetailsStyle() {
        return DetailsStyle;
    }
}
