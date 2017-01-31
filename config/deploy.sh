#!/bin/sh

# checks for env vars which are used
: ${SHAMAN_TOKEN:?"SHAMAN_TOKEN needs to be set!"}
: ${CIRCLE_BUILD_NUM:?"CIRCLE_BUILD_NUM needs to be set!"}
: ${CIRCLE_SHA1:?"CIRCLE_SHA1 needs to be set!"}

echo "******************************"
echo "*     Installing Shaman      *"
echo "******************************"

bundle install --gemfile=config/Gemfile
# clear bash PATH cache
hash -r
echo $SHAMAN_TOKEN | shaman login

echo "******************************"
echo "*         Uploading          *"
echo "******************************"

COMMIT_MSG=`git log -1 --pretty=%B`
COMMITER_NAME=`git log -1 --pretty=%cn`
CHANGELOG="$COMMIT_MSG

Deployed by: $COMMITER_NAME"

shaman deploy -c config/shaman-staging.yml -m "$CHANGELOG"
shaman deploy -c config/shaman-production.yml -m "$CHANGELOG"
