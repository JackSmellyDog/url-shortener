
insert into users (id, username, password, role)
select 0 , 'donaldDuck', '$2a$10$uAqDk2Hczk2Wlzn0M8KDnO9CdeJgP4jFGf5v28krgRPoJZFaiD6PS', 'SUPERUSER'
where not exists (select * from users where role = 'SUPERUSER');


