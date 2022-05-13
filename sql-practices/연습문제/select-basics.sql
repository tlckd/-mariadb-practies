-- select 연습 
select * from departments order by dept_no limit 3,3;
select dept_no, dept_name from departments;

-- alias 
-- 예제1: employees 테이블에서 직원의 이름, 성별, 입사일을 출력 
select first_name as 'firstName', gender '성별', hire_date as'입사일' 
from employees; 

-- 예제2: employees 테이블에서 직원의 이름(성+이름), 성별, 입사일을 출력 
select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees; 

--  distict 
-- 예제3 : titles 테이블에서 모든 직급의 이름을 출력 
select distinct title 
from titles;

-- where 절 #1 
-- 예제: 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력 
select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where hire_date<'1991-01-01'
order by hire_date desc;

-- where 절 #2 : 논리연산자 
-- 예제: 1989년 이전에 입사한 여직원의 이름, 성별, 입사일을 출력 
select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where hire_date<'1990-01-01' and gender = 'F'
order by hire_date desc;

-- where 절 #3 : in 연산자 
-- 예제 : dept_emp 테이블에서 부서번호가 d005 이거나(or) d009에 속한 사원의 사번, 부서번호를 출력 
select emp_no, dept_no from dept_emp where dept_no = 'd005' or dept_no='d009' ;  

select emp_no, dept_no from dept_emp where dept_no in('d005', 'd009');  

-- where 절 #4 : Like 검색 
-- 예제: 1989년 이전에 입사한 여직원의 이름, 성별, 입사일을 출력 
select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where hire_date <= '1989-12-31' and hire_date >= '1989-01-01';

select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where hire_date like '1989%';

select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where hire_date between '1989-12-31' and '1989-01-01';

-- order by 절 
-- 에제1: 남자 직원의 이름, 성별, 입사일을 입사일순(선임순)으로 출력 
select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where gender = 'M'
order by hire_date asc;

-- order by 절 
-- 에제2: 직원들의 사번, 월급을 사번과 월급순으로... 
select emp_no, salary
from salaries
order by emp_no asc, salary desc;


-- 함수 : 문자열 
-- upper 
select upper('buSan;'), upper('busan'), upper('douzone');
select upper(first_name) from employees;

-- lower 
select lower('buSan;'), lower('busan'), lower('douzone');
select lower(first_name) from employees;

-- substring(문자열, index, length) 
select substring('Hello World',3,2);

select concat(first_name, last_name) as '이름', gender '성별', hire_date as'입사일' 
from employees
where substring(hire_date,1,4) = '1989';

-- lpad (오른쪽 정렬) , rpad (왼쪽 정렬)
select lpad('1234',10,'-');
select rpad('1234',10,'-');
 
 -- 예제 : 직원들의 월급을 오른쪽 정렬 (빈공간은 *)
 select lpad( salary,10,'*'), emp_no from salaries;
 
 -- trim, ltrim, rtrim 
 select concat ( '---', ltrim('     hello   ') ,'---' ),
 concat ( '---',rtrim('     hello   ') ,'---' ),
  concat ( '---',trim('     hello   ') ,'---' ),
  concat ( '---',trim(both 'x' from'     hello   ') ,'---' );
  

-- 함수: 날짜 함수 
 
-- curdate(), current_date;
select curdate(), current_date;

-- curtime(), current_time;
select curdate(), current_date;

-- now() vs sysdate()
select now(), sysdate();

select now(), sleep(2), now();
select sysdate(), sleep(2), sysdate();

-- date_format 
-- 2022년 05월 13일 11시 39분 40초
 select date_format(now(),'%Y년 %m월 %d일 %h시 %i분 %s초');
 select date_format(now(),'%y년 %c월 %d일 %h시 %i분 %s초');
 
 -- period_diff 
 -- YYMM, YYYTMM 
 -- 예) 근무개월 수  
  select period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) as month
    from employees
order by month desc;
 
 
 
 -- date_add(=adddate)
-- 날짜를 date에 type(day,month,year) 형식의 표현식을 더하거나 뺸다.alter
-- 예제) 각 사원들의 근무 년 수 수가 5년이 되는 날은 언제인가요? 

select first_name, hire_date,date_add(hire_date, interval 5 year) from employees;


-- cast 
  
-- mysql type
-- varchat(10), char(10) 
select '12345' + '10', cast('12345' as int) + 10, concat('12345', '10');
select date_format('2022-10-10', '%Y년 %m월 %d일'), date_format(cast('2022-10-10' as date), '%Y년 %m월 %d일');
select cast(cast(1-2 as unsigned) as signed);


-- 함수 : 수학 

-- abs 
select abs(-1), abs(1);

-- mod 
select mod(10,3);

-- ceil (실수보다 큰 정수 올림)
select ceil(3.14), ceiling(3.14);

-- floor (버림)
select ceil(3.14);

-- round(x) : x에 가장 근접한 정수 (반올림) 
-- round(x,d) : x값 중에 소수점 d자리에 가장 근접한 실수 

select round(1.498), round(1.498,1), round(1.498,0);

-- power(x,y), pow(x,y) : x의 y승 
select power(2,10),pow(2,10);

-- sign(x) 부호 알려주는거 양수면 1 음수면 -1 
select sign(20), sign(-100), sign(0);

-- greatest(x,y, .....), least(x, y,....) 넣은 값들중에서 최대값, 최소값 뽑아내는거  
select greatest(10,40,20,50), least(10,40,20,50);
select greatest('b','A','C'), greatest('hello','hellp','hellq');

-- 집계쿼리: select에 그룹함수가 적용된 경우 
-- avg, max, min, count, sum, ....  

select avg(salary) from salaries;


-- select 절에 그룹함수가 있는 경우, 어떤 컬럼도 select절에 올 수 없다. 
-- emp_no는 아무 의미가 없다.
-- 오류!!! 
select emp_no, avg(salary)
from salaries;

-- query 순서 
-- 1) from : 접근 테이블 확인 
-- 2) where : 조건에 맞는 row을 선택 
-- 3) 집계 
-- 4) projection 

-- 평균내보기 
select avg(salary) from salaries where emp_no='10060'; 

 -- group by 에 참여하고 있는 컬럼은 projection 이 가능하다.(select 절에 올 수 있다)
 select emp_no,avg(salary), sum(salary)
 from salaries
 group by emp_no
 order by sum(salary) desc, avg(salary) desc;
 
 -- having 
 -- 집계결과(결과 임시 테이블)에서 row를 선택해야 하는 경우
 -- 이미 where 절은 실행이 되었기 때문에 having 절에서 이 조건을 주어야 한다. 
 
select emp_no,avg(salary), sum(salary)
 from salaries
 group by emp_no
 having avg(salary)>60000
 order by avg(salary) asc;
 
 -- 예제 
 -- salaries 테이블에서 사번이 10060인 직원의 급여 평균과 종합을 출력하세요. 
 -- 문법적으로는 오류! 
 -- 의미적으로는 맞다(where)
 -- having 절로 하는게 맞음 
 
 select emp_no,avg(salary), sum(salary)
 from salaries
 where emp_no='10060';
 
 -- 문법적으로 이렇게 해야함 집계함수는 having에서 조건 걸어줘야함 
 
select emp_no,avg(salary), sum(salary)
 from salaries
 group by emp_no 
 having emp_no='10060';
 
 -- inner join 
 -- 예제1 : 현재 근무하고 있는 직원의 이름과 직책을 출력하세요. 
 select a.emp_no, a.first_name, b.title
 from employees as a, titles as b 
 where a.emp_no = b.emp_no -- join 조건 (n-1)
 and b.to_date ='9999-01-01'; -- row 선택 조건 
 
 -- 예제2 : 현재 직원의 이름과 직책을 출력하되 여성 엔지니어(engineer)만 출력하세요 
 select a.first_name,b.title,gender
 from employees as a, titles as b
 where a.emp_no = b.emp_no and gender='F'and b.to_date='9999-01-01' and b.title='engineer';
 
 
 -- 조인 표준문법 
 
 -- 1) natural join 현장에서는 쓸일없음 
 -- 두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건을 명시하지 않아도 암묵적으로 조인이 됨 
select a.emp_no, a.first_name, b.title
 from employees as a natural join titles as b -- 명시안해도 됨 
 where b.to_date ='9999-01-01'; -- row 선택 조건 
 
 -- 2) join ~ using 이것도 현장에서는 안씀 
 -- 내추럴 조인의 문제점 
  select count(*)
 from salaries a natural join titles b 
 where a.to_date ='9999-01-01' and b.to_date ='9999-01-01';
 
   select count(*)
 from salaries a join titles b  using (emp_no)
 where a.to_date ='9999-01-01' and b.to_date ='9999-01-01';
 
 
 -- 3) join ~ on 
  select b.title,avg(salary)
 from salaries a join titles b on a.emp_no = b.emp_no
 where a.to_date ='9999-01-01' and b.to_date ='9999-01-01'
 group by b.title
 order by avg(salary) desc;

 