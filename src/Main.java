import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FileFilter fileFilter = new FileFilter();

        System.out.print("Enter directory: ");
        String dir = scanner.nextLine();
        System.out.println();
        System.out.print("Enter file extension ");
        String extension = scanner.nextLine();
        scanner.close();
        String LCF = null;

        File file = new File(dir);

        fileFilter.setExstension(extension);
        String[] tempArr = file.list(fileFilter);

        if (tempArr.length !=0) {
            LCF = LastCreationFIle(dir, tempArr);
            System.out.println("Name of last created file is " + LCF);
            byten(LCF, tempArr, dir);
        } else {
            System.out.println("There are no your files");
        }

    }

    public static String LastCreationFIle(String dir, String[] name) throws IOException {
                FileTime fileTime = FileTime.fromMillis(0);
                String Output = "";
                for (int i =0 ; i< name.length-1; i++) {
                    Path path = Paths.get(dir + name[i]);
                    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                    if (fileTime.compareTo(attr.creationTime()) < 0) {
                        fileTime = attr.creationTime();
                        Output = name[i];
                    }
                }
        return Output;
    }


    public static void byten (String last, String [] fnames, String dir) throws IOException {
         Path path = Paths.get(dir+last);
         BasicFileAttributes attr = Files.readAttributes(path,BasicFileAttributes.class);
         FileTime lastF = attr.creationTime();
         long lasttoLong = lastF.toMillis();

        for (String i : fnames){
            Path path1 = Paths.get(dir+i);
            BasicFileAttributes attr2 = Files.readAttributes(path1,BasicFileAttributes.class);
            FileTime tocomtaprefiles = attr2.creationTime();
            long tocompaerfileslong = tocomtaprefiles.toMillis();

            if (((lasttoLong - tocompaerfileslong) <=10000) & (!last.equals(i))){
                System.out.println("That file was created no later by 10 sec. " + i);
            }

        }
    }
}
