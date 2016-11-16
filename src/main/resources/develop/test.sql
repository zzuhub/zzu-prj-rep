--测试语句
SELECT S1.user_name,S3.role_name 
FROM shiro_user S1,shiro_user_role S2,shiro_role S3
WHERE S1.user_id=S2.user_id AND S2.role_id=S3.role_id ; 


---统计专业人数
SELECT COUNT(student_id)
FROM  student 
WHERE clazz_id IN(
            SELECT clazz_id
            FROM clazz
            WHERE major_id=(
                     SELECT major_id
                     FROM major
                     WHERE major_id=(
                            SELECT major_id
                            FROM clazz
                            WHERE clazz_id=(
                                  SELECT clazz_id
                                  FROM student
                                  WHERE student_id='2014888'
                            )
                     )
            )
)  ;

--统计专业排名(学分*成绩进行核算)
--1.T1
SELECT S.student_id sid,ROUND(SUM((C.credit*S.score)/100)) gpa
FROM score S,course C
WHERE S.score>=60 
         AND
      S.course_id=C.course_id
GROUP BY S.student_id  ;

--T2
SELECT student_id ,student_name
FROM  student 
WHERE clazz_id IN(
            SELECT clazz_id
            FROM clazz
            WHERE major_id=(
                     SELECT major_id
                     FROM major
                     WHERE major_id=(
                            SELECT major_id
                            FROM clazz
                            WHERE clazz_id=(
                                  SELECT clazz_id
                                  FROM student
                                  WHERE student_id='2014888'
                            )
                     )
            )
)  ;

--整合T1,T2
SELECT  T1.sid , T1.gpa
FROM  (
        SELECT S.student_id sid,ROUND(SUM((C.credit*S.score)/100)) gpa
		FROM score S,course C
		WHERE S.score>=60 
		         AND
		      S.course_id=C.course_id
		GROUP BY S.student_id  
     ) T1  ,
     (
        SELECT student_id ,student_name
		FROM  student 
		WHERE clazz_id IN(
		            SELECT clazz_id
		            FROM clazz
		            WHERE major_id=(
		                     SELECT major_id
		                     FROM major
		                     WHERE major_id=(
		                            SELECT major_id
		                            FROM clazz
		                            WHERE clazz_id=(
		                                  SELECT clazz_id
		                                  FROM student
		                                  WHERE student_id='2014888'
		                            )
		                     )
		            )
		     )   
      ) T2
WHERE T1.sid=T2.student_id 
ORDER BY gpa DESC ;

--再次使用子查询封装，查找到排名
SELECT tmp.rn
FROM  (
        SELECT ROWNUM rn,temp.*
        FROM (
				SELECT  T1.sid , T1.gpa
				FROM  (
				        SELECT S.student_id sid,ROUND(SUM((C.credit*S.score)/100)) gpa
						FROM score S,course C
						WHERE S.score>=60 
						         AND
						      S.course_id=C.course_id
						GROUP BY S.student_id  
				     ) T1  ,
				     (
				        SELECT student_id ,student_name
						FROM  student 
						WHERE clazz_id IN(
						            SELECT clazz_id
						            FROM clazz
						            WHERE major_id=(
						                     SELECT major_id
						                     FROM major
						                     WHERE major_id=(
						                            SELECT major_id
						                            FROM clazz
						                            WHERE clazz_id=(
						                                  SELECT clazz_id
						                                  FROM student
						                                  WHERE student_id='2014888'
						                            )
						                     )
						            )
						     )   
				      ) T2
				WHERE T1.sid=T2.student_id 
				ORDER BY gpa DESC 
            ) temp  
       )tmp
WHERE tmp.sid='2014888'   ;

--每学期总学分条形图
---1.每学期总学分
SELECT term,SUM(credit) sum
FROM score S , course C
WHERE S.course_id=C.course_id AND S.student_id='2014888'
GROUP BY term
---2.每学期修够的学分
SELECT term,SUM(credit) sum_pass
FROM score S , course C
WHERE S.course_id=C.course_id AND S.student_id='2014888' AND score>=60
GROUP BY term
--整合SQL语句
SELECT T1.term,NVL(T2.sum_pass,0),T1.sum 
FROM (
        SELECT term,SUM(credit) sum
		FROM score S , course C
		WHERE S.course_id=C.course_id AND S.student_id='2014888'
		GROUP BY term
     ) T1  ,
     (
        SELECT term,SUM(credit) sum_pass
		FROM score S , course C
		WHERE S.course_id=C.course_id AND S.student_id='2014888' AND score>=60
		GROUP BY term
     )  T2
WHERE T1.term=T2.term(+) ;

