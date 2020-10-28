# Solvo

>mvnw install

>"c:\Program Files\Java\jdk-14.0.1\bin\java" -jar target/solvo-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

#Тестовое задание

Необходимо написать консольное приложение на Java (с использованием Maven), которое позволит обрабатывать параллельно запросы различных типов. Приложение должно содержать в себе юнит-тест (использовать JUnit) для проверки.
Имеется два типа запросов, которые поступают в единую точку входа. Запросы типа А и В, у каждого запроса есть признак Х, который является числом (для простоты int). Запросы разных типов должны обрабатываться строго параллельно, т.е. в очередь запросов типа А, не должны попадать запросы типа В. Запросы с одним и тем же значением признака Х должны выполняться строго последовательно. Т.е., если к нам приходит запрос типа А с Х = 10 (например), а потом приходит запрос типа А или В с Х = 10, то второй запрос должен дождаться окончания выполнения первого и наоборот. При этом запросы одного типа с разным значением признака могут (и по возможности, должны) выполняться параллельно.
Для простоты, общее количество параллельных потоков для обоих типов запроса следует ограничить 10-ю. Оба типа запроса имеют одинаковый приоритет, т.е. распределение потоков должно быть, по возможности, равным: по 5 потоков на каждый тип запроса при полной загрузке очередей.
Непосредственная обработка запроса может заключаться в выводе сообщения в лог. Вывод сообщений в лог надо организовать так, чтобы было понятно какой тип запроса в какой очереди обрабатывается. Так же выводить в лог состояние очередей при каждом входящем запросе.
Тест приложения должен позволять настраивать количество и тип запросов для проверки.
