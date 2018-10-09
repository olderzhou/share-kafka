# share-kafka



1.下载mongo镜像
docker pull mongo
mkdir -p ~/mongo  ~/mongo/db
cd ~/mongo

2.运行Mongo服务
docker run --name yidata_mongo -p 27017:27017 -v $PWD/db:/data/db -v $PWD/backup:/data/backup -d mongo --auth

3.链接mongo，初始化权限
docker exec -it yidata_mongo mongo admin

db.createUser( {
 user: "admin",
 pwd: "123456",
roles: [ {
            "role" : "userAdminAnyDatabase",
            "db" : "admin"
        }, 
        {
            "role" : "readWriteAnyDatabase",
            "db" : "admin"
        }, 
        {
            "role" : "dbAdminAnyDatabase",
            "db" : "admin"
        } ]
})

4.初始化yidata

use yidata
db.createUser({user:"mongouser",pwd:"5BZBQ3JrTrh",roles:["readWrite"]});

db.createCollection('user');

db.share_kafka.createIndex({'username':1});


5.windows 本地连接

mongo.exe 192.168.0.3/share_kafka
db.auth('mongouser','5BZBQ3JrTrh')