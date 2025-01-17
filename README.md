# Quick Start Guide
> Always see [official documentation](https://nightlies.apache.org/flink/flink-docs-release-1.19/docs/try-flink/local_installation/) for the most up-to-date information.

## Install Flink on MacOS

1. Verify Java installation
    ```bash
    java -version
    ```
    If Java is not installed, install it using
    ```bash
    brew install openjdk@11
    ```
2. Download the [binary file](https://flink.apache.org/downloads/)
3. Navigate to the directory of the binary file and extract
    ```bash
    tar -xzf flink-*.tgz
    ```
4. Set the environment variable of `FLINK_HOME` to the directory of the extracted file
    ```bash
    export FLINK_HOME='/your/path/to/flink'
    ```
    You can add this to your ~/.bash_profile` or `~/.zshrc` file to make it permanent.
    
    Verify if the environment variable is pernamnetly set by opening a new session and running
    ```bash
    echo $FLINK_HOME
    ```
See detailed documentation: [Flink Setup process for MacOS](https://nightlies.apache.org/flink/flink-cdc-docs-master/docs/deployment/standalone/#:~:text=Flink%20runs%20on%20all%20UNIX,and%20Cygwin%20(for%20Windows))

## Start Flink Cluster
To start the Flink cluster, run the following command
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

## Run program
After starting Flink cluster, run the following command in `frauddetection` folder to run the program
```bash
mvn clean package
```
`clean` removes the `target` directory and `package` compiles the program and creates a jar file in the `target` directory. `package` phase includes the `compile` phase in the [Maven lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).

Test to see if the program compiled successfully
```bash
mvn exec:java
```

Go to `$FLINK_HOME` and run the following command to run the program
```bash
./bin/flink run -c spendreport.FraudDetection target/flink-fraud-detection-0.1.jar
```
This submits the jar file to the Flink cluster and runs the program.

## Flink Dashboard UI
The following image shows the Flink UI while running the program the program. The Web UI is accessible at `http://localhost:8081/`.

![alt text](./concepts/figures/running_program.png)