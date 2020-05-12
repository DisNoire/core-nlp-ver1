import edu.stanford.nlp.simple.Sentence;

public class NlpSentenceThread implements Runnable{
    private Thread t;
    private String threadName;
    private SentenceWrapper sentence;

    NlpSentenceThread(String name, SentenceWrapper sentence) {
        threadName = name;
        this.sentence = sentence;
        System.err.println("Creating " +  threadName );
    }

    public void run() {
        System.err.println("Running " +  threadName );
        try {
            switch (threadName){
                case "governors": sentence.setGovernors(sentence.getSentence()); break;
                case "dependencies": sentence.setDependecies(sentence.getSentence()); break;
                case "ner": sentence.setNerTags(sentence.getSentence()); break;
                case "pos": sentence.setPosTags(sentence.getSentence()); break;
            }
        } catch (Exception e) {
            System.err.println("Thread " +  threadName + " interrupted.");
        }
        System.err.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.err.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}