package misiepysie.graph_be;

import com.google.gson.Gson;
import misiepysie.graph_be.Callgraph.AnalyzeCalls;
import misiepysie.graph_be.Callgraph.DataCallGraph;
import misiepysie.graph_be.Callgraph.Path;
import misiepysie.graph_be.Data.Data;
import misiepysie.graph_be.Data.DataApi;
import misiepysie.graph_be.Data.DirectoryPath;
import misiepysie.graph_be.Data.EdgeApi;
import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
//
            dataTemp.getNodesData().addAll(AnalyzeFile.getListOfNodes());
//


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
        @ResponseBody
        @RequestMapping(path="/calls", method=RequestMethod.POST)
        public String callsAnalize(@RequestBody String path){

            Gson gson = new Gson();

           Path tempPath = gson.fromJson(path, Path.class);

//            String[] args = new String[] {"/bin/bash","-c","java -jar javacg-0.1-SNAPSHOT-static.jar"+tempPath.getPath()};
            DataCallGraph temp = new DataCallGraph();
            try{
//            Process proc = new ProcessBuilder(args).start();

                System.out.println(tempPath.getPath());

                System.out.println(System.getProperty("user.home"));

                System.out.println("java -jar javacg-0.1-SNAPSHOT-static.jar "+tempPath.getPath()+" > " + System.getProperty("user.home")+"\\output.txt");

                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec("java -jar javacg-0.1-SNAPSHOT-static.jar "+tempPath.getPath()+" > " + System.getProperty("user.home")+"\\output.txt");
                pr.waitFor();


                AnalyzeCalls.analyzeCallGraph(System.getProperty("user.home")+"\\output.txt",temp);

            }

            catch(InterruptedException e){
                System.out.println("Jeblo sie w api");
                e.printStackTrace();
            }
            catch(IOException e){
                System.out.println("Api sie jeblo w drugim");
                e.printStackTrace();
            }

            return gson.toJson(temp);

        }
}