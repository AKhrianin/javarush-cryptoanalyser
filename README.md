Криптоанализатор

Краткое описание

Шивровка/дешифровка русского текста из указанного файла с помощью шифра Цезаря.

Сборка проекта:


Запуск проекта:


Ограничения:
Работа с заданным словарём из 39 символов русского алфавита.
Чтение/запись файлов .txt 

Описание алгоритма шифровки/расшифровки:
Сверка символа входящего текста с алфавитом, замена другим символом,
смещённым на n позиций, заданных пользователем в виде ключа.

Краткое описание классов:

В корневом каталоге проекта ua.com.javarush.akhrianin.cryptoanalyser
находится класс Main, содержащий в себе точку входа в приложение.

Класс Alphabet содержит исходный алфавит в виде константы.

Класс ProgramDialog содержит сообщения, выводимые пользователю.

Основные методы обработки данных.

Класс Main:

метод encode - содержит логику шифрования текста с помощью ключа,
который вводит пользователь.

метод decode - содержит логику расшифровки текста по ключу,
заданному пользователем.

метод decodeBruteForce - содержит логику расшифровки текста методом перебора.
Результаты в виде отдельных фалов записываются в каталог,
указанный пользователем.





