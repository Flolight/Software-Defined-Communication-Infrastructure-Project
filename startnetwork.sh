#!/bin/bash
sudo xterm -T "appServer" -e "docker exec mn.appserver sh -c 'cd /workingdir/in-cse && ./start.sh'" &
sleep 4
sudo xterm -T "app" -e "docker exec mn.gi sh -c 'cd /workingdir/mn-cse && ./start.sh'" &
sleep 4
sudo xterm -T "GI" -e "docker exec mn.gi sh -c 'cd /workingdir/mn-cse && ./start.sh'" &
sleep 4
sudo xterm -T "GF1" -e "docker exec mn.gf1 sh -c 'cd /workingdir/mn-cse && ./start.sh'" &
sleep 4
sudo xterm -T "GF2" -e "docker exec mn.gf2 sh -c 'cd /workingdir/mn-cse && ./start.sh'" &
sleep 4
sudo xterm -T "GF3" -e "docker exec mn.gf3 sh -c 'cd /workingdir/mn-cse && ./start.sh'" &
sleep 4
sudo xterm -T "Datacenter" -e "docker run --priviledged -h h1 --name=mininet-h1 -ti  --net='none' ubuntu /bin/bash" &
