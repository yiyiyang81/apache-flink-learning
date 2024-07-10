# Quick Start Guide to Flink
See detailed documentation: [Flink Setup process for MacOS](https://nightlies.apache.org/flink/flink-cdc-docs-master/docs/deployment/standalone/#:~:text=Flink%20runs%20on%20all%20UNIX,and%20Cygwin%20(for%20Windows))

## Flink Setup process for MacOS
> Always see official documentation for the most up-to-date information.
1. Download the binary file
2. navigate to the directory of the binary file and extract
3. Set the environment variable of `FLINK_HOME` to the directory of the extracted file
4. Start the Flink cluster by running
    ```bash
    cd $FLINK_HOME
    ./bin/start-cluster.sh
    ```

To verify the status of the cluster, navigate to `http://localhost:8081/` in your browser or run
```bash
$ ps aux | grep flink
```

To stop the cluster, run
```bash
$ ./bin/stop-cluster.sh
```
