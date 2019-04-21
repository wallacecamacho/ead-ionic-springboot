#!/bin/bash

echo "stoping... hello-devops_db-mysql_1"
docker stop hello-devops_db-mysql_1
echo "stoping... hello-devops_rabbitmq_1"
docker stop hello-devops_rabbitmq_1
echo "stoping... hello-devops_app-python_1"
docker stop hello-devops_app-python_1
echo "stoping... hello-devops_app-node_1"
docker stop hello-devops_app-node_1


