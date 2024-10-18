package org.university.model;

public class MD5 {
    private String initMessage;
    private String fullBinaryMessage;

    public MD5(String message) {
        this.initMessage = message;
    }

    public String convertCharacterToBinaryString(char character) {
        return String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');
    }

    public void build(String message) {

        StringBuilder builder = new StringBuilder();
        int basicMultiple = 448;
        int fullMultiple = 512;

        char[] characters = message.toCharArray();
        for(char character: characters) {
            builder.append(convertCharacterToBinaryString(character));
        }
    }

    public void addSupplement() {

    }

}
