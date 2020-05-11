# PROJECT NAME. :arrow_forward::high_brightness:
The project is titled 'news-portal'. The project basically functions as the title suggests.For more information check out the following link:https://news-api5.herokuapp.com/


## AUTHOR NAME.
My name is Samuel and i am currently a learning software programmer at Moringa School.:man_technologist::man_technologist:

## DESCRIPTION OF PROJECT.
The project is a Java REST API application built with GSON serialization/deserialization library which converts Java Objects into JSON and back, thus one is able to query and receive information from the news organization's API. :medal_military::medal_military::trophy:

### PROJECT SETUP INSTRUCTIONS.
1. Fork this repository.
2. Clone this repository onto your local machine through use of the command `git clone <Forked-repository-link.>`. 
3. Navigate to your terminal.
4. Navigate the the appropriate directory by use of cd command `cd<root-folder>`.
5. In order to make any additional changes to the project, it is highly adviced that you install an appropriate IDE(Integrated Development Environment).
6. One is also required to install postman to ensure that routes provided below are working efficiently.

### DATABASE SETUP INSTRUCTIONS.
- While in psql:
- `CREATE DATABASE news_api;`
- `\c news_api`
- `CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, user_id integer);`
- `CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR, details VARCHAR, role VARCHAR, position VARCHAR);`
- `CREATE TABLE news (id SERIAL PRIMARY KEY, name VARCHAR,content VARCHAR, dpt_id INTEGER);`
- `CREATE TABLE departments_users(id SERIAL PRIMARY KEY, dpt_id INTEGER, user_id INTEGER);`
- `CREATE DATABASE news_api_test WITH TEMPLATE news_api;`

## API Documentation.
--------------------
###### User :man_scientist:
* Accessing all user(s):https://news-api5.herokuapp.com/users

* Creating a user:https://news-api5.herokuapp.com/users/new
> {
>
> "name":"Samuel",
>
> "position":"Junior developer",
>
> "role":"programmer",
>
> "details":"Software company"
>
>}

* Viewing a specific user:https://news-api5.herokuapp.com/users/:id
  - `Replace :id with id of user`
* Viewing if users are associated with a department:https://news-api5.herokuapp.com/users/:user_id/departments
  - `Replace :user_id with specific id of logged user.`
* Adding a user to a department:https://news-api5.herokuapp.com/departments/:dpt_id/users/new
  - `Remember to replace :dpt_id with id of logged department.`
  
  ###### Departments :phone::phone:
* Accessing all departments:https://news-api5.herokuapp.com/departments
* Creating a department:https://news-api5.herokuapp.com/departments/new
  > {
  >                                                                                                                                                                                                                                                                                                                                          
  > "name":"Sales department",
  >                                                                                                                                                                                                                                                                                                                                        
  > "description":"promotion of company's stocks",
  >                                                                                                                                                                                                                                                                                                                                          
  > "user":"10"
  >                                                                                                                                                                                                                                                                                                                                          
  > {  
   
* Viewing a specific department:https://news-api5.herokuapp.com/departments/:id
  - `Replace :id with specific id of department.` 
* Adding news to a specific department:https://news-api5.herokuapp.com/departments/:id/news/new  
  - `Replace :id with specific id of department.`    
* Assign a department to a user:https://news-api5.herokuapp.com/users/:user_id/departments/:dpt_id
  - `Remember to replace :user_id and :dpt_id with specific id of user and department. `   
  
  ###### News :newspaper::newspaper:
 * Accessing all news updates:https://news-api5.herokuapp.com/news
 * Adding a news update:https://news-api5.herokuapp.com/news/new
   > {
    >                                                                                                                                                                                                                                                                                                                                          
    > "name":"The awakening!!!",
    >                                                                                                                                                                                                                                                                                                                                        
    > "content":"Salary problems in the wake of the pandemic",
    >                                                                                                                                                                                                                                                                                                                                          
    > "dpt_id":"1"
    >                                                                                                                                                                                                                                                                                                                                          
    > {   
* Accessing news from a specific department:https://news-api5.herokuapp.com/departments/:id/dptNews   
  - `Replace :id with specific id of department.`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
### TECHNOLOGIES USED.
- Java.
- Gradle.
- JUnit.
- [GSON] (https://github.com/google/gson).
- Heroku web hosting service.
- [Postman] (https://www.postman.com/).
- Postgresql.

### KNOWN BUGS.
A small problem querying data from the departments route, but it should be resolved soon.

### CONTACT INFORMATION.
For more information, feel free to contact me through the email address: samdot@gmail.com

### LICENCE AND COPYRIGHT INFORMATION.
Copyright :copyright: 2020-present

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

