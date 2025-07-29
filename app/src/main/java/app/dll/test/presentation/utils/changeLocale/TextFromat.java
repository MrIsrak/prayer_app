package app.dll.test.presentation.utils.changeLocale;

public class TextFromat {
    public static String cleanFootnotes(String raw) {
        return raw
                .replaceAll("<sup[^>]*footnote-marker[^>]*>.*?</sup>", "")
                .replaceAll("<sup[^>]*endFootnote[^>]*>.*?</sup>", "")
                .replaceAll("<sup>.*?</sup>", "")
                .replaceAll("<i[^>]*footnote[^>]*>.*?</i>", "")
                .replaceAll("\\s*<br>\\s*", "<br>")
                .replaceAll(" +", " ")
                .trim();
    }

}
