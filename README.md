# Silicon-Data

Message from Duncan:
-begins-
The state of the game codebase as of Monday evening (25th March):
+ All-in-all, "looking good";
+ Main menu seems fairly stable;
+ Tone generator is OK, with some work to do, and I have enlisted Kira (with her musical expertise) to help push this track along; and
+ Michael's most recent set of files have been included in the codebase (however, that does not mean their functionality is available yet: see more detailed comment immediately below).

It would be wonderful if I could enlist Michael's help to integrate the functionality from his files (eg. with respect to moving cards) into the codebase, but this does not seem to be high on his agenda at the moment. Absent his assistance, I am working my way through his files, modifying them as I go, to the point where, if he looks at the High Scores code (which is largely integrated now), about the only thing he will recognise is the name of the high scores file.

However, this has been a valuable exercise in one additional respect: I have found a reasonably simple way to switch back and forth between different menus and screens.  The technique is illustrated in the Main --> High Scores --> Close sequence, and occurs entirely at the "button" (ie. object) level, which is what I wanted to achieve.  This will make it much easier for people to craft, say, a load or save function that just slots straight in to the main menu.  Also note that the "look and feel" is pretty much completely standardised: there is already a standard background specified in the Game.css file, and many of the GUI elements also have their styles specified there.  This means you shouldn't need to worry too much about style: create a Pane, and it should have the right background and borders; create a Button or Label, and it should have the right colours and fonts.

Note that you can always override the set style, but please don't: it's far better to discuss your views with the rest of the team, and make a global change to the .css file if necessary (especially since I make no claim to any stylistic expertise whatsoever, so most of the existing settings are just things that seemed to work OK and didn't clash too violently with everything else!).

Right, now the really important bit:  __I cannot stress enough, how much I need all of your help to bring this project to a conclusion.__  I can't write all of the code; nor, quite frankly, do I want to.  I see my role as being to create a framework to which the rest of the team can contribute their creativity and talent.  I am happy to work on some of the more computationally complex areas, like the sound generator, and on overall game design, but the same person should never ... ever ... be responsible for both high-level design, and low-level coding.  Maybe Sybil could have done it, but I can't.

And now, back to your regularly-scheduled programming!  ;-)
-ends-

The following files are included in the Silicon Project:
+ Game.java (top-level Java Class);
+ HighScores.java (display high score table from a file);
+ Tone.java (simple threaded sound generator - frequency for milliseconds, and volume if you must);
+ Envelope.java (beginnings of an ADSR envelope modulator);
+ Twain ("struct" class file for ADSR duration/level pairs - used by the Envelope Class)
+ Game.css (JavaFX .css file with root, pane, button, label, and other styles);
+ High_Scores.txt (high scores save-file);
+ Silicon-logo.png (logo for the Game Board); and
+ iconic-photographs-1940-first-computer.jpg (first decade/era background for the Game Board).

To compile the game, put the Game.css file in the same directory as the .java files, the high_scores.txt file in a src/data directory, and the Silicon-logo.png and iconic-(etc.)-computer.jpg files in a bin\images directory.
