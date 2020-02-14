import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import javassist.*;
import java.util.List;
import java.util.Properties;

public class CoreNlpExample {

    public static void main(String[] args) {

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = args[0];

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("Temp");

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                try {
                    //if NE is PERSON create class with given name
                    if (token.get(CoreAnnotations.NamedEntityTagAnnotation.class).equals("PERSON")) {
                        ctClass = pool.makeClass(token.get(CoreAnnotations.TextAnnotation.class));
                        ctClass = ClassPool.getDefault().get(token.get(CoreAnnotations.TextAnnotation.class));
                    }
                    //if POS is VB create a method in class with given name
                    if (token.get(CoreAnnotations.PartOfSpeechAnnotation.class).contains("VB")) {
                        ctClass.addMethod(CtNewMethod.make("public void " +
                                token.get(CoreAnnotations.TextAnnotation.class) + "(){}", ctClass));
                    }
                    //if NE is DATE create field with given name
                    if (token.get(CoreAnnotations.NamedEntityTagAnnotation.class).equals("DATE")) {
                        ctClass.addField(CtField.make("private String " + token.get(CoreAnnotations.NamedEntityTagAnnotation.class)
                                + " = \"" + token.get(CoreAnnotations.TextAnnotation.class) + "\";", ctClass));
                    }
                }catch (Exception e){
                    System.err.println("poopy");
                }

                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println(String.format("Print: word: [%s] pos: [%s] ne: [%s]", word, pos, ne));

            }
        }
        CtField[] ctFields = ctClass.getDeclaredFields();
        for (CtField ctField: ctFields) {
            System.out.println(ctField.toString());
        }
        try {
            ctClass.writeFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}