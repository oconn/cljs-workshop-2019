#! /bin/bash

set -e

CYAN='\033[1;36m'
NC='\033[0m'

print_header () {
  msg=$1

  printf "\n${CYAN}---------------------------\n${msg}\n---------------------------${NC}\n"
}

cd $(git rev-parse --show-toplevel)

# Read in user defined build environment variables
source ./.env

print_header "Linting Project"

clojure -Alint

print_header "Testing Project"

clojure -Atest

print_header "Building Project"

clojure -Abuild

./bin/build-index.sh prod

print_header "Deploying Project"

mv ./target/public/cljs-out/prod-main.js ./resources/public/js/main.js

aws s3 sync ./resources/public s3://${S3_BUCKET} \
    --include "*" \
    --exclude "*.DS_Store"

aws cloudfront create-invalidation \
    --distribution-id ${CLOUDFRONT_DISTRIBUTION_ID} \
    --paths /index.html /js/* /images/* /css/* /posts/*

# Reset the index file to use dev settings This is run for to ensure users deploying locally will
# not end up with a prod index built when running figwheel from emacs (does not call
# ./bin/build-index.sh) after deploying.
./bin/build-index.sh
