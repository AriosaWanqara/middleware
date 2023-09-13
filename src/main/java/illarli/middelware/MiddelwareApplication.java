package illarli.middelware;

import illarli.middelware.Models.BalanceType;
import illarli.middelware.Models.DocumentType;
import illarli.middelware.Repositories.BalanceTypeRepository;
import illarli.middelware.Repositories.DocumentTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MiddelwareApplication {

    public static void main(String[] args) {
        try {
            try {
                createClearBat();
                File myObj = new File("filename.txt");
                Scanner myReader = new Scanner(myObj);
                String data = null;
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                if (data != null) {
                    URL url = new URL("http://localhost:" + data + "/shut-down");
                    sendGET(url);
                }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
            } finally {
                FileWriter myWriter = new FileWriter("filename.txt");
                myWriter.write("52155");
                myWriter.close();
            }

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        SpringApplication.run(MiddelwareApplication.class, args);
//        try {
//            if (args.length > 0) {
//                try {
//                    try {
//                        createClearBat();
//                        File myObj = new File("filename.txt");
//                        Scanner myReader = new Scanner(myObj);
//                        String data = null;
//                        while (myReader.hasNextLine()) {
//                            data = myReader.nextLine();
//                        }
//                        if (data != null) {
//                            URL url = new URL("http://localhost:" + data + "/shut-down");
//                            sendGET(url);
//                        }
//                    } catch (FileNotFoundException e) {
//                        System.out.println("An error occurred.");
//                    } finally {
//                        FileWriter myWriter = new FileWriter("filename.txt");
//                        myWriter.write(args[0]);
//                        myWriter.close();
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("An error occurred.");
//                }
//            }
//            String puerto = args[0];
//            System.setProperty("server.port", puerto);
//            SpringApplication.run(MiddelwareApplication.class, args);
//        } catch (Exception e) {
//            System.err.println("Ocurrió un error: " + e);
//            System.exit(1);
//        }
    }

    @Bean
    CommandLineRunner commandLineRunner(DocumentTypeRepository documentTypeRepository, BalanceTypeRepository balanceTypeRepository) {
        return args -> {
            ArrayList<DocumentType> documents = new ArrayList<>(List.of(
                    new DocumentType(1L, "Factura Electrónica"),
                    new DocumentType(2L, "Factura Preimpresa"),
                    new DocumentType(3L, "Recibos"),
                    new DocumentType(4L, "Cotizaciones"),
                    new DocumentType(5L, "Cierres de cajas y otros")

            ));
            ArrayList<BalanceType> balanceType = new ArrayList<>(List.of(
                    new BalanceType(1L, "CAS PDN", 9600, 8, 1, 0, new byte[]{0x05, 0x11}),
                    new BalanceType(2L, "CAS CL 5200", 9600, 8, 1, 0, new byte[]{0x52, 0x34, 0x35, 0x46, 0x30, 0x34, 0x2C, 0x30, 0x30, 0x0A})
            ));
            documents.forEach(documentTypeRepository::save);
            balanceType.forEach(balanceTypeRepository::save);
        };
    }

    private static void sendGET(URL obj) throws IOException {
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

    }

    private static void createClearBat() {
        try {
            FileWriter myWriter = new FileWriter("clear.bat");
            myWriter.write("@echo off\n" +
                    ":: BatchGotAdmin\n" +
                    ":-------------------------------------\n" +
                    "REM  --> Check for permissions\n" +
                    ">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\"\n" +
                    "REM --> If error flag set, we do not have admin.\n" +
                    "if '%errorlevel%' NEQ '0' (    echo Requesting administrative privileges...    goto UACPrompt) else ( goto gotAdmin )\n" +
                    ":UACPrompt\n" +
                    "    echo Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\getadmin.vbs\"\n" +
                    "    echo UAC.ShellExecute \"%~s0\", \"\", \"\", \"runas\", 1 >> \"%temp%\\getadmin.vbs\"\n" +
                    "    \"%temp%\\getadmin.vbs\"\n" +
                    "    exit /B\n" +
                    ":gotAdmin\n" +
                    "net stop spooler &\n" +
                    "del %systemroot%\\System32\\spool\\printers\\* /Q &\n" +
                    "net start spooler");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
