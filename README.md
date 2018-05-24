##Germes

####Task:
Система Кинотеатр. Вы пишете интернет витрину маленького Кинотеатра с одним залом. В нем есть
Расписание показа фильмов на все 7 дней недели с 9:00 до 22:00 (начало последнего фильма).
Незарегистрированный пользователь может видеть: расписание, свободные места в зале, возможность
зарегистрироваться. Зарегистрированный пользователь должен быть в состоянии выкупить билет на
выбранное место. Администратор может: внести в расписание новый фильм, отменить фильм,
просматривать посещаемость зала.
####Requirements:
- Git
- Maven		 
- PostgreSQL

####Build:
1. Clone the project
      ```bash 
      https://github.com/NikaStark/Coliseum.git
      ```
      
 2. Change directory		
     ```bash		     
        cd Coliseum
     ```
      		 
 3. Create empty database and add credentials in 
    ```bash 
    src/main/resources/liquibase/liquibase.properties
    ```
 		 		 
 4. Run clean and default lifecycles (inclusive up to install phase)
     ```bash		     
      mvn clean install tomcat7:run
     ````
     
 5. App will be available by http://localhost:8080/coliseum/