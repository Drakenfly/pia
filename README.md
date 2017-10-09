# PIA - Project Initialization Assistant

## PIA Core
PIA was created to ease the process of setting up new projects. It allows to write plugins which are loaded into the PIA core and extend the functionality.

A plugin is designed to create a subproject, link subprojects together or extend them.

Each plugin offers properties to configure the creation process (this may be a package name for a java project creator, for example).

After configuration phase, the core wires everything together and executes all desired plugins.

## Plugins
To write a plugin, simply create a class that extends `Plugin` and is annotated with `@PluginMetadata(...)`.
Properties that should be entered by the end user are annotated with `@Property`

```java
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.PluginMetadata;
import com.pia.core.annotaion.Property;

@PluginMetadata(name = "My fancy plugin name")
public class MyTestPlugin extends Plugin {
    @Property()
    private String setByGUI;
}
```

## PIA GUI
The provided JavaFX GUI searches all `.jar`-files that are in a `plugins`-directory next to the executable for valid plugins.
If offers the user to select a list of plugins that should be executed und provides an easy way to set all properties.

- Place the plugins in the correct folder:
    ```
    |--plugins
       |-- plugin-library-1.jar
       |-- plugin-library-2.jar
    gui-version.jar
    ```
- Start the GUI: `java -jar gui-version.jar`