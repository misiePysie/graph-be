package misiepysie.graph_be;

import com.google.gson.Gson;
import misiepysie.graph_be.Callgraph.AnalyzeCalls;
import misiepysie.graph_be.Callgraph.DataCallGraph;
import misiepysie.graph_be.Callgraph.EdgeMethod;
import misiepysie.graph_be.Callgraph.Path;
import misiepysie.graph_be.Data.Data;
import misiepysie.graph_be.Data.DataApi;
import misiepysie.graph_be.Data.DirectoryPath;
import misiepysie.graph_be.Data.EdgeApi;
import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;
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
           ArrayList<String> tempToMethods = new ArrayList<String>();
           ArrayList<String> tempFromMethods = new ArrayList<String>();
           ArrayList<EdgeMethod> tempEdgeMethod = new ArrayList<EdgeMethod>();

            DataCallGraph temp = new DataCallGraph(tempToMethods,tempFromMethods,tempEdgeMethod);
            try{

                File output = new File(System.getProperty("user.home")+File.separator+"output.txt");

                ProcessBuilder pb=new ProcessBuilder("java", "-jar", System.getProperty("user.dir")+File.separator+"javacg-0.1-SNAPSHOT-static.jar",tempPath.getPath());
                pb.redirectErrorStream(false);
                pb.redirectOutput(output);

                Process process=pb.start();

                process.waitFor();

                AnalyzeCalls.analyzeCallGraph(System.getProperty("user.home")+File.separator+"output.txt",temp);
            }

            catch(InterruptedException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return temp.toString();

        }
}