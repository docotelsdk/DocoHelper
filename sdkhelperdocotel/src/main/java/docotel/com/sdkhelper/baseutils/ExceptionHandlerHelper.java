package docotel.com.sdkhelper.baseutils;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

/**
 * Created by mzennis on 9/13/16.
 */
public class ExceptionHandlerHelper implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUEH;
    private String localPath;
    private String url;

    public ExceptionHandlerHelper() {
        this(null, null);
    }

    public ExceptionHandlerHelper(String localPath, String url) {
        this.localPath = localPath;
        this.url = url;
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();

        if (this.localPath == null) {
            File sd = Environment.getExternalStorageDirectory();
            if (sd.canWrite()) {
                String folderName = ApplicationHelper.getLastPackageName();
                File f = new File(sd, folderName);
                f.mkdirs();
                this.localPath = f.getAbsolutePath();
            }
        }
    }

    public void uncaughtException(Thread t, Throwable e) {
        String filename = DateHelper.timestamp() + "_" + ApplicationHelper.getLastPackageName() + ".log.txt";

        if (localPath != null) {
            File f = new File(localPath + "/" + filename);
            if (!f.exists())
                f.getParentFile().mkdirs();
            try {
                final PrintStream printWriter = new PrintStream(f);

                e.printStackTrace(printWriter);
                printWriter.close();
            } catch (Exception exc) {

            }
            // writeToFile(stacktrace, filename);
        }

        if (url != null) {
            // sendToServer(stacktrace, filename);
        }

        defaultUEH.uncaughtException(t, e);
    }

    private void writeToFile(String stacktrace, String filename) {
        try {
            File f = new File(localPath + "/" + filename);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                // f.mkdirs();
                // f.createNewFile();
            }

            BufferedWriter bos = new BufferedWriter(new FileWriter(localPath + "/" + filename));
            bos.write(stacktrace);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
