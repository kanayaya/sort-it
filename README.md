
# sort-it

file merging program


## Authors

- [@kanayaya](https://github.com/kanayaya)


## Installation

Install my project with maven.
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