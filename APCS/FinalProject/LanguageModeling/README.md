# Natural Language Modeling, 6 Points
## About
A program that creates an order K Markov model from input text and then generates stylized pseudo-random text.
For the sake of the exercise, the input is treated as a list of words as opposed to characters.

## Parts 

### LanguageModel.java
This is a class that generates an order-K Markov model of the inputted text. 

The model is represented by a HashMap, linking lists of words to a list of possible next words.

#### How it works
1) It is initialized with two inputs, an int K, that represents the order and a String text, the text it should model.
2) It takes the text and splits it into a list of words.
3) Then, for each x words for x in the range [1, K] it adds the next word to the HashMap entry for those x words (This ensures that the model will definitely be able to return a next word. It matches to the best of its ability, trying complex then decreasing in complexity until a word is found).

### TextGenerator.java
This is a class that generates psueo-random text given a LanguageModel object.

#### How it works
1) It is initialized with two inputs, a LanguageModel object and a desired length of output text(desired number of words)
2) While the size of the list of words that makes up the generated text is less than the desired length, the program uses the last K words to generate a weighted random following word. If the the last K words never appeared in the model, it tries again with the last K - 1 and so on until 1. 

## Running
1) Compile the classes.

``` javac LanguageModel.java TextGenerator.java ```

2) Run the program.

``` java TextGenerator <order K> <desired length> < <Model text file> ```

ex : `java TextGenerator 5 500 < trump-rally.txt` will print out 500 words of psuedo-randomly generated Trump speech.

### Output Format
Old Text:

I am the old text.

New Text:

I am the newly generated text.
