#!/usr/bin/env bash

cd ../

#export FC_DB_URL="jdbc:postgresql://localhost/fc?user=postgres&password=password"

export RDS_HOSTNAME="localhost"
export RDS_PORT="5432"
export RDS_DB_NAME="fc"
export RDS_USERNAME="postgres"
export RDS_PASSWORD="password"

export FC_GOOGLE_CLIENT_ID="563875364656-oh78elcrc4ael6vpdf06bbh77ca08732.apps.googleusercontent.com"
export FC_GOOGLE_REDIRECT_URL="http://localhost:9000"
export FC_GOOGLE_CLIENT_SECRET="ILeR4NVzLy2xSwcqDIYy1Iku"

echo "$RDS_HOSTNAME $RDS_PORT $RDS_DB_NAME $RDS_USERNAME $RDS_PASSWORD"

#echo "FC_DB_URL=$FC_DB_URL"
echo "FC_GOOGLE_CLIENT_ID=$FC_GOOGLE_CLIENT_ID"
echo "FC_GOOGLE_REDIRECT_URL=$FC_GOOGLE_REDIRECT_URL"
echo "FC_GOOGLE_CLIENT_SECRET=$FC_GOOGLE_CLIENT_SECRET"

sbt clean -jvm-debug 9990 run

# docker run
# -e FC_DB_URL="jdbc:postgresql://horton.elephantsql.com:5432/bueoqxzn?user=bueoqxzn&password=TwENJrc1Acp1RW-hYKWHp3-dDoCciYdL"
# -e FC_GOOGLE_CLIENT_ID="5638753646-oh78elcrc4ael6vpdf06bbh77ca08732.apps.googleusercontent.com"
# -e FC_GOOGLE_REDIRECT_URL="http://localhost:9000"
# -e FC_GOOGLE_CLIENT_SECRET="ILeR4NVzLy2xSwcqDIYy1Iku"
# -p 9000:9000 200b0ffd584d

# psql -h testfc100.cejd7wkwcswk.us-west-2.rds.amazonaws.com -p 5432  -U postres postgres
# psql -h aatrst67n4c4fq.cejd7wkwcswk.us-west-2.rds.amazonaws.com -p 5432  -U postgres postgres