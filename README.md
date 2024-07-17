- Java: 21
- Maven: 3.9.6

### разбор параметров командной строки

При разборе параметров командной строки значения при
повторяющихся флагах будет ипользован последний и его значение:

```console
in.txt -f -p prefix1 -o .. in2.txt -p prefix2 -s -o 
```

префикс - `prefix2`, выходная директория - `не задана`,
статистика - `краткая`, входные файлы - `in.txt in2.txt`

### сборка и запуск

```console
mvn clean
mvn package
java -jar .\target\util.jar -f -a -p "file with " -o . in1.txt in2.txt in4.txt
```

