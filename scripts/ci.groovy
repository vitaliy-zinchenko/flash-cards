stage "Build maven & Docker image"
sh "mvn clean package -P build-docker-image,qa,build-ui"

stage "Saving Docker image to tar"
sh "docker save flashcards > flashcards.tar"

stage "Copy tar to QA"
sh "scp flashcards.tar root@104.236.116.198:/tmp"
sh "scp docker/docker-compose.yml root@104.236.116.198:/tmp"

stage "Loading Docker image"
sh "ssh root@104.236.116.198 'docker load < /tmp/flashcards.tar'"

stage "Starting QA"
sh "ssh root@104.236.116.198 'cd /tmp && export FC_PORT=80 && export FC_PROFILE=qa && docker-compose -p flashcards_qa up -d --force-recreate'"

stage "Starting DEV"
sh "ssh root@104.236.116.198 'cd /tmp && export FC_PORT=9000 && export FC_PROFILE=dev && docker-compose -p flashcards_dev up -d --force-recreate'"
