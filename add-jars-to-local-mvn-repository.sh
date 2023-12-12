#!/usr/bin/env bash

set -eu

ENDOFLIST=
set spring-data-jdbc-core-2.0.0.M1.jar:org.springframework.data:spring-data-jdbc-ext:2.0.0.M1 \
    ${ENDOFLIST}

home=$(cd && pwd)

for jar
do
    file="`pwd`/libs/$(echo $jar | cut -d ':' -f 1)"
    groupId=$(echo $jar | cut -d ':' -f 2)
    artifactId=$(echo $jar | cut -d ':' -f 3)
    version=$(echo $jar | cut -d ':' -f 4)

    if [ -f $file ]
    then
        echo Installing $file
        mvn -N install:install-file \
            -Dfile=$file \
            -DgroupId=$groupId \
            -DartifactId=$artifactId \
            -Dversion=$version \
            -Dpackaging=jar \
            -DlocalRepositoryPath=$home/.m2/repository \
            -DgeneratePom=true
    fi
done
