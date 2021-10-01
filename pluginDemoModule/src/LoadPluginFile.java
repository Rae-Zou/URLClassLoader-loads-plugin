import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * implement access to PluginInterface class using a plugin-based design.
 *
 * @author Rae
 */
public class LoadPluginFile {

    private List<PluginInterface> pluginClasses = new ArrayList<>(); // store all plugins

    /**
     * Test the accessibility.
     *
     * @param args
     */
    public static void main(String[] args) {
        LoadPluginFile loadFile = new LoadPluginFile();
        List<String> names = new ArrayList<>(){};
        names.add("Plugin1");
        names.add("Plugin2");
        List<PluginInterface> lis = loadFile.loadClasses(names);
        for (PluginInterface p:lis) {
            System.out.println("Name: "+p.getName() + ", ID: "+ p.getID() + ", address: " + p.getAddress());
        }
    }

    /**
     * load Plugins with different classes name.
     *
     * @return - the list
     */
    public List<PluginInterface> loadClasses(List<String> name) {
        try {
            for(String n:name) {
                this.setClasses(n);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return pluginClasses;
    }

    /**
     * Load all Plugins from the jar file.
     * construct all Plugin objects.
     * store them in a list.
     *
     * @param name class name
     * @throws ClassNotFoundException msg
     * @throws IllegalAccessException msg
     * @throws InstantiationException msg
     */
    public void setClasses(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File jarFile = new File("pluginDemoModule/src/");
        File[] files = jarFile.listFiles(file -> file.getPath().toLowerCase().endsWith(".jar"));

        if (files != null && files.length > 0) {
            Class classToLoad = Class.forName(name, false, getClassLoader(files[0]));

            // construct the objects
            Object instance = classToLoad.newInstance();
            PluginInterface ob = (PluginInterface) instance;

            this.pluginClasses.add(ob);
        }

    }

    /**
     * get the classloader with Privileged Blocks.
     *
     * @param file input
     * @return classloader
     */
    public URLClassLoader getClassLoader(File file)  {
        URLClassLoader child = (URLClassLoader) AccessController.doPrivileged(
                new PrivilegedAction() {
                    public Object run() {
                        try {
                            return new URLClassLoader(
                                    new URL[]{file.toURI().toURL()},
                                    PluginInterface.class.getClassLoader()
                            );
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });

        return child;
    }

}
