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

    @CrossOrigin(origins = "http://localhost:8080")

    @ResponseBody
    @RequestMapping(path="/dir", method = RequestMethod.POST)
    public String dirPath(@RequestBody String paths) {

        Gson gson = new Gson();
        try {
            DirectoryPath temp = gson.fromJson(paths, DirectoryPath.class);

            if(temp.getBackendSrc()==null||temp.getFrontendSrc()==null){
                throw(new IllegalArgumentException());
            }


            ArrayList<Node> tempNodes = new ArrayList<Node>();
            ArrayList<Edge> tempEdge = new ArrayList<Edge>();

            Data dataTemp = new Data(tempNodes, tempEdge);


            AnalyzeFile.listAllFilesNames(temp.getBackendSrc());
            AnalyzeFile.createNodeForEachFile();
            dataTemp.getNodesData().addAll(AnalyzeFile.getListOfNodes());

            AnalyzeFile.listAllFilesNames(temp.getFrontendSrc());
            AnalyzeFile.createNodeForEachFile();
            dataTemp.getNodesData().addAll(AnalyzeFile.getListOfNodes());


            AnalyzeFile.createEdge();
            Data.getEdgesData().addAll(AnalyzeFile.getListOfEdges());


            DataApi apiData = new DataApi(dataTemp.getNodesData(), dataTemp.getEdgesData());

            return gson.toJson(apiData);
        }
    catch(IllegalArgumentException e){
        System.out.println("Wrong argument");
        return e.getStackTrace().toString();
    }

}}