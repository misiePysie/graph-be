package misiepysie.graph_be;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class GraphApplicationController {

    @ResponseBody
    @RequestMapping(path="/dir", method = RequestMethod.GET)
    public String dirPath(@RequestBody String paths){

        Gson gson = new Gson();

      DirectoryPath directory = gson.fromJson(paths,DirectoryPath.class);

//       System.out.println(directory.getBackendSrc()+'\n'+directory.getFrontendSrc());
        Nodes node1 = new Nodes("./src/1","./src/java/lorem/ipsum","1","1234");
        Nodes node2 = new Nodes("./src/2","Node 2","15","1234:");
        Nodes node3 = new Nodes("./src/3","Node 3","50","12114");

        Edge edge1 = new Edge("./src/1","./src/3",1);
        Edge edge2 = new Edge("./src/1","./src/2",1);
        Edge edge3 = new Edge("./src/3","./src/3",7);

        ArrayList<Nodes> nodesData= new ArrayList<Nodes>();
        nodesData.add(node1);
        nodesData.add(node2);
        nodesData.add(node3);

        ArrayList<Edge> edgesData= new ArrayList<Edge>();
        edgesData.add(edge1);
        edgesData.add(edge2);
        edgesData.add(edge3);

        Data data = new Data(nodesData,edgesData);


        return gson.toJson(data);
    }
}