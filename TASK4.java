import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    private ArrayList<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner;
    private Timer timer;
    private boolean timeUp;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
        scanner = new Scanner(System.in);
        timer = new Timer();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            timeUp = false;
            Question q = questions.get(currentQuestionIndex);
            System.out.println(q.question);
            for (int i = 0; i < q.options.length; i++) {
                System.out.println((i + 1) + ": " + q.options[i]);
            }
            startTimer();
            int userAnswer = getUserAnswer();
            if (!timeUp) {
                checkAnswer(userAnswer);
            } else {
                System.out.println("Time is up! Moving to the next question.");
            }
        }
        displayResult();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true;
                System.out.println("\nTime's up!");
                timer.cancel();
                timer.purge();
            }
        }, 10000); // 10 seconds for each question
    }

    private int getUserAnswer() {
        int answer = -1;
        while (!timeUp && (answer < 1 || answer > 4)) {
            System.out.print("Your answer: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
            } else {
                scanner.next(); // clear the invalid input
            }
        }
        return answer;
    }

    private void checkAnswer(int userAnswer) {
        if (userAnswer == questions.get(currentQuestionIndex).correctAnswer) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is " + questions.get(currentQuestionIndex).correctAnswer);
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        
        // Add questions to the quiz
        quiz.addQuestion(new Question(
            "What is the capital of France?",
            new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
            3
        ));
        quiz.addQuestion(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"},
            2
        ));
        quiz.addQuestion(new Question(
            "Who wrote 'Hamlet'?",
            new String[]{"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. J.K. Rowling"},
            2
        ));
        
        // Start the quiz
        quiz.start();
    }
}
