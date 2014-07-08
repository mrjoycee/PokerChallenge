#### Prerequisites: ####
- Java SDK 1.6
- JUnit 4 (https://github.com/junit-team/junit/wiki/Download-and-Install)
- ANT (http://ant.apache.org/bindownload.cgi)
- CLASSPATH environment variable must be configured to include junit.jar and hamcrest-core.jar.

#### Building: ####
From the project's home directory, run:

    ant build

#### Running: ####
Assuming build was succcessful, from the project's home directory, run:

    java -cp bin mjoyce.poker.PokerChallenge
    
Hands are input via direct interation with the app. Cards must be specified in the form &lt;rank&gt;&lt;suit&gt;" where &lt;rank&gt; is the numeric rank of the card, or J, Q, K, A for face cards and &lt;suit&gt; is the first letter of in the name of the desired suit. For example, 10D is the Ten of Diamonds, and KS is the King of Spades. Each card in the hand must be separated by a space.

Sample output:

    Enter hand 1 ('q' to quit): ac 10s jd qh kh
    Enter hand 2 ('q' to quit): 6s 6h jc js 9h
    Hand 1 ([Ace of Clubs, King of Hearts, Queen of Hearts, Jack of Diamonds, Ten of Spades]) Straight
    vs.
    Hand 2 ([Jack of Clubs, Jack of Spades, Nine of Hearts, Six of Spades, Six of Hearts]) Two Pair
    Hand 1 wins!
    
#### Running JUnit tests: ####
Assuming build was succcessful, from the project's home directory, run:

    ant test
