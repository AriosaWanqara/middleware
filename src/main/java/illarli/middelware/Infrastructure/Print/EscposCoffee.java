package illarli.middelware.Infrastructure.Print;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.PrintModeStyle;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.escpos.barcode.QRCode;
import com.github.anastaciocintra.escpos.image.*;
import com.github.anastaciocintra.output.PrinterOutputStream;
import illarli.middelware.Models.PrinterLibraryRepository;
import illarli.middelware.Utils.SetConfigToPrint;


import javax.imageio.ImageIO;
import javax.print.PrintService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class EscposCoffee implements PrinterLibraryRepository {
    @Override
    public void print(CoffeeMessageBuilder coffeeMessageBuilder, String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            escpos = new EscPos(new PrinterOutputStream(printService));
            printHeader(escpos, coffeeMessageBuilder.header);
            printBody(escpos, coffeeMessageBuilder.body);
            printDetails(escpos, coffeeMessageBuilder.details);
            printFooter(escpos, coffeeMessageBuilder.footer);
            escpos.feed(2);
            escpos.cut(EscPos.CutMode.FULL);
            escpos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void printHeader(CoffeeMessageHeader coffeeMessageHeader, String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            Style titleStyle = coffeeMessageHeader.getHeaderTitleCoffeeStyle();
            Style subtitleStyle = coffeeMessageHeader.getHeaderSubtitleCoffeeStyle();
            escpos = new EscPos(new PrinterOutputStream(printService));
            coffeeMessageHeader.getTitle().forEach(title -> {
                try {
                    System.out.println(title);
                    escpos.writeLF(titleStyle, title);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.feed(2);
            coffeeMessageHeader.getSubtitle().forEach(subtitle -> {
                try {
                    System.out.println(subtitle);
                    escpos.writeLF(subtitleStyle, subtitle);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.feed(2);
            escpos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void printBody(CoffeeMessageBody coffeeMessageBody, String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            Style bodyStyle = coffeeMessageBody.getBodyStyle();
            escpos = new EscPos(new PrinterOutputStream(printService));
            coffeeMessageBody.getBodyMessages().forEach(message -> {
                try {
                    System.out.println(message);
                    escpos.writeLF(bodyStyle, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void printDetails(CoffeeMessageDetails coffeeMessageDetails, String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            Style detailsStyle = coffeeMessageDetails.getDetailsStyle();
            escpos = new EscPos(new PrinterOutputStream(printService));
            coffeeMessageDetails.getDetailsMessage().forEach(message -> {
                try {
                    System.out.println(message);
                    escpos.writeLF(detailsStyle, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.feed(2);
            escpos.cut(EscPos.CutMode.FULL);
            escpos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void printFooter(CoffeeMessageFooter coffeeMessageFooter, String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            Style footerStyle = coffeeMessageFooter.getFooterStyle();
            escpos = new EscPos(new PrinterOutputStream(printService));
            coffeeMessageFooter.getFooterMessages().forEach((s, strings) -> {
                if (s.equals("text")) {
                    strings.forEach(text -> {
                        try {
                            escpos.writeLF(footerStyle, text);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                if (s.equals("imgU")) {
                    strings.forEach(text -> {
                        printImageFromUrl(escpos, text);
                    });
                }
                if (s.equals("imgF")) {
                    strings.forEach(text -> {
                        printImageFromFile(escpos, text);
                    });
                }
                if (s.equals("BC")) {
                    strings.forEach(text -> {
                        printBarCode(escpos, text);
                    });
                }
                if (s.equals("QR")) {
                    strings.forEach(text -> {
                        printQRCode(escpos, text);
                    });
                }
            });
            escpos.feed(3);
            escpos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cut(String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            escpos = new EscPos(new PrinterOutputStream(printService));
            escpos.feed(4);
            escpos.cut(EscPos.CutMode.FULL);
            escpos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean printTest(SetConfigToPrint config) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(config.getPrinterName());
        EscPos escpos;
        try {
            escpos = new EscPos(new PrinterOutputStream(printService));
            Style title = new Style()
                    .setFontSize(Style.FontSize._2, Style.FontSize._2)
                    .setJustification(EscPosConst.Justification.Center);
            Style title1 = new Style()
                    .setLineSpacing(8)
                    .setFontName(Style.FontName.Font_B);
            String[] characters = new String[11];
            Arrays.fill(characters, "000000000-");
            String zeros = String.join("", characters);
            escpos.writeLF(title, "Test");
            PrintModeStyle pms = new PrintModeStyle().setFontSize(false, false).setFontName(config.getFontName());
            escpos.feed(1);
            escpos.writeLF(pms, zeros);
            escpos.feed(1);
            escpos.writeLF(title1, "Cuente los caracteres que entran en la primera linea de texto y coloque el valor en Cantidad de caracteres 👌");
            escpos.feed(1);
            escpos.writeLF(new Style().setJustification(EscPosConst.Justification.Center), "Configuracion Actual");
            escpos.writeLF("Nombre de impresora: " + config.getPrinterName());
            escpos.writeLF("Tipo de fuente: " + config.getFontName().name());
            escpos.feed(2);
            escpos.feed(5);
            escpos.cut(EscPos.CutMode.FULL);
            escpos.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public String[] getPrinterList() {
        return PrinterOutputStream.getListPrintServicesNames();
    }

    @Override
    public void OpenCashDrawerFromPrinter(String printerName) {
        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        EscPos escpos;
        try {
            escpos = new EscPos(new PrinterOutputStream(printService));
            escpos.write(27).write(112).write(0).write(25).write(250);
            escpos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printBarCode(EscPos escpos, String barCodeMessage) {
        try {
            BarCode barcode = new BarCode();
            escpos.feed(1);
            escpos.write(barcode, barCodeMessage);
            escpos.feed(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printQRCode(EscPos escpos, String QRCodeMessage) {
        try {
            QRCode qrcode = new QRCode();
            qrcode.setJustification(EscPosConst.Justification.Center);
            escpos.feed(1);
            escpos.write(qrcode, QRCodeMessage);
            escpos.feed(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printImageFromUrl(EscPos escpos, String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);

            CoffeeImage coffeeImage = new CoffeeImageImpl(bufferedImage);
            Bitonal algorithm = new BitonalOrderedDither();
            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            imageWrapper.setJustification(EscPosConst.Justification.Center);

            EscPosImage escposImage = new EscPosImage(coffeeImage, algorithm);
            escpos.write(imageWrapper, escposImage);
            escpos.feed(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printImageFromFile(EscPos escpos, String fileUrl) {
        try {
            File file = new File(fileUrl);
            BufferedImage bufferedImage = ImageIO.read(file);

            CoffeeImage coffeeImage = new CoffeeImageImpl(bufferedImage);
            Bitonal algorithm = new BitonalOrderedDither();
            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            imageWrapper.setJustification(EscPosConst.Justification.Center);

            EscPosImage escposImage = new EscPosImage(coffeeImage, algorithm);
            escpos.write(imageWrapper, escposImage);
            escpos.feed(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printBody(EscPos escpos, CoffeeMessageBody coffeeMessageBody) {
        try {
            Style bodyStyle = coffeeMessageBody.getBodyStyle();
            coffeeMessageBody.getBodyMessages().forEach(message -> {
                try {
                    System.out.println(message);
                    escpos.writeLF(bodyStyle, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printHeader(EscPos escpos, CoffeeMessageHeader coffeeMessageHeader) {
        try {
            Style titleStyle = coffeeMessageHeader.getHeaderTitleCoffeeStyle();
            Style subtitleStyle = coffeeMessageHeader.getHeaderSubtitleCoffeeStyle();
            coffeeMessageHeader.getTitle().forEach(title -> {
                try {
                    System.out.println(title);
                    escpos.writeLF(titleStyle, title);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.feed(2);
            coffeeMessageHeader.getSubtitle().forEach(subtitle -> {
                try {
                    System.out.println(subtitle);
                    escpos.writeLF(subtitleStyle, subtitle);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.feed(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printDetails(EscPos escpos, CoffeeMessageDetails coffeeMessageDetails) {
        try {
            Style detailsStyle = coffeeMessageDetails.getDetailsStyle();
            coffeeMessageDetails.getDetailsMessage().forEach(message -> {
                try {
                    System.out.println(message);
                    escpos.writeLF(detailsStyle, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escpos.feed(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printFooter(EscPos escpos, CoffeeMessageFooter coffeeMessageFooter) {
        try {
            Style footerStyle = coffeeMessageFooter.getFooterStyle();
            coffeeMessageFooter.getFooterMessages().forEach((s, strings) -> {
                if (s.equals("text")) {
                    strings.forEach(text -> {
                        try {
                            escpos.writeLF(footerStyle, text);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                if (s.equals("imgU")) {
                    strings.forEach(text -> {
                        printImageFromUrl(escpos, text);
                    });
                }
                if (s.equals("imgF")) {
                    strings.forEach(text -> {
                        printImageFromFile(escpos, text);
                    });
                }
                if (s.equals("BC")) {
                    strings.forEach(text -> {
                        printBarCode(escpos, text);
                    });
                }
                if (s.equals("QR")) {
                    strings.forEach(text -> {
                        printQRCode(escpos, text);
                    });
                }
            });
            escpos.feed(3);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
