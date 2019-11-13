package misiepysie.graph_be;

import misiepysie.graph_be.Callgraph.AnalyzeCalls;
import misiepysie.graph_be.Modules.AnalyzeModulesWithJavaParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class GraphApplication {

    public static void main(String[] args) throws IOException {



        SpringApplication.run(GraphApplication.class, args);
        AnalyzeModulesWithJavaParser amwjp = new AnalyzeModulesWithJavaParser();
        amwjp.findModuleDependencies("C:\\Users\\asus\\graph-be\\src");

        }

}

