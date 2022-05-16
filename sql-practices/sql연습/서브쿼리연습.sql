-- subquery 

-- 1) select 절
select (select max(salary) from salaries);  

-- 2) from 절 
select now() as a, sysdate() as b, 3+1 as c;
select s.a,s.b 
from(select now() as a, sysdate() as b, 3+1 as c) s;
	
-- 3) where 절
-- 현재, Fai bale이 근무하는 부서의 근무하는 직원의 사번, 전체 이름을 출력해보세요.

select dept_no
from dept_emp a, employees b 
where a.emp_no = b.emp_no
and a.to_date='9999-01-01'
and concat(b.first_name, ' ',b.last_name) = 'Fai bale';

select b.emp_no, b.first_name
from dept_emp a, employees b 
where a.emp_no = b.emp_no
and a.to_date='9999-01-01'
and dept_no ='d004';

-- sol)
select b.emp_no, b.first_name
from dept_emp a, employees b 
where a.emp_no = b.emp_no
and a.to_date='9999-01-01'
and dept_no = (select dept_no
				from dept_emp a, employees b 
				where a.emp_no = b.emp_no
				and a.to_date='9999-01-01'
				and concat(b.first_name, ' ',b.last_name) = 'Fai bale');
                

-- 3-1) 단일행 연산자 : =, >, <, >=, <=, <>, !=
-- 실습문제 1:   현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의  이름, 급여를 나타내세요.
select concat(first_name,last_name) as 이름, salary
from employees, salaries
where employees.emp_no = salaries.emp_no 
and salaries.to_date='9999-01-01'
and salary < (select avg(salary) from  salaries where salaries.to_date='9999-01-01')
order by salary desc;

-- 실습문제 2:   현재 가장적은 평균 급여를 받고 있는 직책에해서  평균 급여를 구하세요   
-- 1) 현재 가장 적은 직책의 평균급여 구하기 -> from 절에서..
	-- 1-1) 직책별 평균 급여 
		select a.title,avg(b.salary)
        from titles a, salaries b
        where a.emp_no = b.emp_no
        and a.to_date='9999-01-01'
        and b.to_date='9999-01-01'
        group by a.title;
	-- 1-2) 가장 적은 평균 급여
		select min(avg_salary) as 가장적은평균급여
		from (select a.title,avg(b.salary) as avg_salary
				from titles a, salaries b
				where a.emp_no = b.emp_no
				and a.to_date='9999-01-01'
				and b.to_date='9999-01-01'
				group by a.title) c;
        

        
        
-- 2-1) sol: 서브쿼리로 구해보기 
 
select a.title,avg(b.salary) as avg_salary
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date='9999-01-01'
and b.to_date='9999-01-01'
group by a.title
having avg_salary = (select min(avg_salary) as 가장적은평균급여
						from (select a.title,avg(b.salary) as avg_salary
								from titles a, salaries b
								where a.emp_no = b.emp_no
								and a.to_date='9999-01-01'
								and b.to_date='9999-01-01'
								group by a.title) c);


-- 2-2) sol2 : top-k 리미트 이용 
        select a.title,avg(b.salary)
        from titles a, salaries b
        where a.emp_no = b.emp_no
        and a.to_date='9999-01-01'
        and b.to_date='9999-01-01'
        group by a.title
        order by avg(salary) asc
        limit 0,1;
        
        
-- 3-2) 복수행 연산자 : 

-- 실습문제3 : 현재 급여가 50000 이상인 직원의 이름을 출력하세요. (급여가 큰 순서대로) 
-- 조인 서브쿼리 




                

