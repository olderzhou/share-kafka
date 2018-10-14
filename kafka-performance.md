#performance



2018-10-14 11:47:00.046  INFO 1 --- [pool-7-thread-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version : 1.0.2
2018-10-14 11:47:00.046  INFO 1 --- [pool-7-thread-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId : 2a121f7b1d402825
2018-10-14 11:47:01.678  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :1677 ms
2018-10-14 11:47:30.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : admin config is :{bootstrap.servers=[192.168.0.3:9092]}
2018-10-14 11:47:30.001  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send message started
2018-10-14 11:47:30.711  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :710 ms
2018-10-14 11:48:00.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : admin config is :{bootstrap.servers=[192.168.0.3:9092]}
2018-10-14 11:48:00.001  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send message started
2018-10-14 11:48:00.812  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :811 ms
2018-10-14 11:48:30.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : admin config is :{bootstrap.servers=[192.168.0.3:9092]}
2018-10-14 11:48:30.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send message started
2018-10-14 11:48:30.608  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :608 ms
2018-10-14 11:49:00.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : admin config is :{bootstrap.servers=[192.168.0.3:9092]}
2018-10-14 11:49:00.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send message started
2018-10-14 11:49:00.579  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :579 ms
2018-10-14 11:49:30.001  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : admin config is :{bootstrap.servers=[192.168.0.3:9092]}
2018-10-14 11:49:30.001  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send message started
2018-10-14 11:49:30.587  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :586 ms
2018-10-14 11:50:00.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : admin config is :{bootstrap.servers=[192.168.0.3:9092]}
2018-10-14 11:50:00.000  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send message started
2018-10-14 11:50:00.579  INFO 1 --- [pool-7-thread-1] c.k.m.sharekafka.ShareKafkaApplication   : send 100000 message finished,time use :579 ms

single 

much more then 100000 per second   which means QPS is greater then  100000/s
 