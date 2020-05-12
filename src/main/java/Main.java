public class Main {

    public static void main(String[] args){
        DataHandler textData = new DataHandler("Alex like drinking beer now");
        textData.setSentences();
        /*for (SentenceWrapper sentenceWrapper: textData.getSentences()){
            System.out.println();
            for (String word: sentenceWrapper.getSentence().words()){
                //Object[] sent = sentenceWrapper.getSentence().words().toArray();
                //System.out.println(sent[0]);
                System.out.println(word);
            }
        }*/
        textData.mapData();
        textData.executeClasses();
    }
}