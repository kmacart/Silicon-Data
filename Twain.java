/*
 * Twain Class: "struct" class for Envelope Class
 * Each Twain consists of a duration (in milliseconds) and a level (0.0d ... 1.0d).
 * An Envelope therefore consists of 4 Twains: one each for Attack, Decay, Sustain and Release.
 */
public class Twain {
    public int duration;
    public double level;

    // Constructor for Twain Class - requires duration (in milliseconds)
    // and level (0.0d ... 1.0d)
    public Twain(int duration, double level) {
	this.duration = duration;
	this.level = level;
    }
}