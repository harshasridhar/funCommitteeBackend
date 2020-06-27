cd ..
mvn clean package -DskipTests=true
cd deployScripts
cp ../target/committee-0.0.1-SNAPSHOT.jar .
sh prepareDB.sh test
mkdir db-data
mysqldump -uroot -proot funCommitteeBackend > db-data/dump.sql