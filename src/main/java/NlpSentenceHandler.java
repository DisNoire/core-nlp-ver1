import edu.stanford.nlp.simple.Sentence;

import java.util.List;
import java.util.Optional;

public class NlpSentenceHandler {

    public static List<String> getNerTags(Sentence sentence){
        return sentence.nerTags();
    }

    public static List<String> getPosTags(Sentence sentence){
        return sentence.posTags();
    }

    public static List<Optional<Integer>> getGovernors(Sentence sentence){
        return sentence.governors();
    }

    public static List<Optional<String>> getIncomingDependencyLabels(Sentence sentence){
        return sentence.incomingDependencyLabels();
    }
}
