import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {
    private String exstension;

    public void setExstension(String exstension) {
        this.exstension = exstension;
    }

    @Override
    public boolean accept(File file, String s) {
        return s.toLowerCase().endsWith(exstension);
    }
}
