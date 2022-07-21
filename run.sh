#!/bin/bash

# Use below Command when Coding in Crio Workspace. Add comment for below command during code submission on Geektrust Platform
#./gradlew clean build -x test --no-daemon
#java -jar /tmp/gradle_builds/backend/backend/libs/geektrust.jar sample_input/input1.txt

# Remove the below comment during code submission on Geektrust Platform
 gradle clean build -x test --no-daemon
 java -jar build/libs/geektrust.jar sample_input/input1.txt