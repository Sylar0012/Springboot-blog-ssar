# MyBatis DB연결 세팅

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### 테이블 생성
```sql
create table users(
    id number primary key,
    username varchar2(20),
    password varchar2(20),
    email varchar2(50),
    createdAt TIMESTAMP
);

CREATE SEQUENCE users_seq 
INCREMENT BY 1 
START WITH 1;
```

### 더미데이터 추가
```sql
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'ssar', '1234', 'ssar@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'cos', '1234', 'cos@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'hong', '1234', 'hong@nate.com', sysdate);
commit;
```

### 회원탈퇴
```
임시로 익명의 유저를 만든 후에 쿼리 수정해야함.

```