package app.revanced.integrations.youtube.utils;

public final class ByteTrieSearch extends TrieSearch<byte[]> {

    public ByteTrieSearch() {
        super(new ByteTrieNode());
    }

    /**
     * @return If the pattern is valid to add to this instance.
     */
    public static boolean isValidPattern(byte[] pattern) {
        for (byte b : pattern) {
            if (TrieNode.isInvalidRange((char) b)) {
                return false;
            }
        }
        return true;
    }

    private static final class ByteTrieNode extends TrieNode<byte[]> {
        ByteTrieNode() {
            super();
        }

        ByteTrieNode(char nodeCharacterValue) {
            super(nodeCharacterValue);
        }

        @Override
        TrieNode<byte[]> createNode(char nodeCharacterValue) {
            return new ByteTrieNode(nodeCharacterValue);
        }

        @Override
        char getCharValue(byte[] text, int index) {
            return (char) text[index];
        }

        @Override
        int getTextLength(byte[] text) {
            return text.length;
        }
    }
}