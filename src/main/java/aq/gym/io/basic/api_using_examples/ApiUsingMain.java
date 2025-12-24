package aq.gym.io.basic.api_using_examples;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Arrays;
import java.nio.file.Path;

public class ApiUsingMain {

    public static void main(String[] args) {
        textWrite();
        textRead();
        writeData();
        readData();
        printListFilesOfFile();
        randomAccess();
    }

    private static void textWrite() {
        String pathText = "src\\main\\java\\aq\\gym\\io\\basic\\api_using_examples\\text.txt";
        try(PrintWriter pw = new PrintWriter(pathText)) {
            if(Files.notExists(Path.of(pathText)))
                Files.createFile(Path.of(pathText));
            pw.println("Hello");
            pw.println("World");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void textRead() {
        String pathText = "src\\main\\java\\aq\\gym\\io\\basic\\api_using_examples\\text.txt";
        try(FileReader fr = new FileReader(pathText)) {
            char[] buff = new char[7];
            while(fr.read(buff) != -1) {
                char[] copy = buff;
                System.out.println(new String(buff, 0, 5));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeData() {
        String pathData = "src\\main\\java\\aq\\gym\\io\\basic\\api_using_examples\\data.txt";
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(pathData))) {
            if(Files.notExists(Path.of(pathData)))
                Files.createFile(Path.of(pathData));
            dos.writeInt(85);
            dos.writeChar('|');
            dos.writeUTF("line");
            dos.writeChar('|');
            dos.writeUTF("addition line");
            dos.writeInt(58);
            dos.writeChar('+');
            dos.writeUTF("mock");
            dos.writeChar('|');
            dos.writeUTF("addition mock");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void readData() {
        String pathData = "src\\main\\java\\aq\\gym\\io\\basic\\api_using_examples\\data.txt";
        try(DataInputStream dis = new DataInputStream(new FileInputStream(pathData))) {
            while(true) {
                System.out.printf("%d%s%s%s%s%n",
                        dis.readInt(), dis.readChar(), dis.readUTF(), dis.readChar(), dis.readUTF());
            }
        } catch (EOFException e) {
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void printListFilesOfFile() {
        File f = new File("src\\main\\java\\aq\\gym\\contests\\stack");
        Arrays.stream(f.listFiles()).forEach(System.out::println);
    }

    private static void randomAccess() {
        String pathData = "src\\main\\java\\aq\\gym\\io\\basic\\api_using_examples\\random.txt";
        try {
            Files.deleteIfExists(Path.of(pathData));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try(RandomAccessFile raf = new RandomAccessFile(pathData, "rw")) {
            String hello = "Hello", bye = "Bye";
            int fortyTwo = 42, eightyFive = 85;
            raf.writeInt(fortyTwo);
            raf.writeInt(hello.length());
            raf.writeChars(hello);
            raf.writeInt(eightyFive);
            raf.writeInt(bye.length());
            raf.writeChars(bye);
            raf.seek(0L);
            System.out.println("Current file state:");
            while (raf.getFilePointer() < raf.length()) {
                int num = raf.readInt();
                int len = raf.readInt();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < len; i++) {
                    sb.append(raf.readChar());
                }
                System.out.printf("%d %s%n", num, sb.toString());
            }
            raf.seek(4L + 4L + hello.length() * 2);
            int num = raf.readInt();
            int len = raf.readInt();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len; i++) {
                sb.append(raf.readChar());
            }
            System.out.println("Second file entry:");
            System.out.printf("%d %s%n", num, sb.toString());
            raf.seek(4L + 4L + hello.length() * 2);
            raf.writeInt(555);
            raf.writeInt("Updated line".length());
            raf.writeChars("Updated line");
            raf.seek(4L + 4L + hello.length() * 2);
            num = raf.readInt();
            len = raf.readInt();
            sb = new StringBuilder();
            for(int i = 0; i < len; i++) {
                sb.append(raf.readChar());
            }
            System.out.println("Modified second file entry:");
            System.out.printf("%d %s%n", num, sb.toString());
            raf.seek(0L);
            System.out.println("Current file state:");
            while (raf.getFilePointer() < raf.length()) {
                num = raf.readInt();
                len = raf.readInt();
                sb = new StringBuilder();
                for(int i = 0; i < len; i++) {
                    sb.append(raf.readChar());
                }
                System.out.printf("%d %s%n", num, sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
