package illarli.middelware.Infrastructure.Print;

import com.github.anastaciocintra.escpos.Style;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMessageBody {
    private List<String> bodyMessages;
    private String fontName;
    private Style bodyStyle;
    private int bodyI;

    public CoffeeMessageBody(List<String> bodyMessages) {
        this.bodyMessages = bodyMessages;
        this.bodyStyle = new Style();
    }


    public void build(String fontName) {
        this.fontName = fontName;
        this.bodyStyle.setFontName(setBodyFontName(fontName));
    }

    private Style.FontName setBodyFontName(String fontName) {
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

    public List<String> getBodyMessages() {
        return bodyMessages;
    }

    public String getFontName() {
        return fontName;
    }

    public Style getBodyStyle() {
        return bodyStyle;
    }

    public int getBodyI() {
        return bodyI;
    }

    public void setBodyMessages(List<String> bodyMessages) {
        this.bodyMessages = bodyMessages;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setBodyStyle(Style bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public void setBodyI(int bodyI) {
        this.bodyI = bodyI;
    }

    public CoffeeMessageBody() {
        this.bodyStyle = new Style();
    }
}
