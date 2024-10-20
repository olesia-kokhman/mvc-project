package org.university.model;

public class MD5 {
    //private String initBinaryMessage;
    private String fullBinaryMessage;

    public MD5(String message) {
        //this.initBinaryMessage = buildInitBinaryMessage(message);
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

        System.out.println("message - " + message);
        StringBuilder builder = new StringBuilder();
        builder.append(message);
        int basicMultiple = 448;
        int fullMultiple = 512;
        int one = 1;

        int remainder = message.length() % fullMultiple; // 320

        int addNumber = 0;
        if(remainder < basicMultiple) {
            addNumber = basicMultiple - remainder - one; // 448 - 320 - 1 = 127
        } else { // 456
            addNumber = (basicMultiple + fullMultiple) - remainder - one; // (448 + 512) - 456 - 1 = 503
        }

        builder.append("1");
        builder.append("0".repeat(addNumber));

        String binaryLength = String.format("%64s", Integer.toBinaryString(message.length())).replace(' ', '0');

        System.out.println(binaryLength);
        builder.append(binaryLength);

        return builder.toString();
    }

    public String hash() {
        return null;
    }


}
