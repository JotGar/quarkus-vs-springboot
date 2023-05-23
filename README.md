# Quarkus-VS-SpringBoot

## Java frameworks comparison

To start these two projects, you need to check the following items:

- Ensure that you're using Java 17.
- Have Java JDK installed.
- Have Maven installed.

If you want to run both projects in a container for executing different tests, please ensure that you have Docker installed. You can check it by executing the following command:

```shell
docker -v
```

If Docker is installed, you should see output similar to this:

```shell
Docker version 23.0.6, build ef23cbc431
```

---

## Run and test

To start either project, load the repository folder in your preferred IDE. You can load either the root folder or the specific project folder.

Both projects have the same two services:

- trends
- trends/movies

The header \`X-MinVoteAverage\` allows you to filter by the minimum vote average.

### Spring Boot

The Spring Boot project runs on port 9001. You can use the following two cURL commands to execute a test via the terminal or import them into other tools like Postman.

**Trends**
```shell
curl --location 'http://localhost:9001/trends'
```

**Movies**
```shell
curl --location 'http://localhost:9001/trends/movies' \
--header 'X-MinVoteAverage: 0'
```

### Quarkus

The Quarkus project runs on port 8080. You can use the following two cURL commands to execute a test via the terminal or import them into other tools like Postman.

**Trends**
```shell
curl --location 'http://localhost:8080/trends'
```

**Movies**
```shell
curl --location 'http://localhost:8080/trends/movies' \
--header 'X-MinVoteAverage: 0'
```

---

## Container tests

Both projects have their respective Dockerfile configurations. To use the defined Dockerfiles, please ensure that you are in the **root directory of each project**.

### Build image

To create the Docker images, execute the following command:

```shell
docker build . -t image-name
```

This command creates an image using the Dockerfile from the root directory.

The \`-t\` parameter allows us to specify a name and optionally a tag in the \`name:tag\` format. The dot \`.\` is used to specify the Dockerfile path, which is the root folder. If you want to learn more about this command, visit: [Docker build command](https://docs.docker.com/engine/reference/commandline/build/)

To check if the image was created successfully, you can execute:

```shell
docker images
```

### Create container

To create a container from an image, run the following command:

```shell
docker create -p 9001:9001 --name container-name image-name
```

This command creates a container that allows us to check the container's memory and CPU usage.

In this command, we specify the image name and the container name. The \`-p\` parameter is used to expose a port so that we can call the container services. If you want to learn more about this command, visit: [Docker create command](https://docs.docker.com/engine/reference/commandline/create/)

### Run container 

To run the container you can use the command line executing the following command:

``` shell
  docker start container-name -a
```

This command start the container and show us the container logs thanks to **-a** param.

If you want to learn more about this command visit: https://docs.docker.com/engine/reference/commandline/start/


Other option to run your container is using the docker desktop UI, using this you can check memory and cpu using clicking over the container names also you can check that info using an extension called Tachometer that you can install in the Extensions Marketplace.

--- 

### Native image test using GraalVM

Use the commands learned before to build an image and create a container using GraalVM.

You're going to have to install GraalVM to can build the image and modify the project POM to achieve this.
