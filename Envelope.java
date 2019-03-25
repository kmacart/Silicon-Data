/*
 * Class Envelope: Provides ADSR envelopes for Tone Class
 * Each Envelope consists of 4 Twains: one each for Attack, Decay, Sustain and Release
 * Constructors are provided for 4 Twains, 4 duration/level pairs, or ADSHR
 * Durations are denominated in milliseconds, level in normalised volume (0.0d ... 1.0d)
 * There is also an accessor method to get the total duration of a Tone
 */

public class Envelope {
    private Twain attack; // Duration and level for attack phase
    private Twain decay; // Duration and level for decay phase
    private Twain sustain; // Duration and level for sustain phase
    private Twain release; // Duration and level for release phase

    // Constructor for Envelope Class - requires 4 Twains: one each for
    // Attack, Decay, Sustain and Release
    public Envelope(Twain attack, Twain decay, Twain sustain, Twain release) {
	this.attack = attack;
	this.decay = decay;
	this.sustain = sustain;
	this.release = release;
    }

    // Alternative constructor for Envelope Class - requires 4 duration/level pairs:
    // one each for Attack, Decay, Sustain and Release
    public Envelope(int attackDuration, double attackLevel, int decayDuration, double decayLevel, int sustainDuration,
	    double sustainLevel, int releaseDuration, double releaseLevel) {
	attack.duration = attackDuration;
	attack.level = attackLevel;
	decay.duration = decayDuration;
	decay.level = decayLevel;
	sustain.duration = sustainDuration;
	sustain.level = sustainLevel;
	release.duration = releaseDuration;
	release.level = releaseLevel;
    }

    // Alternative constructor for Envelope Class - requires Attack, Decay, Sustain,
    // Hold and Release. Attack, Decay, Hold and Release are all durations
    // (in milliseconds). Sustain is a level (0.0d ... 1.0d).
    public Envelope(int attackDuration, int decayDuration, double sustainLevel, int sustainDuration,
	    int releaseDuration) {
	attack.duration = attackDuration;
	attack.level = 1.0d;
	decay.duration = decayDuration;
	decay.level = sustainLevel;
	sustain.duration = sustainDuration;
	sustain.level = sustainLevel;
	release.duration = releaseDuration;
	release.level = 0.0d;
    }

    // getToneDuration()
    public int getToneDuration(Envelope e) {
	return e.attack.duration + e.decay.duration + e.sustain.duration + e.release.duration;
    }

}
