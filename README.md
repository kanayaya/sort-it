
# sort-it
(По-русски ниже)

file merging program


## Authors

- [@kanayaya](https://github.com/kanayaya)


## Installation

Install my project with maven.
pre-install maven and git. set PATH variable to both
#### Build on Linux, Windows:

```bash
  git clone https://github.com/kanayaya/sort-it.git
  cd sort-it
  mvn package
  cd target
```
And now you in folder with sort-it.exe.

#### You could only use it on Windows:
flags:  
-a ascendant order / -d descendant order #not required, ascendant is default  
-i integers / -s strings #required

then you should specify destination file

then you should specify not less than one source file

#### examples:

```cmd
  .\sort-it.exe -s c:\destination\file c:\any\amount c:\of\source\files
```
```cmd
  .\sort-it.exe -d -i c:\destination\file c:\any\amount c:\of\source\files
```
# sort-it

Программа слияния файлов


## Автор

- [@kanayaya](https://github.com/kanayaya)


## Установка

Производится с помощью Maven.
Установите Maven и Git, укажите пути к их папкам bin в PATH переменной среды
#### Сборка на Linux, Windows:

```bash
  git clone https://github.com/kanayaya/sort-it.git
  cd sort-it
  mvn package
  cd target
```
Следуя инструкциям вы окажетесь в папке с файлом sort-it.exe.

#### Использовать можно только на Windows:
Флаги:  
-a по-возрастанию / -d по-убыванию #необязательный, по-умолчанию возрастание  
-i целые числа / -s строки #обязательный

затем укажите файл куда писать

затем укажите сколько угодно (не менее одного) файлов-источников

#### Примеры:

```cmd
  .\sort-it.exe -s c:\destination\file c:\any\amount c:\of\source\files
```
```cmd
  .\sort-it.exe -d -i c:\destination\file c:\any\amount c:\of\source\files
```