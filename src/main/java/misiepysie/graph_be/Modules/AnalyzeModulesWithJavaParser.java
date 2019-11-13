package misiepysie.graph_be.Modules;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalyzeModulesWithJavaParser {

    public void findModuleDependencies(String rootPath) throws IOException {
        File modules = new File(rootPath);
        System.out.println("path test: " + rootPath);

        SourceRoot sourceRoot = new SourceRoot(modules.toPath());

        List<ParseResult<CompilationUnit>> parseResults = sourceRoot.tryToParse("");

        List<CompilationUnit> allCompUnits = parseResults.stream()
                .filter(ParseResult::isSuccessful)
                .map(r -> r.getResult().get())
                .collect(Collectors.toList());

        for (CompilationUnit cu:allCompUnits){
            //Work in progress
            cu.getPackageDeclaration().get().getName();
            cu.findAll(MethodCallExpr.class);
        }




        // TODO potem jak znajdziemy package declarations (za pomocą metody getPackageDeclarations) to musimy znalezc metody z paczki
        //i przypisac je do danej paczki  i to najlepiej chyba trzymac w jakiejs kolekcji

        // TODO i trzeba jeszcze wymyslic jak te znalezione paczki+metody umiescic w node'ach i edge'ach am i right????
        //TODO ogolnie tak zrobic zeby zwracalo JSONa zeby mozna bylo wyswietlic graf

    }


}
