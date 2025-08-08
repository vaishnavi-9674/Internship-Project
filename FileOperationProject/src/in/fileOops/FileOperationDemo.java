package in.fileOops;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class FileOperationDemo {
	
	 private static final String FILE_PATH = "sample.txt";

	public static void main(String[] args) {
		try 
		{
            writeToFile("Original content.\nWelcome to File Operations!");
            System.out.println("Initial read:");
            readFromFile();

            modifyFileContent("Welcome", "Hello");
            System.out.println("\nAfter modification:");
            readFromFile();

        } 
		catch (IOException e) 
		{
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void writeToFile(String content) throws IOException
    {
        Files.write(Paths.get(FILE_PATH), content.getBytes());
        System.out.println("File written.");
    }

    public static void readFromFile() throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        lines.forEach(System.out::println);
    }
    public static void modifyFileContent(String target, String replacement) throws IOException 
    {
        Path path = Paths.get(FILE_PATH);
        String content = new String(Files.readAllBytes(path));
        String modified = content.replaceAll(target, replacement);
        Files.write(path, modified.getBytes());
        System.out.println("File modified.");
    }
		



}
