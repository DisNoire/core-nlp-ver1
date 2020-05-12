import edu.stanford.nlp.simple.Sentence;

import java.util.List;
import java.util.Optional;

public class SentenceWrapper {
    private volatile Sentence sentence;
    private volatile List<Optional<Integer>> governors;
    private volatile List<Optional<String>> dependecies;
    private volatile List<String> nerTags;
    private volatile List<String> posTags;

    public SentenceWrapper(Sentence sentence){
        this.sentence = sentence;
    }

    public synchronized void setGovernors(Sentence sentence) {
        this.governors = NlpSentenceHandler.getGovernors(sentence);
    }

    public synchronized void setDependecies(Sentence sentence) {
        this.dependecies = NlpSentenceHandler.getIncomingDependencyLabels(sentence);
    }

    public synchronized void setNerTags(Sentence sentence) {
        this.nerTags = NlpSentenceHandler.getNerTags(sentence);
    }

    public synchronized void setPosTags(Sentence sentence) {
        this.posTags = NlpSentenceHandler.getPosTags(sentence);
    }

    public synchronized Sentence getSentence() {
        return sentence;
    }

    public synchronized List<Optional<Integer>> getGovernors() {
        return governors;
    }

    public synchronized List<Optional<String>> getDependecies() {
        return dependecies;
    }

    public synchronized List<String> getNerTags() {
        return nerTags;
    }

    public synchronized List<String> getPosTags() {
        return posTags;
    }
}
