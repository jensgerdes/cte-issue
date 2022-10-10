#!/bin/sh
docker run --rm -it -p 5432:5432 -e POSTGRES_PASSWORD=secure -e POSTGRES_DB=test postgres:10