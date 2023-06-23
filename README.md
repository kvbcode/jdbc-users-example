## Пример работы с JDBC

Имеются 3 таблицы, содержащие список пользователей, ролей и их связь. По условию задания, отношение между пользователем и ролью задано как многие-ко-многим.

```
id|user_name          |roles_list                      
--+-------------------+--------------------------------
 1|Lionel C. Mathena  |READ, WRITE, UPLOAD, INFO, STATS
 2|Pierre J. Gouge    |READ, WRITE, UPLOAD             
 3|Rose D. Kent       |READ, WRITE, UPLOAD             
 4|Beverly S. Bingaman|READ, WRITE, UPLOAD             
 5|Daniel V. Morones  |INFO, STATS                     
 6|Thomas E. Bynum    |                                
```

Задачи:
- Получать список пользователей обладающих ролью.
- Получать список ролей отдельного пользователя.


### Запуск
```shell
mvn clean compile exec:java
```


### Ожидаемый вывод
```
Пользователи с ролью 'READ': 
User{id=1, name='Lionel C. Mathena'}
User{id=2, name='Pierre J. Gouge'}
User{id=3, name='Rose D. Kent'}
User{id=4, name='Beverly S. Bingaman'}
Роли пользователя id=1: [Role{READ}, Role{UPLOAD}, Role{STATS}, Role{INFO}, Role{WRITE}]
Роли пользователя id=3: [Role{READ}, Role{UPLOAD}, Role{WRITE}]
Роли пользователя id=6: []
Роли пользователя 'Pierre J. Gouge': [Role{READ}, Role{UPLOAD}, Role{WRITE}]
```