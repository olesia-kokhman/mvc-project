package org.university.model;

public class MD5 {
    private String fullBinaryMessage;

    private int A = 0x67452301;
    private int B = 0xefcdab89;
    private int C = 0x98badcfe;
    private int D = 0x10325476;

    int fullMultiple = 512;

    public static final int[] T = {
            0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
            0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
            0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
            0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
            0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
            0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
            0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
            0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
            0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
            0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
            0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
            0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
            0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
            0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
            0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
            0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391
    };

    public static final int[] S = {
            7, 12, 17, 22,
            5, 9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };

    public MD5(String message) {
        fullBinaryMessage = addSupplement(buildInitBinaryMessage(message));
    }

    public String getFullBinaryMessage() {
        return this.fullBinaryMessage;
    }

    private String buildInitBinaryMessage(String message) {
        StringBuilder builder = new StringBuilder();

        char[] characters = message.toCharArray();
        for(char character: characters) {
            builder.append(String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0'));
        }

        System.out.println(builder);
        return builder.toString();
    }

    private String addSupplement(String message) {

        StringBuilder builder = new StringBuilder();
        builder.append(message);
        int basicMultiple = 448;

        int one = 1;

        int remainder = message.length() % fullMultiple;

        int addNumber = 0;
        if(remainder < basicMultiple) {
            addNumber = basicMultiple - remainder - one;
        } else {
            addNumber = (basicMultiple + fullMultiple) - remainder - one;
        }

        builder.append("1");
        builder.append("0".repeat(addNumber));

        String binaryLength = String.format("%64s", Integer.toBinaryString(message.length())).replace(' ', '0');
        builder.append(binaryLength);

        return builder.toString();
    }

    public String compression() {
        TriFunction<Integer, Integer, Integer, Integer> f = (b, c, d) -> (b & c) | (~b & d);
        TriFunction<Integer, Integer, Integer, Integer> g = (b, c, d) -> (b & d) | (c & ~d);
        TriFunction<Integer, Integer, Integer, Integer> h = (b, c, d) -> (b ^ c ^ d);
        TriFunction<Integer, Integer, Integer, Integer> i = (b, c, d) -> (c ^ (b | ~d));

        int counter = 0;
        int wordLength = 32;
        int[] X = new int[fullBinaryMessage.length() / wordLength];
        int blocks = fullBinaryMessage.length() / fullMultiple;
        for(int block = 1; block <= blocks; block++) {
            for(int start = counter * fullMultiple; start < block * fullMultiple; start += wordLength) {
                String word = fullBinaryMessage.substring(start, start + 31);
                int word16 = Integer.parseInt(word);
                X[counter] = word16;
                counter++;
            }
        }

        int maxRound = 64;
        int currentX = X[0];
        TriFunction<Integer, Integer, Integer, Integer> currentFunction = f;

        for(int currentRound = 0; currentRound < maxRound; currentRound++) {
            if(currentRound < 16) {
                currentFunction = f;
                currentX = X[currentRound]; // 0..15
            } else if(currentRound >= 16 && currentRound < 32) {
                currentFunction = g;
                currentX = X[(1 + 5 * (currentRound % 16)) % 16];
            } else if(currentRound >= 32 && currentRound < 48) {
                currentFunction = h;
                currentX = X[(5 + 3 * (currentRound % 16)) % 16];
            } else if(currentRound >= 48) {
                currentFunction = i;
                currentX = X[7 * (currentRound % 16) % 16];
            }

            int sIndex = 1; // додати логіку визначення s

            A = B + Integer.rotateLeft(A + currentFunction.apply(B, C, D) + currentX, S[sIndex]);

        }

        return null;
    }

}
