import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit Test.
 */
public class UnitTest {
    /**
     * test the accessibility.
     */
    @Test
    public void test() {
        LoadPluginFile loadFile = new LoadPluginFile();
        List<String> names = new ArrayList<>(){};
        names.add("Plugin1");
        names.add("Plugin2");
        List<PluginInterface> lis = loadFile.loadClasses(names);
        for (PluginInterface p:lis) {
            for(String n:names){
                assertEquals(n, p.getName());
            }
        }
    }
}
