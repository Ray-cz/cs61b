

import synthesizer.GuitarString;
public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        GuitarString[] keyboard = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < keyboard.length; i++) {
            keyboard[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index > 0) {
                    keyboard[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString key: keyboard) {
                sample += key.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString key: keyboard) {
                key.tic();
            }
        }
    }
}
