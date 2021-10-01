# Use `URLClassLoader` loads `plugin` jars at runtime
After the theory behind plugin-based design, the way to loads plugin jars at runtime becomes important. Thanks to the well-developed java.net.URLClassLoader Utilities, which can be used to implement access to custom plugins.
"URL class loader is used to load classes and resources from a search path of URLs referring to both JAR files and directories." https://docs.oracle.com/javase/7/docs/api/java/net/URLClassLoader.html
## Step 1: creat the plugin interface
By having an interface to specify some behaviors. Example `PluginInterface` shown in pluginModule/scr/.
## Step 2: creat your custom plugins which implement the plugin interface
Having the custom plugin classes implements the interface, so we could cast the custom plugin back to PluginInterface, which made them easy to work with.
## Step 3: produce the plugin jar file

## Step 4: load the plugin jar file

- use `Privileged` blocks catch the illegal access


