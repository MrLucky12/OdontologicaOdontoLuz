USE zumaran;
INSERT INTO dentist(id, cmp, create_at,email,last_name,name,phone,photo,status) 
VALUES  (1, '123456', '2021-08-10', 'jpanaque@unitru.edu.pe', 'Panaque', 'Jamil', '123456789', NULL, 1),
        (2, '159749', '2022-08-10', 'avisitacion@unitru.edu.pe', 'Visitacion', 'Alvaro', '123789456', NULL, 1),
        (3, '346715', '2020-08-10', 'vvillar@unitru.edu.pe', 'Villar', 'Victor', '147456853', NULL, 1);

INSERT INTO offices(id,create_at,description,name,photo,status,dentist)
VALUES  (1, '2020-05-26', 'description 1', 'Consultorio 1', NULL, 1, 1);