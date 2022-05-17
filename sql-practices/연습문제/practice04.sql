-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?

select count(*)
from employees a, salaries b
where a.emp_no = b.emp_no 
and b.to_date ='9999-01-01'
and salary > (select avg(salary)
				from salaries a 
				where a.to_date='9999-01-01');


-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

select a.emp_no as 사번, concat(first_name, last_name) as 이름, salary 부서연봉 
from employees a, salaries b, dept_emp c, departments d 
where a.emp_no = b.emp_no 
and a.emp_no = c.emp_no
and c.dept_no = d.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and salary in (select max(salary)
				from salaries a, dept_emp b, departments c 
				where a.emp_no = b.emp_no 
				and b.dept_no = c.dept_no
				and a.to_date='9999-01-01'
				and b.to_date='9999-01-01'
				group by c.dept_name);


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 

select a.emp_no 사번, concat(first_name, last_name) as 이름 ,salary 연봉, avg_salary 자신의부서평균연봉 
		from employees a, 
		salaries b,
		dept_emp c,
		departments d,
				(select dept_name,avg(salary) as avg_salary
				from salaries a , dept_emp b, departments c 
				where a.emp_no = b.emp_no 
				and b.dept_no = c.dept_no
				and a.to_date='9999-01-01'
				and b.to_date='9999-01-01'
				group by dept_name) e
		where a.emp_no = b.emp_no
		and a.emp_no = c.emp_no
		and c.dept_no = d.dept_no
		and d.dept_name = e.dept_name
		and b.to_date='9999-01-01'
		and c.to_date='9999-01-01'
		and salary > avg_salary
        order by 연봉 desc;
        
-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
-- 사원 -조인- dept_emp -조인- dept_manager - dept_no

select a.emp_no, concat(first_name, last_name) as 이름, 매니저이름, dept_name as 부서이름 
from employees a, dept_emp c , departments d,
(select b.dept_no,a.emp_no,concat(first_name, last_name) as 매니저이름
from employees a, dept_manager b 
where a.emp_no = b.emp_no 
and b.to_date='9999-01-01') b
where a.emp_no = c.emp_no
and c.dept_no = b.dept_no
and c.dept_no = d.dept_no
and c.to_date = '9999-01-01';


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select a.emp_no as 사번, concat(first_name, last_name) as 이름, title as 직책, salary as 연봉 
from employees a, titles b, salaries c, dept_emp d 
where a.emp_no = b.emp_no 
and a.emp_no = c.emp_no 
and a.emp_no = d.emp_no
and b.to_date like '9999%'
and c.to_date like '9999%'
and d.to_date like '9999%'
and d.dept_no = (select b.dept_no
				from salaries a, dept_emp b, departments c 
				where a.emp_no = b.emp_no 
				and b.dept_no = c.dept_no
				and a.to_date like '9999%'
				and b.to_date like '9999%'
				group by dept_name
				having avg(salary) = (select max(a.mydept_avg)
									from (select dept_name, avg(salary) as mydept_avg
									from salaries a, dept_emp b, departments c 
									where a.emp_no = b.emp_no 
									and b.dept_no = c.dept_no
									and a.to_date like '9999%'
									and b.to_date like '9999%'
									group by dept_name)a ))
order by salary desc;
                    
                    

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

-- 문제7.
-- 평균 연봉이 가장 높은 직책?

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.


