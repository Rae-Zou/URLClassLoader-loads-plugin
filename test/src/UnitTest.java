import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

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
                Assertions.assertEquals(n, p.getName());
            }
        }
    }
}
