package illarli.middelware.Infrastructure.Print;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CoffeeMessageBuilder {
    @NotNull(message = "Not header")
    public CoffeeMessageHeader header;
    @NotNull(message = "Not header")
    public CoffeeMessageBody body;
    @Null
    public CoffeeMessageDetails details;
    @Null
    public CoffeeMessageFooter footer;

    public CoffeeMessageBuilder(
            CoffeeMessageHeader header,
            CoffeeMessageBody body,
            CoffeeMessageDetails details,
            CoffeeMessageFooter footer
    ) {
        this.header = header;
        this.body = body;
        this.details = details;
        this.footer = footer;
    }

    public void build(String fontName) {
        this.body.build(fontName);
        this.details.build(fontName);
        this.footer.build(fontName);
        this.header.build(fontName);
    }
}
