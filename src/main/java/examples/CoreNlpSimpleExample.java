package examples;

import edu.stanford.nlp.simple.*;

import java.util.List;
import java.util.Optional;

/*
Class that shows how SimpleNlp works
 */
public class CoreNlpSimpleExample {

    public static void main(String[] args){
        Sentence sentence = new Sentence("Alex like drinking beer now.");
        //Little boat sailing through the sea
        //Alex like drinking beer now.
        //After John drank his coffee, he left the coffee shop.

        List<Optional<Integer>> governors = sentence.governors();
        List<Optional<String>> dependecies = sentence.incomingDependencyLabels();
        List<String> nerTags = sentence.nerTags();
        List<String> posTags = sentence.posTags();
        for (Optional<Integer> governor : governors) {
            governor.ifPresent(System.out::println);
        }
        for (Optional<String> dependency : dependecies) {
            dependency.ifPresent(System.out::println);
        }
        for (String nerTag : nerTags) {
            System.out.println(nerTag);
        }
        for (String posTag : posTags) {
            System.out.println(posTag);
        }
        System.out.println(sentence.dependencyGraph().toDotFormat());
        System.out.println(sentence.dependencyGraph().toString());
        System.out.println(sentence.dependencyGraph().toCompactString());
        System.out.println(sentence.dependencyGraph().toFormattedString());

        Document document = new Document("");
        document.sentences();
    }
}
