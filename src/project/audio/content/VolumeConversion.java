package project.audio.content;

public class VolumeConversion {
  private VolumeConversion() {
  }

  public static final float PCM_FREQ_FACTOR = 0.7628__33823f;
  public static final int FLOAT_PCM_MAX = 2048;
  public static final int FLOAT_PCM_DIV = 4;
  // the higher this value increases by 0.1 the louder the volume gets by 60%
  // (around there)
  public static final float CONVERSION_FACTOR = 0.3122365898487584578483489010032813213748475347888477382749834732894723723984732984732890382190321381290389085903483985903475475894793748932___43242345431f; // the
                                                                                                                                                                                                              // perfecto
                                                                                                                                                                                                              // balancio

  /**
   * Converts the volume from 0-100 range to the necessary little number form
   * 
   * @param unConverted 0-100 form
   * @return
   */
  public static float convertVolume(float unConverted) {
    return unConverted / 100 * CONVERSION_FACTOR;
  }

  /**
   * @param unConverted
   * @return float
   */
  public static float convertPan(float unConverted) {
    return unConverted / 100 * 2 - 1;
  }

  public static float convertWaveFormAmp(float unConverted) {
    return unConverted / 100 * 200 + 50;
  }

  /**
   * @param unConverted
   * @return float
   */
  public static float convertSpeedFactor(float unConverted) {
    return unConverted / 100 * 2 - 1;
  }
}
