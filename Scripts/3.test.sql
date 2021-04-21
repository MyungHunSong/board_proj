INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');

select max(board_num) from board;
select * from board;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(25, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0);

-- listCount 구하기
select count(*) from board;
select * from board;
-- list
-- (page-1)*10 -> 1 page 0, 2 page -> 10, 3 page ->20
select BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,BOARD_CONTENT,BOARD_FILE
	,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE
 from board;
 order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
 limit 0,10;
 


select BOARD_READCOUNT from board;

desc board;

select * from board where BOARD_NUM;

update board set BOARD_READCOUNT = BOARD_READCOUNT +1 where BOARD_NUM = 10;

delete from board where BOARD_NUM =21;

update board set BOARD_READCOUNT = BOARD_READCOUNT+1  
where BOARD_NUM = 15;

select * from board where BOARD_NUM =26;

-- 글 삭제
delete 
from board 
where BOARD_NUM =22;

select * from board;
-- 패스워드 비교
select 1 from board where BOARD_NUM =25 and BOARD_PASS ='0111';

select * from board;
desc board;

-- 수정
update board set BOARD_SUBJECT ='정글북킹르반', BOARD_CONTENT ='ㅅㅂ탑새끼 텔안타ㄴㅑ?'
where BOARD_NUM =22;

-- 리플라이.
insert into board(BOARD_NUM , BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ) values
	(31,'캐뤼미','1234','고자라니','수술받아야합니까?','',31,23,1,1);
	
-- 리플라이 업데이트 레벨에 +1
update board set BOARD_RE_LEV =BOARD_RE_SEQ +1
where BOARD_RE_REF =2 and BOARD_RE_SEQ >0;

-- 답변
select * from board where BOARD_RE_REF=22;

select * from board;

delete from board where BOARD_NUM =24;