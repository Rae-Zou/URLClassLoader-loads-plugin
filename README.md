# Use `URLClassLoader` loads `plugin` jars at runtime
After the theory behind plugin-based design, the way to loads plugin jars at runtime becomes important. Thanks to the well-developed java.net.URLClassLoader Utilities, which can be used to implement access to custom plugins.
"URL class loader is used to load classes and resources from a search path of URLs referring to both JAR files and directories." https://docs.oracle.com/javase/7/docs/api/java/net/URLClassLoader.html
## Step 1: creat the plugin interface
By having an interface to specify some behaviors. Example `PluginInterface` shown in pluginDemoModule/scr/.
## Step 2: creat your custom plugins which implement the plugin interface
Having the custom plugin classes implements the interface, we could cast the custom plugin back to PluginInterface, which made them easy to work with. Example `Plugin1` and `Plugin2` shown in pluginModule/scr/. These custom plugin classes need to be build in a seperate module which can be exported as a jar.
To creat an independent module of the project in `IntelliJ`:
  - File -> Project Structure -> Modules ->Add -> New Module -> Dependencies -> Add -> Module Dependency -> Choose `pluginDemoModule` -> Apply
  
   ![image](https://user-images.githubusercontent.com/76859781/135685153-7a85c44a-bd04-420d-9374-755d38cd6611.png)


## Step 3: produce the plugin jar file
To produce the plugin jar files of the `pluginModule` in in `IntelliJ`:
  - Build Project -> 
    ![image](https://user-images.githubusercontent.com/76859781/135685978-f21152af-f786-4f71-9884-fcf187eb331c.png)

## Step 4: load the plugin jar file

- use `Privileged` blocks catch the illegal access


