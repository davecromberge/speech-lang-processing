NGrams v1.0 Readme
===================

The sample output was generated using the works of Charles Dickens.  
The books were obtained from the following source:
http://www.textfiles.com/etext/AUTHORS/DICKENS/

To run the program, from the command line type:

> java -jar ngrams.jar <path_to_corpus1.txt> <path_to_corpus2.txt>

where path_to_corpus.txt is a text file containing a corpus of some text for analysis.

For example, using "A Christmas Carol" and "The Cricket on the Hearth" by Charles Dickens:

> java -jar 35398574_ngrams.jar "dickens_christmas.txt" "dickens_cricket.txt"


The program will then produce unigram and bigram output for each corpus input as an argument.
