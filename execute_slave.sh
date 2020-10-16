#!/bin/bash
echo -e "\n\033[35;1;4mStarting slave work...\033[0m"
#echo -e "\n\033[34;1;4mGradle build...\033[0m"
#sudo gradle build
echo -e "\n\033[34;1;4mDocker build...\033[0m\n"
sudo docker build -t sdf/rps-game-2020-backend .
#echo -e "\n\033[34;1;4mHeroku Login...\033[0m\n"
#sudo heroku login
echo -e "\n\033[34;1;4mHeroku Container Login...\033[0m\n"
sudo heroku container:login
echo -e "\n\033[34;1;4mPushing image to Heroku...\033[0m\n"
sudo heroku container:push web --app thawing-oasis-43755
echo -e "\n\033[34;1;4mReleasing image to Heroku...\033[0m\n"
sudo heroku container:release web --app thawing-oasis-43755
echo -e "\n\033[34;1;4mShowing logs...\033[0m\n"
sudo heroku logs --tail --app thawing-oasis-43755
echo -e "\n\033[35;1;4mFinishing slave work...\033[0m\n"