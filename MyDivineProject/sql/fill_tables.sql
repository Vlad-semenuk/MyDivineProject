--fill DB--
--fill roles--
INSERT INTO roles (name) VALUES ('Student');
INSERT INTO roles (name) VALUES ('Teacher');
INSERT INTO roles (name) VALUES ('Admin');

--fill topic--
INSERT INTO topic (name) VALUES ('JAVA');
INSERT INTO topic (name) VALUES ('DEVOPS');
INSERT INTO topic (name) VALUES ('PHP');
INSERT INTO topic (name) VALUES ('ANDROID');
INSERT INTO topic (name) VALUES ('PYTHON');
INSERT INTO topic (name) VALUES ('RUBY');
INSERT INTO topic (name) VALUES ('JAVASCRIPT');
INSERT INTO topic (name) VALUES ('AUTOMATED_TESTING');

--fill states--
INSERT INTO states (name) VALUES ('Open');
INSERT INTO states (name) VALUES ('During');
INSERT INTO states (name) VALUES ('Finished');

--fill user--
INSERT INTO "users"(login, password, fullname, role_id) VALUES ('admin', '3fde6bb0541387e4ebdadf7c2ff31123', 'Админ Админович',3);
INSERT INTO "users"(login, password, fullname) VALUES ('student', '3fde6bb0541387e4ebdadf7c2ff31123', 'Студент Обыкновенный');
INSERT INTO "users"(login, password, fullname) VALUES ('student2', '3fde6bb0541387e4ebdadf7c2ff31123', 'Иван Иванов');
INSERT INTO "users"(login, password, fullname) VALUES ('student3', '3fde6bb0541387e4ebdadf7c2ff31123', 'Петр Петров');
INSERT INTO "users"(login, password, fullname) VALUES ('student4', '3fde6bb0541387e4ebdadf7c2ff31123', 'John Johnson');
INSERT INTO "users"(login, password, fullname) VALUES ('student5', '3fde6bb0541387e4ebdadf7c2ff31123', 'Bob Marley');
INSERT INTO "users"(login, password, fullname) VALUES ('student6', '3fde6bb0541387e4ebdadf7c2ff31123', 'Little Big');
INSERT INTO "users"(login, password, fullname) VALUES ('student7', '3fde6bb0541387e4ebdadf7c2ff31123', 'Just Student');
INSERT INTO "users"(login, password, fullname) VALUES ('student8', '3fde6bb0541387e4ebdadf7c2ff31123', 'Software Developer');
INSERT INTO "users"(login, password, fullname) VALUES ('student9', '3fde6bb0541387e4ebdadf7c2ff31123', 'Сын Маминой Подруги');
INSERT INTO "users"(login, password, fullname, role_id) VALUES ('teacher', '3fde6bb0541387e4ebdadf7c2ff31123', 'Дмитрий Колесников', 2);
INSERT INTO "users"(login, password, fullname, role_id) VALUES ('teacher2', '3fde6bb0541387e4ebdadf7c2ff31123', 'John Berners-Lee', 2);
INSERT INTO "users"(login, password, fullname, role_id) VALUES ('teacher3', '3fde6bb0541387e4ebdadf7c2ff31123', 'Dennis Ritchie', 2);
INSERT INTO "users"(login, password, fullname, role_id) VALUES ('teacher4', '3fde6bb0541387e4ebdadf7c2ff31123', 'Иван Иванович', 2);
INSERT INTO "users"(login, password, fullname, role_id) VALUES ('teacher5', '3fde6bb0541387e4ebdadf7c2ff31123', 'Олег Олегович', 2);


--fill Courses--
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('JAVA TRAINING',1 , 2, 'teacher', '2020-4-15', '2020-6-15');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('JAVA WEB DEVELOPMENT',1 , 3, 'teacher', '2019-12-10', '2020-2-10');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('JAVA ONLINE TRAINING',1 , 1, 'teacher', '2020-1-20', '2020-5-20');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('DEVOPS SYSTEMS ENGINEERING',2 , 1, 'teacher2', '2020-5-10', '2020-6-6');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('PHP Training',3 , 2, 'teacher4', '2019-12-11', '2019-12-20');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('PHP BOOT CAMP',3 , 2, 'teacher4', '2019-12-11', '2019-12-20');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('ANDROID WINTER TRAINING',4 , 1, 'teacher3', '2019-01-12', '2020-05-02');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('ANDROID SUMMER TRAINING',4 , 1, 'teacher3', '2020-01-06', '2020-05-08');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('ANDROID FOR OPEN SOURCE',4 , 1, 'teacher3', '2020-05-05', '2019-06-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('ANDROID',4 , 2, 'teacher3', '2019-11-05', '2019-11-28');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('PYTHON PROGRAMMING',5 , 1, 'teacher2', '2020-05-05', '2019-06-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('PYTHON TRAINING',5 , 1, 'teacher2', '2021-05-05', '2021-06-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('RUBY TRAINING',6 , 1, 'teacher', '2020-05-02', '2020-06-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('JAVASCRIPT (FRONTEND)',7 , 1, 'teacher5', '2020-05-02', '2020-06-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('JAVASCRIPT TRAINING',7 , 1, 'teacher5', '2020-01-02', '2020-03-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('JAVASCRIPT DEVELOPED',7 , 1, 'teacher5', '2020-03-05', '2020-05-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('AUTOMATED TESTING',8 , 1, 'teacher4', '2020-04-05', '2020-06-06');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('TEST AUTOMATION BASICS',8 , 1, 'teacher4', '2020-04-05', '2020-06-07');
INSERT INTO "courses"(name, topic_id, state_id, teacher, start_date, end_date) VALUES ('TEST AUTOMATION',8 , 3, 'teacher5', '2019-04-05', '2019-06-07');


--fill grade book--
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student', 1);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student', 2);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student2', 1);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student3', 1);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student3', 2);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student4', 3);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student4', 9);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student5', 2);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student5', 4);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student6', 4);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student7', 2);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student6', 5);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student7', 5);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student8', 5);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student8', 3);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student9', 5);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student9', 6);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student9', 7);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student2', 7);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student2', 9);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student2', 11);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student', 10);
INSERT INTO "grade_book" ( user_login, course_id) VALUES ('student', 19);