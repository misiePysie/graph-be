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
    private static List<String> listOfFileNames;

    public static void createNodeForEachFile() {

        listOfSourceFiles = new ArrayList<SourceFile>();


        for (int i = 0; i < listOfFileNames.size() ; i++) {

            SourceFile sf = new SourceFile(listOfFileNames.get(i),getSize(i),connectionsOfFiles(listOfFileNames.get(i)));
            getListOfSourceFiles().add(sf);
        }
        resizeCircle();

    }


    public static List<String> listAllFilesNames(String path ) {

        try (Stream<Path> walk = Files.walk(Paths.get(path))) { //todo: add the path of current directory

            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            //set list of files as an already read directory
            listOfFileNames=result;
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

                String returnstatement= s.toString();

                int countingImport = countingWordsInString(returnstatement,"import");

                out.append("Imports:" + countingImport);
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


    public static double getSize(int i) {


        File f = new File(listOfFileNames.get(i));
        return f.length();

    }



    private static void resizeCircle()
    {
        double max=-1;
        int index=-1;
        for (int i=0;i<listOfSourceFiles.size();i++)
        {
            if(listOfSourceFiles.get(i).getSizeOfFile()>max) {
                max = listOfSourceFiles.get(i).getSizeOfFile();
                index = i;
            }
        }
        listOfSourceFiles.get(index).setSizeOfCircle(100);
        double val=0;
        for(int i=0;i<listOfSourceFiles.size();i++)
        {
            val=(listOfSourceFiles.get(i).getSizeOfFile()*100)/max;
            listOfSourceFiles.get(i).setSizeOfCircle(val);
        }

    }



    public static List<SourceFile> getListOfSourceFiles() {
        return listOfSourceFiles;
    }




}
