# Harbinger
The proof-of-concept library for loading and executing scripts in runtime. 

## Getting started
clone this project and build via maven

```mvn -U clean install```

Run ExampleRunner class to see example. Used ini files is example.ini and example2.ini

Run ReloadExampleRunner to see how reloading works. Used ini file is reloadable.ini

Test your snippets after changing

```mvn test```

## Concept
### Given:
* An ini-style file with numbers as keys and java source code or keywords as values
### Requirements:
* Ability to reload scripts in runtime
* Divide huge blocks of code into small files
* Ability to test these files
* Be able to use simple precompiled processors such as copy value processor
### Used tech
* Groovy language to compile, load and reload files into metaspace
* Byte-buddy to create Processor interface implementations on-the-fly
* Spock to test processors 

## ToDo List
* Cover with tests
* Think about one more dimension for scripting to have several methods in one processor
