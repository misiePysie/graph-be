package misiepysie.graph_be.ModuleGraph;

import misiepysie.graph_be.Data.EdgeApi;
import misiepysie.graph_be.GraphObjects.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.module.ModuleDescriptor;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@RestController
public class ModuleGraphController {
    @GetMapping("/modules")
    public Map<String, HashSet<?>> moduleInfo() {
        HashSet nodes = new HashSet<Node>(); // <1>
        HashSet edges = new HashSet<EdgeApi>(); // <2>
        fillNodeAndEdges(nodes, edges); // <3>
        return Map.of("nodes", nodes, "edges", edges); // <4>
    }
    private void fillNodeAndEdges(HashSet<Node> nodes, HashSet<EdgeApi> edges) {
        Set<Module> modules = ModuleLayer.boot().modules(); // <5>
        for (Module module : modules) {
            String moduleName = module.getName();
            if (moduleNotContain(moduleName, "jdk")) { // <6>
                nodes.add(new Node(moduleName));
            }
            Set<ModuleDescriptor.Requires> requires = module.getDescriptor().requires(); //<7>
            for (ModuleDescriptor.Requires require : requires) {
                edges.add(new EdgeApi(moduleName, require.name())); //<8>
            }
            System.out.println(module.getName().toString());
        }
    }
    private boolean moduleNotContain(String moduleName, String text) {
        return !moduleName.startsWith(text);
    }
}
