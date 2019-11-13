package misiepysie.graph_be.Modules;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeModulesWithJavaParser {

    public void findModuleDependencies(String rootPath) throws IOException {
        File modules = new File(rootPath);
        System.out.println("path test: " + rootPath);

        //zeby miec liste compUnits to musimy najpierw sparsować pliki zrodlowe

        SourceRoot sourceRoot = new SourceRoot(modules.toPath()); //albo po prostu path XD
        //parsujemy kod i otrzymujemy liste el. typu parseResult<compilationUnit>,   //parseResult - może nim być compUnit bo pr to typ generyczny ktory ma pole T result
        List<ParseResult<CompilationUnit>> parseResults = sourceRoot.tryToParse("");
//compilationUnit -jednostka kompilowalna, zwykle odnosi się do jednego pliku źródłowego, każdy plik źródłowy posiada dokładnie jedną
        //packageDeclaration no logiczne że jedna klasa nie może być więcj niż w jednej paczce naraz duh (no chyba że są zagnieżdżone ale to i tak bezpośrednio jest tylko w jednej)


        //https://static.javadoc.io/com.github.javaparser/javaparser-core/3.5.7/com/github/javaparser/utils/SourceRoot.html


        //TODO przeszukujemy parseResults i sprawdzamy z jakiej paczki pochodzi compilationUnit
        // TODO potem jak znajdziemy package declarations (za pomocą metody getPackageDeclarations) to musimy znalezc metody z paczki
        //i przypisac je do danej paczki  i to najlepiej chyba trzymac w jakiejs kolekcji

// TODO i trzeba jeszcze wymyslic jak te znalezione paczki+metody umiescic w node'ach i edge'ach am i right????
        //TODO ogolnie tak zrobic zeby zwracalo JSONa zeby mozna bylo wyswietlic graf
        //ja nie wiem czy to jest dobry pomysł nie bijcie mnie proszę ale chyba namieszałam jeszcze bardziej XDDDDDD
        //nic nie zrobiłam a moja szara komórka właśnie tańczy tańce fortnite
    }


}
