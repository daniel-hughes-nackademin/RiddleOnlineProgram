package Server;
    enum State{
        WAITING,
        ASKED_FOR_RIDDLE,
        SENT_RIDDLE
    }

public class Protocol {

        String[] riddles = {"I am something people love or hate. I change peoples appearances and thoughts. If a person takes care of his/her self I will go up even higher.#To some people I will fool them. To others I am a mystery. Some people might want to try and hide me but I will show. No matter how hard people try I will never go down. What am I?",
                            "If a rooster laid a brown egg and a white egg, what kind of chicks would hatch?",
                            "Why can you not trust Atoms?"};

        String[] answers = {"Age",
                            "None. Roosters don't lay eggs",
                            "Because they make up everything"};

        int currentRiddle = 0;


    private State state = State.WAITING;

    public String processInput(String input){
        String output = null;
        String goodbyeText = "Fine. Be that way. Goodbye.";

        switch (state){
            case WAITING:
                output = "Want to hear a riddle? I promise you it's good. Maybe.";
                state = State.ASKED_FOR_RIDDLE;
                break;
            case ASKED_FOR_RIDDLE:
                if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("yeah") || input.equalsIgnoreCase("sure") || input.equalsIgnoreCase("if you must") || input.equalsIgnoreCase("fine") || input.equalsIgnoreCase("ok i guess")){
                    output = riddles[currentRiddle];
                    state = State.SENT_RIDDLE;
                }
                else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("nah") || input.equalsIgnoreCase("i'd rather die") || input.equalsIgnoreCase("please don't") || input.equalsIgnoreCase("do I look that bored to you?") || input.equalsIgnoreCase("hell no") || input.equalsIgnoreCase("please no more")){
                    output = goodbyeText;
                }
                else {
                    output = "Please say \"Y\" or \"yes\" (not case-sensitive).";
                }
                break;
            case SENT_RIDDLE:
                if(input.equalsIgnoreCase(answers[currentRiddle])){
                    output = "That is correct! Want another riddle?";
                    currentRiddle++;
                    if(currentRiddle == riddles.length)
                        currentRiddle = 0;
                    state = State.ASKED_FOR_RIDDLE;
                }
                else if (input.equalsIgnoreCase("i give up") || input.equalsIgnoreCase("please no more") || input.equalsIgnoreCase("this is retarded") || input.equalsIgnoreCase("this is impossible") || input.equalsIgnoreCase("i don't know")){
                    output = goodbyeText;
            }
                else {
                    output = "That is not correct. We may be picky but please give us the exact answer. Try again.";
                }
                break;
        }

        return output;
    }
}
