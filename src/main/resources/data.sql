INSERT INTO t_user (user_id, login, password, username, email, user_role_type)
VALUES ('4', 'admin', 'admin', 'adminKOT', 'admin@kmwt.pl','ADMIN'), ('5', 'jan', 'jan', 'janX', 'jan@lol.pl','USER'),
       ('6', 'aga', 'aga', 'aga511', 'aga@wp.pl','USER');

INSERT INTO t_tracked_object (tracked_obj_id, name, surname, user_id,tracked_obj_type,is_tracked)
VALUES ('13', 'ania', 'kownacka', '5', 'CHILD',FALSE), ('24', 'brajan', 'mcczis', '5', 'CHILD',FALSE), ('46', 'jan', 'matejko', '5', 'CHILD',TRUE);;

INSERT INTO t_fence (fence_id,fence_name, is_tracked)
VALUES ('101','szkoła',TRUE), ('102','krakow',FALSE);

INSERT INTO t_point (point_id, x, y, fence_id)
VALUES ('1564','12.512','15.64','101'), ('1462','22.512','55.64','101');

INSERT INTO t_tracked_object_fences (tracked_obj_id, fence_id)
VALUES ('13','101'), ('13','102'), ('24','101'), ('24','102');