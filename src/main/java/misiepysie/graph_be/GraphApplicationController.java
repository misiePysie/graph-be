package misiepysie.graph_be;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;


@RestController
public class GraphApplicationController {

    @ResponseBody
    @RequestMapping(path="/dir", method = RequestMethod.GET)
    public String dirPath(@RequestBody String paths){

        Gson gson = new Gson();

      DirectoryPath directory = gson.fromJson(paths,DirectoryPath.class);

        String test = "Hejka testujemy";

        System.out.println(directory.getBackendSrc()+'\n'+directory.getFrontendSrc());
        return gson.toJson(test);
    }
}