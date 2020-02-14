# core-nlp-ver1
Mapping of sentences into Java structure using Stanford NLP
This project will read your sentence and create a class from it following the pattern:
- if word is PERSON, this word will be the name of a class
- if word is VERB, the programm will create a method with given name
- if word is DATE, it will field of your class 

To run project:

1)Download core-nlp-ver1.jar
2)Open terminal
3)Direct terminal to core-nlp-ver1.jar location
4)Execute 
         java -Xmx4096M -jar core-nlp-ver1.jar *sentence you want to test out*
