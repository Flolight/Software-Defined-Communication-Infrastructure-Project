#Let's config the address
sudo sed -i "s/configaddress/`hostname -i | awk '{print $1}'`/g" /workingdir/mn-cse/configuration/config.ini
#And now the name and id
sudo sed -i "s/configbaseid\|configbasename/$1/g" /workingdir/mn-cse/configuration/config.ini
cd /workingdir/mn-cse && ./start.sh
