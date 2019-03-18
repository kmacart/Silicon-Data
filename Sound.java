
/*
 * Class Sound: Simple sound generator for Silicon Project.
 * The methods are static so you can go: Sound.tone(hz, msecs) to make a sound.
 * If you must, you can also set the volume too: Sound.tone(hz, msecs, vol).
 * Adapted from some horrible (largely non-functional) code at Stack Overflow.
 * Coding credits(!): "https://stackoverflow.com/a/6700039"
 * The original (kind of) worked at the specific combination of sample rate and frequency it used.
 * But it produced silence at the frequencies normal ears can handle.
 * It also just flicked method-specific errors up-the-chain (don't you hate that?).
 * Our adaptation seems to work for all frequencies, and handles its own errors.  Cough.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sound {
    private static float SAMPLE_RATE = 22000f; // Don't mess with this. You have been warned!
    private static int MULTIPLIER = 22; // Yes, it's an int equal to the sample rate divided by 1,000

    // This is the entry point for code that just wants to specify frequency and
    // duration - to play notes, see https://pages.mtu.edu/~suits/notefreqs.html
    public static void tone(int hz, int msecs) {
	tone(hz, msecs, 1.0);
    }

    // This is the entry point for code that wants to specify frequency, duration
    // and volume (0 ... 1)
    public static void tone(int hz, int msecs, double vol) {
	byte[] buf = new byte[1];
	AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
	SourceDataLine sdl;

	// Sound output lines can be a bit scarce at times - worth checking
	try {
	    sdl = AudioSystem.getSourceDataLine(af);
	} catch (LineUnavailableException e) {
	    e.printStackTrace();
	    return;
	}

	// We probably could have got away with flicking this one, 'cos there's not much
	// chance it'll fire, but meh, just put the code in!
	try {
	    sdl.open(af);
	} catch (LineUnavailableException e) {
	    e.printStackTrace();
	    return;
	}

	/*
	 * When you consider that the original author doubtless cribbed the math from a
	 * textbook, the fact that he or she screwed up everything else, is really not
	 * very impressive. However, one obvious enhancement over the current version
	 * would be to include an amplitude envelope generator that varies vol based on
	 * i. Any takers?
	 */
	sdl.start();
	for (int i = 0; i < msecs * MULTIPLIER; i++) {
	    double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
	    buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
	    sdl.write(buf, 0, 1);
	}

	// Nice safe close-down (yes, I actually liked this bit - no data bytes were
	// harmed during the making of this exit)
	sdl.drain();
	sdl.stop();
	sdl.close();
    }
}
