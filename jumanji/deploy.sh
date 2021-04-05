#!/bin/sh

REPOSITORY=c:\build
cd $REPOSITORY

APP_NAME=action_codedeploy
JAR_NAME=$(ls $REPOSITORY | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY\$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)