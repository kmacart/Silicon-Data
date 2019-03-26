# Silicon-Data
The state of the game codebase as of Tuesday evening (25th March):
+ All-in-all, "looking good";
+ Main menu seems fairly stable;
+ Tone generator is OK, with some work to do, and Kira (with her musical expertise) with help from John (with his DJ/Venue experience) will push this track along; and
+ Michael's most recent set of files have been included in the codebase (Michael and Duncan will be working to integrate their functionality).

We now have a reasonably simple way to switch back and forth between different menus and screens.  The technique is illustrated in the Main --> High Scores --> Close sequence, and occurs entirely at the "button" (ie. object) level, which is what I wanted to achieve.  This will make it much easier for people to craft, say, a load or save function that just slots straight in to the main menu.  Also note that the "look and feel" is pretty much completely standardised: there is already a standard background specified in the Game.css file, and many of the GUI elements also have their styles specified there.  This means you shouldn't need to worry too much about style: create a Pane, and it should have the right background and borders; create a Button or Label, and it should have the right colours and fonts.

Note that you can always override the set style, but please don't: it's far better to discuss your views with the rest of the team, and make a global change to the .css file if necessary (especially since I make no claim to any stylistic expertise whatsoever, so most of the existing settings are just things that seemed to work OK and didn't clash too violently with everything else!).

Right, now the really important bit:  __We cannot stress enough, how much we need all of your help to bring this project to a conclusion.__  No one person can write all of the code; nor, quite frankly, would anyone want to!  The folks who are working on the main menu, and other core areas, need to ensure that they also build "hooks" to which other coders may connect their code-blocks.  This is essentially what Michael and Duncan will be doing this week.

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
