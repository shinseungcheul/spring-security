insert into User_Entity(uid,userid,password) values('uid1','admin','1234');
insert into User_Entity(uid,userid,password) values('uid2','user1','1234');
insert into User_Entity(uid,userid,password) values('uid3','user2','1234');
insert into User_Entity(uid,userid,password) values('uid4','user3','1234');
insert into User_Entity(uid,userid,password) values('uid5','user4','1234');


insert into User_Authority(uid,useruid,userrole) values('a1','uid1','Admin');
insert into User_Authority(uid,useruid,userrole) values('a2','uid1','User');
insert into User_Authority(uid,useruid,userrole) values('a3','uid2','User');
insert into User_Authority(uid,useruid,userrole) values('a4','uid3','User');
insert into User_Authority(uid,useruid,userrole) values('a5','uid4','User');
insert into User_Authority(uid,useruid,userrole) values('a7','uid5','Admin');
