package misiepysie.graph_be;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFiles {

    public ReadFiles() throws IOException {
    }

    private static List<SourceFile> listOfSourceFiles;

    public static void createNodeForEachFile() {

        listOfSourceFiles = new ArrayList<SourceFile>();

        for (int i = 0; i < listAllFilesNames().size() ; i++) {

            SourceFile sf = new SourceFile(listAllFilesNames().get(i),getSize(i),connectionsOfFiles(listAllFilesNames().get(i)));
            getListOfSourceFiles().add(sf);
        }

    }


    public static List<String> listAllFilesNames() {

        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"/src"))) { //todo: add the path of current directory

            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            //set list of files as an already read directory

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String connectionsOfFiles(String listOfFiles) {

        StringBuilder out = new StringBuilder("");
        try {
                Stream<String> lines = Files.lines(Paths.get(listOfFiles));
                List<String> content = lines.collect(Collectors.toList());
                StringBuilder s = new StringBuilder("\nImports:");
                content.forEach(x -> s.append(searchImports(x)));
                s.append("\nIncludes:");
                content.forEach(x -> s.append(searchIncludes(x)));
                s.append("\nUsings:");
                content.forEach(x -> s.append(searchUsings(x)));
                String returnstatement= s.toString();
                System.out.println( "\n" + listOfFiles + "\n" + getSizeString(listOfFiles) +"\n");

                int countingImport = countingWordsInString(returnstatement,"import");
                int countingIncludes = countingWordsInString(returnstatement,"include");
                int countingUsings = countingWordsInString(returnstatement,"using");

                out.append("Imports:" + countingImport + "Include:" + countingIncludes + "Using:" + countingUsings );
//              System.out.print("Imports: " + countingImport + " Include: " + countingIncludes + " Using: " + countingUsings);
//              System.out.println(returnstatement);
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return null;
        }
        return out.toString();
    }

    public static int countingWordsInString(String descriptionFile, String keyWord ){

        int j = 0;
        Pattern p = Pattern.compile(keyWord);
        Matcher m = p.matcher( descriptionFile );
        while (m.find()) {
            j++;
        }
        return j;
    }

    public static String searchImports(String line) {

        if (line.startsWith("import")) {
            return line;
        }
        else return null + " ";
    }

    public static String searchIncludes(String line) {

        if (line.startsWith("#include")) {
            return line;
        }
        else return null + " ";
    }

    public static String searchUsings(String line) {

        if (line.startsWith("using")) {
            return line;
        }
        else return null + " ";
    }

    public static String getSize(int i) {

        File f = new File(listAllFilesNames().get(i));
        if (!f.exists() || !f.isFile()) {
            return ("there is no file in this path");
        } else {
            return ("Size of file: " + getFileSizeBytes(f));
        }


    }

    public static String getSizeString(String i) {

        File f = new File(i);
        if (!f.exists() || !f.isFile()) {
            return ("there is no file in this path");
        } else {
            return ("Size of file: " + getFileSizeBytes(f));
        }


    }

    private static String getFileSizeBytes(File file) {
        return (double) file.length() + "b";
    }

    private static final long  MEGABYTE = 1024L * 1024L;

    public static long bytesToMeg(long bytes) {
        return bytes / MEGABYTE ;
    }

    public static List<SourceFile> getListOfSourceFiles() {
        return listOfSourceFiles;
    }




}
