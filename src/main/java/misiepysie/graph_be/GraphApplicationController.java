package misiepysie.graph_be;

import org.springframework.web.bind.annotation.*;


@RestController
public class GraphApplicationController {

    @RequestMapping(value = "/dir", method = RequestMethod.POST)
        public String dirPath(@RequestHeader(value="path")String path){
        GraphApplication.directoryPath = path;

        return GraphApplication.directoryPath;
    }
}
