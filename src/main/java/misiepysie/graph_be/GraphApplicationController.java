package misiepysie.graph_be;

import com.google.gson.Gson;
import misiepysie.graph_be.Data.Data;
import misiepysie.graph_be.Data.DataApi;
import misiepysie.graph_be.Data.DirectoryPath;
import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class GraphApplicationController {

    @CrossOrigin(origins = "http://localhost:3030")

    @ResponseBody
    @RequestMapping(path="/dir", method = RequestMethod.GET)
    public String dirPath(@RequestBody String paths){

        Gson gson = new Gson();

        DirectoryPath temp = gson.fromJson(paths,DirectoryPath.class);

        ArrayList<Node> tempNodes = new ArrayList<Node>();
        ArrayList<Edge> tempEdge = new ArrayList<Edge>();

        Data dataTemp = new Data(tempNodes,tempEdge);

//      GraphApplication.directory.setBackendSrc(temp.getBackendSrc());
//      GraphApplication.directory.setFrontendSrc(temp.getFrontendSrc());
        AnalyzeFile.listAllFilesNames(temp.getBackendSrc());
        AnalyzeFile.createNodeForEachFile();
        dataTemp.getNodesData().addAll(AnalyzeFile.getListOfNodes());
//         System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));

        AnalyzeFile.listAllFilesNames(temp.getFrontendSrc());
        AnalyzeFile.createNodeForEachFile();
        dataTemp.getNodesData().addAll(AnalyzeFile.getListOfNodes());
//        System.out.println(Arrays.toString(dataTemp.getNodesData().toArray()));
//        System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));


        AnalyzeFile.createEdge();
        Data.getEdgesData().addAll(AnalyzeFile.getListOfEdges());

//        System.out.println(dataTemp.getNodesData());
//        System.out.println(dataTemp.getEdgesData());

        DataApi apiData = new DataApi(dataTemp.getNodesData(),dataTemp.getEdgesData());

       return gson.toJson(apiData);
    }


}