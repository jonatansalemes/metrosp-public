aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 396182636964.dkr.ecr.us-east-1.amazonaws.com/jslsolucoes/metrosp-public-bot
docker tag jslsolucoes/metrosp-public-bot:latest 396182636964.dkr.ecr.us-east-1.amazonaws.com/jslsolucoes/metrosp-public-bot:latest
docker push 396182636964.dkr.ecr.us-east-1.amazonaws.com/jslsolucoes/metrosp-public-bot:latest