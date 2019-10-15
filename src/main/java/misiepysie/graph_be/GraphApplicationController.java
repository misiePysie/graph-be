package misiepysie.graph_be;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class GraphApplicationController {

    @CrossOrigin(origins = "http://localhost:3030")

    @ResponseBody
    @RequestMapping(path="/dir", method = RequestMethod.GET)
    public String dirPath(@RequestBody String paths){

        Gson gson = new Gson();

      DirectoryPath directory = gson.fromJson(paths,DirectoryPath.class);


       return "hej";
    }


}