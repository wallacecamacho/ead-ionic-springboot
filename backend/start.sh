#!/bin/bash

echo "starting... hello-devops_db-mysql_1"
docker start ead-ionic-springboot_db-mysql_1
echo "starting... ead-ionic-springboot_app-ead-service"
docker start ead-ionic-springboot_app-ead-service

