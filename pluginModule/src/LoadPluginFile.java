import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class LoadPluginFile {

    private List<PluginInterface> pluginClasses = new ArrayList<>(); // store all bugs

    /**
     * get all the plugin classes from jar.
     *
     * @return a list of classes
     */
    public List<PluginInterface> getEnemyClasses(){
        return this.pluginClasses;
    }

    /**
     * Load all enemy from the jar file.
     * construct all enemy objects with associated positions.
     * store them in enemyClasses list.
     *
     * @param names list of name
     * @throws MalformedURLException msg
     * @throws ClassNotFoundException msg
     * @throws IllegalAccessException msg
     * @throws InstantiationException msg
     */
    public void setEnemyClasses(List<String> names, String name) throws MalformedURLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        File jarFile = new File("src/nz/ac/vuw/ecs/swen225/gp21/persistency/levels");
        File[] files = jarFile.listFiles(file -> file.getPath().toLowerCase().endsWith(".jar"));

        assert files != null;

        Class classToLoad = Class.forName(name, false, getClassLoader(files[0]));

        // set each with object corresponding position
        for (String n : names) {
            Object instance = classToLoad.newInstance();
            PluginInterface act = (PluginInterface) instance;
            this.pluginClasses.add(act);
        }

    }

    /**
     * get the classloader with Privileged Blocks.
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
