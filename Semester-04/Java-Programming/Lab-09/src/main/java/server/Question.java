package server;

public class Question {
    private String questionContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Question(String questionContent, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionContent = questionContent;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer.trim().toUpperCase();
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return questionContent + "\n" +
                "A)" + optionA + "\n" +
                "B)" + optionB + "\n" +
                "C)" + optionC + "\n" +
                "D)" + optionD;
    }
}
