#!/bin/bash
echo "Bot url base:"
read bot_url
echo "Github username:"
read username
echo "Github password:"
read -s password
docker build --rm --build-arg BOT_URL=$bot_url --build-arg GIT_USERNAME=$username --build-arg GIT_PASSWORD=$password -t jslsolucoes/metrosp-public-bot:latest .