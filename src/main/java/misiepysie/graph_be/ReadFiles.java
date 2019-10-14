package misiepysie.graph_be;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFiles {

    private static List<SourceFile> listOfSourceFiles;

    public ReadFiles() throws IOException {
    }

    public static void createNodeForEachFile() {
        listOfSourceFiles = new ArrayList<SourceFile>();
        for (int i = 0; i < listAllFilesNames().size(); i++) {
            SourceFile sf = new SourceFile(listAllFilesNames().get(i),getSize(i),connectionsOfFiles(listAllFilesNames()));
            getListOfSourceFiles().add(sf);
        }

    }


    public static List<String> listAllFilesNames() {

        try (
                Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"\\src"))) { //todo: add the path of current directory

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            //set list of files as an already read directory


            result.forEach(System.out::println);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String connectionsOfFiles(List<String> listOfFiles) {
        System.out.println("\n\n");
        for (int i = 0; i < listOfFiles.size(); i++) {
            try {

                Stream<String> lines = Files.lines(Paths.get(listOfFiles.get(i)));
                System.out.println("\n" + listOfFiles.get(i) + "\n" + getSize(i));

                List<String> content = lines.collect(Collectors.toList());
                StringBuilder s=new StringBuilder("\nImports: ");
                content.forEach(x -> s.append(searchImports(x)));
                s.append("\nIncludes:");
                content.forEach(x -> s.append(searchIncludes(x)));
                s.append("\nUsings:");
                content.forEach(x -> s.append(searchUsings(x)));

                return s.toString();

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
        return null;
    }

    public static String searchImports(String line) {

        if (line.startsWith("import")) {
            return line;
        }
        else return null;
    }

    public static String searchIncludes(String line) {

        if (line.startsWith("#include")) {
            return line;
        }
        else return null;
    }

    public static String searchUsings(String line) {

        if (line.startsWith("using")) {
            return line;
        }
        else return null;
    }

    public static String getSize(int i) {
        File f = new File(listAllFilesNames().get(i));
        if (!f.exists() || !f.isFile()) {
            return ("there is no file in this path");
        } else {
            return ("Size of file: " + getFileSizeBytes(f));
        }


    }

    private static String getFileSizeBytes(File file) {
        return (double) file.length() + "  bytes";
    }

    public static List<SourceFile> getListOfSourceFiles() {
        return listOfSourceFiles;
    }




}
