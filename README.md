# Mastermind

![Mastermind](https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Mastermind.jpg/300px-Mastermind.jpg)

Hoofing around with a Clojure/ClojureScript version of the '70s game Mastermind - inspired
by Peter Marks' talk at [Haskell London](https://www.youtube.com/watch?v=eSrVC4-p7aI).

## Gameplay and Rules

From _Wikipedia_:

The game is played using:

* a _decoding board_, with a shield at one end covering a row of four 
  large holes, and twelve (or ten, or eight, or six) additional rows 
  containing four large holes next to a set of four small holes;

* _code pegs_ of six (or more; see Variations below) different colors, 
  with round heads, which will be placed in the large holes on the 
  board; and
  
* _key pegs_, some colored or black, some white, which are flat-headed 
  and smaller than the code pegs; they will be placed in the small holes
  on the board.

The two players decide in advance how many games they will play, which must
be an even number. One player becomes the _codemaker_, the other the 
_codebreaker_. The codemaker chooses a pattern of four code pegs. Duplicates
are allowed, so the player could even choose four code pegs of the same color.
The chosen pattern is placed in the four holes covered by the shield, visible
to the codemaker but not to the codebreaker.

The codebreaker tries to guess the pattern, in both order and color, within
twelve (or ten, or eight) turns. Each guess is made by placing a row of code
pegs on the decoding board. Once placed, the codemaker provides feedback by
placing from zero to four key pegs in the small holes of the row with the
guess. A colored or black key peg is placed for each code peg from the guess
which is correct in both color and position. A white key peg indicates the 
existence of a correct color code peg placed in the wrong position.

Once feedback is provided, another guess is made; guesses and feedback 
continue to alternate until either the codebreaker guesses correctly, or 
twelve (or ten, or eight) incorrect guesses are made.

The codemaker gets one point for each guess a codebreaker makes. An extra 
point is earned by the codemaker if the codebreaker doesn't guess the 
pattern exactly in the last guess. (An alternative is to score based on the
number of colored key pegs placed.) The winner is the one who has the most 
points after the agreed-upon number of games are played.

Other rules may be specified.
