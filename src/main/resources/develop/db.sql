DROP SEQUENCE user_seq ;
DROP SEQUENCE user_role_seq ;
DROP SEQUENCE role_seq ;
DROP SEQUENCE role_permission_seq ;

CREATE SEQUENCE user_seq NOCACHE ;
CREATE SEQUENCE user_role_seq NOCACHE ;
CREATE SEQUENCE role_seq NOCACHE ;
CREATE SEQUENCE role_permission_seq NOCACHE ;

DROP TABLE shiro_role_permission PURGE ;
DROP TABLE shiro_user_role  PURGE  ;
DROP TABLE shiro_user PURGE ;
DROP TABLE shiro_role PURGE  ;
DROP TABLE shiro_permission PURGE ;



CREATE TABLE shiro_permission(
        permission_id NUMBER(4)   ,
	permission_name VARCHAR2(50)  ,
	permission_note VARCHAR2(50) ,
        CONSTRAINT pk_permission_id PRIMARY KEY(permission_id)
)   ;
CREATE TABLE shiro_role(
       role_id   NUMBER(4)    ,
       role_name VARCHAR2(50)  ,
       CONSTRAINT pk_role_id PRIMARY KEY(role_id)   
)  ;
CREATE TABLE shiro_role_permission(
        role_permission_id   NUMBER(4)   ,
	role_id   NUMBER(4)   ,
	permission_id NUMBER(4)  ,
	CONSTRAINT pk_role_permission_id PRIMARY KEY(role_permission_id)  ,
	CONSTRAINT fk_role_id_role_permission FOREIGN KEY(role_id) REFERENCES shiro_role(role_id)  ,
	CONSTRAINT fk_permission_id FOREIGN KEY(permission_id) REFERENCES shiro_permission(permission_id)
)   ;
CREATE TABLE shiro_user(
     user_id   NUMBER(4)   ,
     user_name  VARCHAR2(50)   ,
     password  VARCHAR2(50)   ,
     create_time  DATE   ,
     update_time DATE   ,
     last_login DATE    ,
     CONSTRAINT pk_user_id  PRIMARY KEY(user_id)  ,
     CONSTRAINT uk_user_name  UNIQUE(user_name)
)  ;
CREATE TABLE shiro_user_role (
         user_role_id  NUMBER(4)   ,
         user_id  NUMBER(4)   ,
	 role_id  NUMBER(4)  ,
	 CONSTRAINT pk_user_role_id  PRIMARY KEY(user_role_id) ,
	 CONSTRAINT fk_user_id  FOREIGN KEY(user_id) REFERENCES shiro_user(user_id),
	 CONSTRAINT fk_role_id_user_role   FOREIGN KEY(role_id) REFERENCES shiro_role(role_id)
)  ;


---数据模块建表待开发中....


INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(1,'Student:INSERT','增加学生')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(2,'Student:DELETE','删除学生')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(3,'Student:SELECT','查询学生')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(4,'Student:UPDATE','修改学生')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(5,'Teacher:INSERT','增加教师')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(6,'Teacher:DELETE','删除教师')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(7,'Teacher:SELECT','查询教师')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(8,'Teacher:UPDATE','修改教师')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(9,'User:INSERT','增加用户')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(10,'User:DELETE','删除用户')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(11,'User:SELECT','查询用户')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(12,'User:UPDATE','修改用户')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(13,'Course:INSERT','增加课程')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(14,'Course:DELETE','删除课程')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(15,'Course:SELECT','查询课程')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(16,'Course:UPDATE','修改课程')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(17,'Major:INSERT','增加专业')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(18,'Major:DELETE','删除专业')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(19,'Major:SELECT','查询专业')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(20,'Major:UPDATE','修改专业')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(21,'Score:INSERT','增加分数')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(22,'Score:DELETE','删除分数')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(23,'Score:SELECT','查询分数')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(24,'Score:UPDATE','修改分数')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(25,'Clazz:INSERT','增加班级')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(26,'Clazz:DELETE','删除班级')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(27,'Clazz:SELECT','查询班级')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(28,'Clazz:UPDATE','修改班级')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(29,'Role:INSERT','增加角色')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(30,'Role:DELETE','删除角色')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(31,'Role:SELECT','查询角色')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(32,'Role:UPDATE','修改角色')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(33,'Permission:SELECT','查询权限')   ;



INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(34,'Teach:INSERT','增加教学')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(35,'Teach:DELETE','删除教学')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(36,'Teach:SELECT','查询教学')   ;
INSERT INTO shiro_permission(permission_id,permission_name,permission_note) VALUES(37,'Teach:UPDATE','修改教学')   ;







INSERT INTO shiro_role(role_id,role_name) VALUES(role_seq.nextVal,'SuperAdmin')  ;
INSERT INTO shiro_role(role_id,role_name) VALUES(role_seq.nextVal,'Assist')  ;

INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,1)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,2)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,3)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,4)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,5)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,6)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,7)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,8)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,9)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,10)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,11)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,12)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,13)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,14)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,15)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,16)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,17)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,18)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,19)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,20)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,21)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,22)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,23)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,24)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,25)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,26)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,27)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,28)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,29)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,30)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,31)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,32)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,2,33)    ;

INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,3)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,7)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,11)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,15)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,19)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,23)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,27)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,31)    ;
INSERT INTO shiro_role_permission(role_permission_id,role_id,permission_id) VALUES(role_permission_seq.nextVal,3,33)    ;




--课程表的脚本段
DROP SEQUENCE course_seq ;
DROP TABLE course ;
CREATE SEQUENCE course_seq NOCACHE ;
CREATE TABLE course(
     course_id  NUMBER(4)    ,
    course_name VARCHAR2(50)  ,
    note         CLOB       ,
    term        NUMBER(4)   ,
    credit       NUMBER(4)  ,
   CONSTRAINT pk_course_id  PRIMARY KEY(course_id)
)  ;

INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'高数','很多人都挂在这棵树上',1,5)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'英语','计算机专业的英语不好吃翔去吧',1,3)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'体育','程序员就是体育课要多锻炼',1,2)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'Java','我的第一个Java老师是李学相',1,5)  ;
       
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'思修','品德败坏的大学牲一堆堆的还不如农民工',2,1)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'网页设计','设计师搞得好,马云少不了',2,4)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'语文','这真他妈是我最恶心的一门课',2,1)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'Linux','我最后悔没好好学习Linux',2,5)  ;
       
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'数据结构','科班出身的素养',3,5)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'计算机网络','会Ping就行了',3,5)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'操作系统','老师上课真扯淡',3,4)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'组原','沃日我是一点都没听懂',3,2)  ;
       
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'Oracle','程序员必须会的',4,5)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'JSP','老师的课我只记住了一句话',4,8)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'金融学概论','考试会抄就行,无意义',4,1)  ;
INSERT INTO course(course_id,course_name,note,term,credit)
       VALUES(course_seq.NEXTVAL,'会计','学了你也当不了会计',4,3)  ;

--创建专业表
DROP TABLE major  ;
DROP SEQUENCE major_seq ;
CREATE SEQUENCE major_seq NOCACHE   ;
CREATE TABLE major(
      major_id   NUMBER(4)   ,
      major_name VARCHAR2(100)   ,
      note       CLOB   ,
      create_time DATE    ,
      CONSTRAINT pk_major_id  PRIMARY KEY(major_id) 
)  ;
INSERT INTO major(major_id,major_name,note,create_time) VALUES(major_seq.NEXTVAL,'金融信息化','大金融好威武啊啊啊啊',SYSDATE) ;
INSERT INTO major(major_id,major_name,note,create_time) VALUES(major_seq.NEXTVAL,'Java','自古Java专业出垃圾',SYSDATE) ;


--班级表
DROP TABLE clazz  ;
DROP SEQUENCE clazz_seq  ;
CREATE SEQUENCE clazz_seq NOCACHE  ;
CREATE TABLE clazz(
     clazz_id   NUMBER(4)   ,
     clazz_name  VARCHAR2(100)   ,
     major_id    NUMBER(4)   ,
     CONSTRAINT  pk_clazz_id PRIMARY KEY(clazz_id),
     CONSTRAINT  fk_major_id  FOREIGN KEY(major_id) REFERENCES major(major_id)
)  ;
INSERT INTO clazz(clazz_id,clazz_name,major_id)VALUES(clazz_seq.NEXTVAL,'14金融1班',2)  ;
INSERT INTO clazz(clazz_id,clazz_name,major_id)VALUES(clazz_seq.NEXTVAL,'14金融2班',2)  ;
INSERT INTO clazz(clazz_id,clazz_name,major_id)VALUES(clazz_seq.NEXTVAL,'14金融3班',2)  ;
INSERT INTO clazz(clazz_id,clazz_name,major_id)VALUES(clazz_seq.NEXTVAL,'14Java1班',3)  ;
INSERT INTO clazz(clazz_id,clazz_name,major_id)VALUES(clazz_seq.NEXTVAL,'14Java2班',3)  ;
INSERT INTO clazz(clazz_id,clazz_name,major_id)VALUES(clazz_seq.NEXTVAL,'14Java3班',3)  ;

--学生表
DROP TABLE student  ;
DROP SEQUENCE student_seq    ;
CREATE SEQUENCE student_seq NOCACHE ;
CREATE TABLE student(
       student_id  VARCHAR2(100)   ,
       clazz_id    NUMBER(4)    ,
       student_name  VARCHAR2(50)   ,
       sex           VARCHAR2(10)    ,
       age          NUMBER(4)   ,
       address      VARCHAR2(150)   ,
       birth        DATE       ,
       password     VARCHAR2(50)   ,
       tel          VARCHAR2(50)   ,
       photo        VARCHAR2(100)   ,
       note         CLOB            ,
       CONSTRAINT pk_student_id   PRIMARY KEY(student_id)   ,
       CONSTRAINT fk_clazz_id  FOREIGN KEY(clazz_id) REFERENCES clazz(clazz_id)
)   ;


--分数表
DROP SEQUENCE score_seq ;
CREATE SEQUENCE score_seq NOCACHE  ;
DROP TABLE score  ;
CREATE TABLE score(
          score_id     NUMBER(8),
          student_id   varchar2(50)   ,
          course_id    NUMBER(4)     ,
          score        NUMBER(4)     ,
          CONSTRAINT pk_score_id PRIMARY KEY(score_id)  ,
          CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES student(student_id)  ,
          CONSTRAINT fk_course_id FOREIGN KEY(course_id) REFERENCES course(course_id)
)   ;

--教师表
DROP TABLE teacher   ;
DROP SEQUENCE teacher_seq ;
CREATE SEQUENCE teacher_seq NOCACHE   ;
CREATE TABLE teacher(
      teacher_id     NUMBER(4)    ,
      teacher_name   varchar2(50)   ,
      CONSTRAINT pk_teacher_id  PRIMARY KEY(teacher_id)
)  ;

--教学表
DROP TABLE teach   ;
DROP SEQUENCE teach_seq  ;
CREATE SEQUENCE teach_seq NOCACHE  ;
CREATE TABLE teach(
      teach_id   NUMBER(4)    ,
      clazz_id   NUMBER(4)   ,
      course_id  NUMBER(4)    ,
      teacher_id NUMBER(4)    ,
      CONSTRAINT pk_teach_id PRIMARY KEY(teach_id)  ,
      CONSTRAINT fk_clazz_id_teach FOREIGN KEY(clazz_id) REFERENCES clazz(clazz_id)  ,
      CONSTRAINT fk_course_id_teach FOREIGN KEY(course_id) REFERENCES course(course_id)  ,
      CONSTRAINT fk_teacher_id_teach FOREIGN KEY(teacher_id) REFERENCES teacher(teacher_id),
      CONSTRAINT uk_teach UNIQUE(clazz_id,course_id,teacher_id)
)  ;



--数据模块清表语句,按需执行!
DROP TABLE score PURGE ;
DROP TABLE student PURGE  ;
DROP SEQUENCE student_seq ;
DROP TABLE teach  PURGE  ;
DROP TABLE  teacher PURGE ;
DROP SEQUENCE teacher_seq ;
DROP TABLE clazz PURGE  ;
DROP SEQUENCE clazz_seq  ;
DROP TABLE major  PURGE ;
DROP SEQUENCE major_seq ;


       
       
       



