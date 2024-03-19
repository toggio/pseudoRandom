/*
 * Universal Cross-Platform Multi-Language Pseudo Random Number Generator (pseudoRandom) v0.9
 *
 * A Set of libraries for Pseudo-Random Number Generation across environments
 *
 * X-PRNG is an advanced set of libraries designed to provide developers with tools
 * for generating pseudo-random numbers across various computing environments. It
 * supports a wide range of applications, from statistical analysis and data simulation
 * to secure cryptographic functions. These libraries offer a simple yet powerful
 * interface for incorporating high-quality, pseudo-random numbers into applications
 * written in different programming languages, ensuring consistency and reliability
 * across platforms. 
 *
 * Copyright (C) 2024 under GPL v. 2 license
 * 18 March 2024
 *
 * @author Luca Soltoggio
 * https://www.lucasoltoggio.it
 * https://github.com/toggio/X-PRNG
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
 
/*
 *
 * PseudoRandom Number Generator Java Class
 *
 * This class implements a pseudo-random number generator (PRNG) using a linear
 * congruential generator (LCG) algorithm. 
 *
 */
 
import java.util.zip.CRC32;
import java.time.Instant;

public class pseudoRandom {
    private static Long savedRSeed = null;
    private static Long RSeed = 0L;
    private static final Long a = 1664525L; // Multiplier
    private static Long c = 1013904223L; // Increment
    private static final Long m = 4294967296L; // Modulus (2^32)
    private static int counter = 0;

    /**
     * Constructor to initialize the PRNG with an optional seed.
     * If a string is provided as a seed, it uses crc32 to convert it into an integer.
     * If no seed is provided, it uses the current time.
     *
     * @param seed Optional seed for the PRNG, can be a string or a number.
     */
    public pseudoRandom(Object seed) {
        reSeed(seed);
    }

    /**
     * Method to re-seed the PRNG. This can be used to reset the generator's state.
     *
     * @param seed Optional new seed for the PRNG, can be a string or a number.
     */
    public void reSeed(Object seed) {
        if (seed instanceof String) {
            CRC32 crc = new CRC32();
            crc.update(((String) seed).getBytes());
            RSeed = crc.getValue();
        } else if (seed instanceof Number) {
            RSeed = Math.abs(((Number) seed).longValue());
        } else {
            RSeed = Instant.now().getEpochSecond();
        }
        counter = 0;
    }

    /**
     * Saves the current state of the PRNG for later restoration.
     */
    public void saveStatus() {
        savedRSeed = RSeed;
    }

    /**
     * Restores the PRNG to a previously saved state.
     */
    public void restoreStatus() {
        if (savedRSeed != null) {
            RSeed = savedRSeed;
        }
    }

    /**
     * Generates a pseudo-random integer within a specified range.
     *
     * @param min The lower bound of the range (inclusive).
     * @param max The upper bound of the range (inclusive).
     * @return A pseudo-random integer between min and max.
     */
    public int randInt(int min, int max) {
        CRC32 crc = new CRC32();
        String input = counter + RSeed.toString() + counter;
        crc.update(input.getBytes());
        c = crc.getValue();
        
        RSeed = (RSeed * a + c) % m;
        counter++;
        return (int)((RSeed / (double)m) * (max - min + 1) + min);
    }

    /**
     * Generates a string of pseudo-random bytes of a specified length.
     *
     * @param len The length of the byte string.
     * @param readable If true, ensures the generated bytes are in the readable ASCII range.
     * @return A string of pseudo-random bytes.
     */
    public String randBytes(int len, boolean readable) {
        StringBuilder bytes = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int n;
            if (readable) {
                n = randInt(32, 126); // Readable ASCII range
            } else {
                n = randInt(0, 255); // Full byte range
            }
            bytes.append((char)n);
        }
        return bytes.toString();
    }
}
