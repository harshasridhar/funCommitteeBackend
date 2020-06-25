echo 'Deleting local database...';
echo 'drop database funcommitteebackend;'| mysql -uroot -proot
echo 'Creating local database funcommitteebackend...'
echo 'create database funcommitteebackend;'| mysql -uroot -proot
echo 'Adding Tables to database...'
mysql -uroot -proot funcommitteebackend < v1/schema.sql