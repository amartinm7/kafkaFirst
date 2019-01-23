![kafka](./_media/kafka-logo.png)

# Kafka PoC

### Obtain the ip of the localhost
```bash
ifconfig
...
wlp4s0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.1.58  netmask 255.255.255.0  broadcast 192.168.1.255
```
In this case the ip is 192.168.1.58. This Ip will be used in the next sections.
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)



### run zookeeper
docker run -d --name zookeeper -p 2181:2181 jplock/zookeeper

### run kafka
docker run -d --name kafka -p 7203:7203 -p 9092:9092 -e KAFKA_ADVERTISED_HOST_NAME=192.168.1.58 -e ZOOKEEPER_IP=192.168.1.58 ches/kafka

### Create topic
docker run \
--rm ches/kafka kafka-topics.sh \
--create \
--topic senz \
--replication-factor 1 \
--partitions 1 \
--zookeeper 192.168.1.58:2181

### List topics
docker run \
--rm ches/kafka kafka-topics.sh \
--list \
--zookeeper 192.168.1.58:2181

### Create publisher
docker run --rm --interactive \
ches/kafka kafka-console-producer.sh \
--topic senz \
--broker-list 192.168.1.58:9092

### Create consumer
docker run --rm \
ches/kafka kafka-console-consumer.sh \
--topic senz \
--from-beginning \
--zookeeper 192.168.1.58:2181


### Go inside Zookeeper container

docker exec -it zookeeper bash

### Connect to Zookeeper server
bin/zkCli.sh -server 127.0.0.1:2181

### List root
ls /

###  List brokers
ls /brokers

### List topics
ls /brokers/topics

### List consumers
ls /consumers

### List consumer owner
ls /consumers/console-consumer-1532/owners


## ![docker](./_media/icons/docker.png) Dockerize the app
From the project root folder exec the commands to create a docker image and run it:

for macosx start the docker daemon
```bash
killall Docker && open /Applications/Docker.app
```

then execute the next commands in order to create the docker image and run it:
```bash
docker build -f docker/Dockerfile . -t friends
docker run -p 8080:8080 friends
```

to stop the application first we have to stop the docker process and then kill the docker process:
```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean the docker images from the system:
```bash
docker images
docker rmi PID
``` 

Once you have the dockerized app is really easy bring it to the cloud. You look for a cloud provider to deploy it and host it.