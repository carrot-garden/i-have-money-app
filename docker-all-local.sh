#!/usr/bin/env bash

export HOST_IP='127.0.0.1'

docker rm -f writeback writefront readback readfront
nohup ./docker-writeback.sh &
nohup ./docker-readback.sh &
nohup ./docker-readfront.sh &
nohup ./docker-writefront.sh &
