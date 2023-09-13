package illarli.middelware.Infrastructure.Print;

import java.io.IOException;

public class Cancelable {
    public static void clearSpooler() throws IOException {
        String[] stopCommand = {"clear.bat"};
        Process process = Runtime.getRuntime().exec(stopCommand);
        waitForProcess(process);
    }

    public static void startPrintSpooler() throws IOException {
        String startCommand = "cmd.exe net start spooler";
        Process process = Runtime.getRuntime().exec(startCommand);
        waitForProcess(process);
    }

    public static void waitForProcess(Process process) {
        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Command exited with error code: " + exitCode);
            } else {
                System.out.println("end");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
