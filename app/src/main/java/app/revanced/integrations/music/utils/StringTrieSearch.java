package app.revanced.integrations.music.utils;

/**
 * Text pattern searching using a prefix tree (trie).
 */
public final class StringTrieSearch extends TrieSearch<String> {

    public StringTrieSearch() {
        super(new StringTrieNode());
    }

    /**
     * @return If the pattern is valid to add to this instance.
     */
    public static boolean isValidPattern(String pattern) {
        for (int i = 0, length = pattern.length(); i < length; i++) {
            if (TrieNode.isInvalidRange(pattern.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static final class StringTrieNode extends TrieNode<String> {
        StringTrieNode() {
            super();
        }

        StringTrieNode(char nodeCharacterValue) {
            super(nodeCharacterValue);
        }

        @Override
        TrieNode<String> createNode(char nodeValue) {
            return new StringTrieNode(nodeValue);
        }

        @Override
        char getCharValue(String text, int index) {
            return text.charAt(index);
        }

        @Override
        int getTextLength(String text) {
            return text.length();
        }
    }
}