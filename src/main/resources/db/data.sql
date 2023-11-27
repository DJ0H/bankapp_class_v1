insert into user_tb(username, password, fullname) values ('길동', '1234', '고');
insert into user_tb(username, password, fullname) values ('둘리', '1234', '애기공룡');
insert into user_tb(username, password, fullname) values ('마이', '1234', '콜');
-- 기본 계좌 등록
insert into account_tb(number, password, balance, user_id) values ('1111', '1234', 1300, 1);
insert into account_tb(number, password, balance, user_id) values ('2222', '1234', 1100, 2);
insert into account_tb(number, password, balance, user_id) values ('3333', '1234', 0, 3);

-- 1번계좌 1000원
-- 2번계좌 1000원
-- 3번계좌 0원
-- 이체 내역을 기록
-- 1번 계좌에서 2번계좌로 100원 이체한다.
insert into history_tb(amount, w_balance, d_balance, w_account_id, d_account_id) values (100, 900, 1100, 1, 2);
-- atm 출금만 1번 게좌에서 100원만 출금 하는 히스토리
insert into history_tb(amount, w_balance, d_balance, w_account_id, d_account_id) values (100, 800, null, 1, null);
-- 1번 계좌에 500원 입금
insert into history_tb(amount, w_balance, d_balance, w_account_id, d_account_id) values (500, null, 1300, null, 1);