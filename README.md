# marathon-env-plugin

Set environment variables in a JSON file and add them in the builder configuration.
These variables will be applied on every tasks and overwrite the environment variables previously set that have the same name.
It can also set labels to all tasks.

## Configuration

To configure the plugin, use the following configuration file

```
{
  "plugins": {
    "envVarExtender": {
      "plugin": "mesosphere.marathon.plugin.task.RunSpecTaskProcessor",
      "implementation": "com.criteo.marathon.CriteoEnvPlugin",
      "configuration": {
        "env": {
          "foo": "bar",
          "key": "value"
        },
        "labels": {
          "fooLabel": "bar",
          "anotherLabel": "value"
        }
      }
    }
  }
}
```

and run marathon with `--plugin_conf <your config file>` flag.
