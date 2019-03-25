
/*
 * Class Tone: Simple sound generator for Silicon Project.  It is simple ... Java does all the work.
 * The methods are threaded so you can go: "new Thread(new Tone(int hz, int msecs)).start()" to play a tone.
 * If you wish, you can also set the volume: "new Thread(new Tone(int hz, int msecs, double vol)).start()", 
 * where "vol" is between 0.0d and 1.0d.
 * To find frequencies that correspond to musical notes, look here: https://pages.mtu.edu/~suits/notefreqs.html
 * This Class was adapted from some horrible (mainly non-functional) code at Stack Overflow.
 * Coding credits(!): "https://stackoverflow.com/a/6700039"
 * The original worked at the specific combination of sample rate and frequency it used 
 * (for a unique definition of "worked" -- ie. played a completely different frequency).
 * However, it produced silence at the frequencies normal ears can handle.
 * It also blocked the calling method until the sound was finished playing.
 * Finally, it flicked method-specific errors up-the-chain (don't you hate that?).
 * Our adaptation seems to work for frequencies up to about 11KHz, doesn't block, and handles its own errors.  Cough.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone implements Runnable {
    private float SAMPLE_RATE = 22000f; // Don't mess with this number. You have been warned!
    private int MULTIPLIER = 22; // Yes, it's an int equal to the sample rate divided by 1,000
    private int hz; // Frequency of sound to play
    private int msecs; // Milliseconds to play the sound
    private double vol; // Volume (0.0 ... 1.0) for the sound

    /*
     * This is the constructor for code that just wants to specify frequency and
     * duration.
     */
    public Tone(int hz, int msecs) {
	this.hz = hz;
	this.msecs = msecs;
	this.vol = 1.0d;
    }

    /*
     * This is the constructor for code that wants to specify frequency, duration
     * *and* volume (0.0d ... 1.0d)
     */
    public Tone(int hz, int msecs, double vol) {
	this.hz = hz;
	this.msecs = msecs;
	this.vol = vol;
    }

    /*
     * Accessor method for value of MULTIPLIER Other classes can use it to convert
     * from milliseconds to sampling 'ticks
     */
    public int getMultiplier() {
	return this.MULTIPLIER;
    }

    /*
     * This is the code for a new thread that plays each tone
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
	AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);

	// Sound output lines can be a bit scarce at times - worth a try/catch block
	SourceDataLine sdl;
	try {
	    sdl = AudioSystem.getSourceDataLine(af);
	} catch (LineUnavailableException e) {
	    e.printStackTrace();
	    return;
	}

	/*
	 * We probably could have got away with flicking this one, 'cos there's not much
	 * chance it'll fire if we got the source-data-line OK, but meh, I've put it in
	 * a try/catch block anyway!
	 */
	try {
	    sdl.open(af);
	} catch (LineUnavailableException e) {
	    e.printStackTrace();
	    return;
	}

	/*
	 * When you consider that the original author doubtless cribbed the math from a
	 * textbook, the fact that he or she screwed up everything else, is really not
	 * very impressive. That said, the ADSR envelope generator I've added below is
	 * both simple and inelegant (to be replaced by a better one). NB: At the moment
	 * we assume there's enough room in the buffer for the full note. My PC
	 * allocates 11k, presumably intended to be half a second of mono data at a
	 * 22kHz sample rate. I still need to change this so it will feed the data in
	 * gradually if it fills the buffer. If the generator just ignores your sample
	 * data, this may be the cause.
	 */
	double scalar = SAMPLE_RATE / ((hz << 1) * Math.PI);
	int sample = msecs * MULTIPLIER;
	int sample2 = sample >> 1;
	int sample4 = sample2 >> 1;
	int sample8 = sample4 >> 1;
	byte[] buf = new byte[sample];
	double env;
	for (int i = 0; i < sample; i++) {
	    // Envelope goes 0 ... 1 over each time period
	    env = (i % sample4) / (double) sample4;
	    // Is this Period 1?
	    if (i < (sample4)) {
		// Y: No change needed (Attack phase)
		// N: Is this the first half of Period 2?
	    } else if (i < (sample4 + sample8)) {
		// Y: Invert the envelope (Decay phase)
		env = 1.0d - (env / 2);
		// N: Is this the second half of Period 2?
	    } else if (i < (sample2 + sample4)) {
		// Y: Set envelope = 0.75 (Sustain phase)
		env = 0.75d;
		// N: This is Period 3
	    } else
		// Invert the envelope (Release phase)
		env = 0.75d - (env * 0.75d);
	    double angle = i / scalar;
	    buf[i] = (byte) (Math.sin(angle) * 127.0 * env * vol);
	}
	sdl.start();
	sdl.write(buf, 0, sample);

	// Nice safe close-down (yes, I actually liked these 3 lines - no data bytes
	// were harmed during the making of this exit from the method)
	sdl.drain();
	sdl.stop();
	sdl.close();
    }
}
