import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private Document document;
    private List<SentenceWrapper> sentences = new ArrayList<>();

    public DataHandler(String text){
        this.document = new Document(text);
    }

    public void setSentences() {
        List<Sentence> sentenceList = document.sentences();
        for (Sentence sentence: sentenceList) {
            SentenceWrapper sentenceWrapper = new SentenceWrapper(sentence);
            new NlpSentenceThreadHandler("governors",sentenceWrapper).start();
            new NlpSentenceThreadHandler("dependencies",sentenceWrapper).start();
            new NlpSentenceThreadHandler("ner",sentenceWrapper).start();
            new NlpSentenceThreadHandler("pos",sentenceWrapper).start();
            sentences.add(sentenceWrapper);

        }
    }
}
