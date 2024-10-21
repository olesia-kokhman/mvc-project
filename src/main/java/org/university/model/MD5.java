package org.university.model;

public class MD5 {
    private String fullBinaryMessage;

    private int A = 0x67452301;
    private int B = 0xefcdab89;
    private int C = 0x98badcfe;
    private int D = 0x10325476;

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

    private String addSupplement(String message) { // add supplement

        StringBuilder builder = new StringBuilder();
        builder.append(message);
        int basicMultiple = 448;
        int fullMultiple = 512;
        int one = 1;

        int remainder = message.length() % fullMultiple;

        int addNumber = 0;
        if(remainder < basicMultiple) {
            addNumber = basicMultiple - remainder - one;
        } else { // 456
            addNumber = (basicMultiple + fullMultiple) - remainder - one;
        }

        builder.append("1");
        builder.append("0".repeat(addNumber));

        String binaryLength = String.format("%64s", Integer.toBinaryString(message.length())).replace(' ', '0');
        builder.append(binaryLength);

        return builder.toString();
    }

    private void compression() {
        TriFunction<Integer, Integer, Integer, Integer> f = (b, c, d) -> (b & c) | (~b & d);
        TriFunction<Integer, Integer, Integer, Integer> g = (b, c, d) -> (b & d) | (c & ~d);
        TriFunction<Integer, Integer, Integer, Integer> h = (b, c, d) -> (b ^ c ^ d);
        TriFunction<Integer, Integer, Integer, Integer> i = (b, c, d) -> (c ^ (b | ~d));
    }


    public String hash() {
        return null;
    }


}
