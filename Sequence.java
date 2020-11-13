package Oefententamens.JavaKansEen20182019;


public class Sequence {
    public String sequence;

    public Sequence(String sequence) {
        this.sequence = sequence;
    }

    public void setSequence(String sequence) {
        if (sequence.matches("^[ATGC]*$"))
            this.sequence = sequence;
    }

    public String getSequence() {
        return this.sequence;
    }

    public int getLength() {
        return this.sequence.length();
    }

    public float getGCpercentage(){
        int amount_g = 0;
        int amount_c = 0;

        for (int i = 0; i < sequence.length(); i++ ){
            if (i == 'C'){
                amount_c++;
            }
            if (i == 'G'){
                amount_g++;
            }
        }
        float g = amount_g;
        float c = amount_c;
        float percentage = (g + c) / sequence.length() * 100;
        return percentage;
    }
}





