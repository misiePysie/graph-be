package misiepysie.graph_be;

import com.google.gson.Gson;
import misiepysie.graph_be.Callgraph.*;
import misiepysie.graph_be.Data.Data;
import misiepysie.graph_be.Data.DataApi;
import misiepysie.graph_be.Data.DirectoryPath;
import misiepysie.graph_be.Data.EdgeApi;
import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;
import misiepysie.graph_be.Modules.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;


@RestController
public class GraphApplicationController {

    @CrossOrigin(origins = "http://localhost:8080")

    @ResponseBody
    @RequestMapping(path="/dir", method = RequestMethod.POST)
    public String dirPath(@RequestBody String paths) {


        Gson gson = new Gson();
        try {
            DirectoryPath temp = gson.fromJson(paths, DirectoryPath.class);

            if (temp.getBackendSrc() == null || temp.getFrontendSrc() == null) {
                throw (new IllegalArgumentException());
            }


            ArrayList<Node> tempNodes = new ArrayList<Node>();
            ArrayList<Edge> tempEdge = new ArrayList<Edge>();

            ArrayList<EdgeApi> edgesData = new ArrayList<EdgeApi>();

            Data dataTemp = new Data(tempNodes, tempEdge);

            AnalyzeFile.setListOfFileNames(new ArrayList<String>());
            AnalyzeFile.listAllFilesNames(temp.getBackendSrc());
            AnalyzeFile.listAllFilesNames(temp.getFrontendSrc());

            AnalyzeFile.createNodeForEachFile();

            dataTemp.getNodesData().addAll(AnalyzeFile.getListOfNodes());

            AnalyzeFile.createEdge();
            Data.getEdgesData().addAll(AnalyzeFile.getListOfEdges());

            for (int i = 0; i < dataTemp.getEdgesData().size(); i++) {
                edgesData.add(new EdgeApi(dataTemp.getEdgesData().get(i).getFrom().getId(), dataTemp.getEdgesData().get(i).getTo().getId(), dataTemp.getEdgesData().get(i).getWeight()));
            }


            DataApi apiData = new DataApi(dataTemp.getNodesData(), edgesData);

            return gson.toJson(apiData);

        } catch (IllegalArgumentException e) {
            System.out.println("Wrong argument");
            return e.getStackTrace().toString();
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")

    @ResponseBody
        @RequestMapping(path="/calls", method=RequestMethod.POST)
        public String callsAnalize(@RequestBody String path){

            Gson gson = new Gson();

           Path tempPath = gson.fromJson(path, Path.class);
           ArrayList<NodeMethod> tempToMethods = new ArrayList<NodeMethod>();
           ArrayList<NodeMethod> tempFromMethods = new ArrayList<NodeMethod>();
           ArrayList<EdgeMethod> tempEdgeMethod = new ArrayList<EdgeMethod>();

            DataCallGraph temp = new DataCallGraph(tempToMethods,tempFromMethods,tempEdgeMethod);
            try{

                File output = new File(System.getProperty("user.home")+"\\output.txt");

                ProcessBuilder pb=new ProcessBuilder("java", "-jar", System.getProperty("user.dir")+"\\javacg-0.1-SNAPSHOT-static.jar",tempPath.getPath());
                pb.redirectErrorStream(false);
                pb.redirectOutput(output);

                Process process=pb.start();

                process.waitFor();

                AnalyzeCalls.analyzeCallGraph(System.getProperty("user.home")+File.separator+"output.txt",temp);
                temp.getMethodsToArray().forEach(x-> System.out.println(x));
                System.out.println("\n\n");
                temp.getMethodsFromArray().forEach(x-> System.out.println(x));
                System.out.println("\n\n");
                temp.getEdgesOfMethods().forEach(x-> System.out.println(x));
            }

            catch(InterruptedException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return temp.toString();

        }

    @CrossOrigin(origins = "http://localhost:8080")

    @ResponseBody
    @RequestMapping(path="/modules", method=RequestMethod.POST)
    public String moduleAnalize(@RequestBody String path){

        Gson gson = new Gson();

        Path tempPath = gson.fromJson(path, Path.class);
        ArrayList<NodePackage> tempNodes = new ArrayList<NodePackage>();
        ArrayList<EdgeMethodPackage> tempEdgeMethodPackage = new ArrayList<EdgeMethodPackage>();
        ArrayList<EdgePackage> tempEdgePackage = new ArrayList<EdgePackage>();

        DataModules temp = new DataModules(tempNodes,tempEdgeMethodPackage,tempEdgePackage);
        try{

            File output = new File(System.getProperty("user.home")+"\\output.txt");

            ProcessBuilder pb=new ProcessBuilder("java", "-jar", System.getProperty("user.dir")+"\\javacg-0.1-SNAPSHOT-static.jar",tempPath.getPath());
            pb.redirectErrorStream(false);
            pb.redirectOutput(output);

            Process process=pb.start();

            process.waitFor();


            AnalyzeModules.analyzeModule(System.getProperty("user.home")+"\\output.txt",temp);

            temp.getListOfNodePackage().forEach(x-> System.out.println(x));
            System.out.println("\n\n");
            temp.getListOfEdgeMethodPackage().forEach(x-> System.out.println(x));
            System.out.println("\n\n");
            temp.getListOfEdgePackage().forEach(x-> System.out.println(x));

        }

        catch(InterruptedException e){
            System.out.println("syplo sie w api");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("Api sie syplo w drugim");
            e.printStackTrace();
        }

        return gson.toJson(temp);

    }
}