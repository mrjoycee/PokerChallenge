#### Prerequisites: ####
- Java SDK 1.6
- JUnit 4
- ANT
- CLASSPATH environment variable must be configured to include junit and hamcrest jars.

#### Building: ####
From the project's home directory, run:

    ant build

#### Running: ####
Assuming build was succcessful, from the project's home directory, run:

    java -cp bin mjoyce.poker.PokerChallenge
    
Hands are input via direct interation with the app. Cards must be specified in the form &lt;rank&gt;&lt;suit&gt;" where &lt;rank&gt; is the numeric rank of the card, or J, Q, K, A for face cards and &lt;suit&gt; is the first letter of in the name of the desired suit. For example, 10D is the Ten of Diamonds, and KS is the King of Spades. Each card in the hand must be separated by a space.
    
#### Running JUnit tests: ####
Assuming build was succcessful, from the project's home directory, run:

    ant test
