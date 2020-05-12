import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

import java.util.*;

public class DataHandler {
    private Document document;
    private List<SentenceWrapper> sentences = new ArrayList<>();
    private List<ClassHandler> classes = new ArrayList<>();

    public DataHandler(String text){
        this.document = new Document(text);
    }

    public void executeClasses(){
        for (ClassHandler classHandler : classes){
            classHandler.makeClass();
        }
    }

    public void mapData(){
        for (SentenceWrapper sentenceWrapper : sentences){
            String className = "";
            List<String> subClasses = new ArrayList<>();
            Map<String, String> methodNames = new HashMap<String, String>();
            Map<String, String> attributes = new HashMap<String, String>();
            for (Optional<String> dependency : sentenceWrapper.getDependecies()) {
                if (dependency.get().equals("nsubj")
                        || sentenceWrapper.getNerTags().get(sentenceWrapper.getDependecies().indexOf(dependency)).equals("PERSON")){
                    className = sentenceWrapper.getSentence().words().get(sentenceWrapper.getDependecies().indexOf(dependency));
                }
                if (dependency.get().equals("dobj")){
                    String temp = sentenceWrapper.getSentence().words().get(sentenceWrapper.getDependecies().indexOf(dependency));
                    for (Optional<Integer> governor : sentenceWrapper.getGovernors()){
                        if (governor.get().equals(sentenceWrapper.getDependecies().indexOf(dependency))){
                            if (governor.get() > sentenceWrapper.getDependecies().indexOf(dependency)){
                                temp = temp +
                                        sentenceWrapper.getSentence().words().get(sentenceWrapper.getGovernors().indexOf(governor));
                            }
                            else {
                                temp = sentenceWrapper.getSentence().words().get(sentenceWrapper.getGovernors().indexOf(governor))
                                        + temp;
                            }
                        }
                    }
                    classes.add(new ClassHandler(temp,null,null));
                    subClasses.add(temp);
                }
            }
            for (String posTag : sentenceWrapper.getPosTags()){
                if (posTag.contains("VB")){
                    if (subClasses.isEmpty())
                        methodNames.put(sentenceWrapper.getSentence().words().get(sentenceWrapper.getPosTags().indexOf(posTag)), "");
                    else {
                        String temp = "";
                        for (String subClass : subClasses){
                            String subClassHeader = subClass;
                            subClassHeader.replace(subClass.charAt(0), Character.toUpperCase(subClass.charAt(0)));
                            subClass.replace(subClass.charAt(0), Character.toLowerCase(subClass.charAt(0)));
                            temp += subClassHeader + " " + subClass;
                        }
                        methodNames.put(sentenceWrapper.getSentence().words().get(sentenceWrapper.getPosTags().indexOf(posTag)),
                                temp);
                    }
                }
            }
            classes.add(new ClassHandler(className, attributes, methodNames));
        }
    }

    public void setSentences() {
        List<Sentence> sentenceList = document.sentences();
        for (Sentence sentence: sentenceList) {
            SentenceWrapper sentenceWrapper = new SentenceWrapper(sentence);
            new NlpSentenceThread("governors",sentenceWrapper).start();
            new NlpSentenceThread("dependencies",sentenceWrapper).start();
            new NlpSentenceThread("pos",sentenceWrapper).start();
            sentenceWrapper.setNerTags(sentenceWrapper.getSentence());
            sentences.add(sentenceWrapper);

        }
    }

    public List<SentenceWrapper> getSentences() {
        return sentences;
    }
}
