package Blackjack;

import java.util.concurrent.CountDownLatch;
import Game.GameWriteable;
import processing.core.PApplet;


public class BlackjackGame extends PApplet implements GameWriteable  { //Blackjack game class
    Blackjack cardGame = new Blackjack();
    CountDownLatch latch = new CountDownLatch(1);
    int timer = 0;

    public void play() {
        System.out.println("\n---Blackjack---");
        PApplet.runSketch(new String[] {"Blackjack.GameFunctions"}, this);

        try {
            latch.await();
        } catch (InterruptedException e) {
    
            e.printStackTrace();
        }
    }

    @Override
    public void settings() {
        initialize();
    }

    @Override
    public void draw() {
        imageMode(CENTER);
        image(Blackjack.backgroundImg, width / 2, height / 2, 750, 700); // Background

        if (cardGame.page == 0) { // START/DEAL PAGE
            textAlign(CENTER, CENTER);
            textSize(50);
           
            fill(255);
            text("LET'S PLAY BLACKJACK", width / 2, 100);

            image(Blackjack.deckImg, width / 2, height / 2, 160, 200);

            checkHover(cardGame.dealButton);
            cardGame.dealButton.draw(this);

        } else if (cardGame.page == 1) { // GAMEPLAY PAGE
            cardGame.dealerHand.draw(this);
            cardGame.playerOneHand.draw(this);
            image(Blackjack.deckImg, width / 2, height / 2, 160, 200);

            gamePlay();

        } else if (cardGame.page == 2) { // ROUND RESULTS PAGE
            cardGame.dealerHand.draw(this);
            cardGame.playerOneHand.draw(this);

            fill(100, 100, 100, 200);
            rect(0, 0, 750, 700);

            gamePlayOver();

        } else if (cardGame.page == 3) {
            endPage();
            latch.countDown();
        }
    }

    @Override
    public void mousePressed() {
        cardGame.handleButtonClick(mouseX, mouseY);
    }

    void initialize() { // Everything that goes into settings
        size(Blackjack.width, Blackjack.height);
        Blackjack.deckImg = loadImage("Blackjack/deckImg.png");
        Blackjack.backgroundImg = loadImage("Blackjack/background.jpg");
        cardGame.initializeGame();
        for (Card c : cardGame.deck) {
            c.img = loadImage("Blackjack/cardFaces/" + c.value + "_of_" + c.suit + ".png");
        }
    }

    public void gamePlay() {
        if (cardGame.playerOneTurn) {
            if (cardGame.gameActive) {
                checkHover(cardGame.hitButton);
                checkHover(cardGame.standButton);
                cardGame.hitButton.draw(this);
                cardGame.standButton.draw(this);
            }

            if (cardGame.playerOneHand.calculateScore() > 21) { // Wait a few moments before declaring bust, so user can
                                                                // realize
                cardGame.gameActive = false; // You can no longer hit or stand
                if (timer == 60) {
                    timer = 0;
                    cardGame.page = 2;
                    cardGame.playerOneTurn = false;
                }
                timer++;
            }

        } else { // Dealers turn
            push();
            textAlign(CENTER, CENTER);
            text("Dealer's turn...", width / 2, 50);
            pop();

            if (timer == 60) { // Dealer takes a few moments to draw a card
                timer = 0;
                if (cardGame.dealerHand.calculateScore() < 17) {
                    cardGame.handleComputerTurn();
                } else {
                    cardGame.page = 2;
                }
            }
            timer++;
        }

    }

    public void gamePlayOver() { // Once round is over
        fill(255);
        textAlign(CENTER, CENTER);

        if (!cardGame.roundOver) { // Only run once per round, so balance is not updated every iteration of draw
            cardGame.updateBalance(); // roundOver becomes true
        }

        textSize(30);
        text(cardGame.determineWinner(), width / 2, height / 2); // Print ending message

        textAlign(RIGHT, TOP); // Display balance
        if (cardGame.playerMoney < 0) {
            text("Profit: -$" + cardGame.playerMoney * -1, width - 10, 10);
        } else {
            text("Profit: $" + cardGame.playerMoney, width - 10, 10);
        }

        checkHover(cardGame.againButton); // Buttons
        checkHover(cardGame.endButton);
        cardGame.againButton.draw(this);
        cardGame.endButton.draw(this);
    }

    public void endPage() { // Summary page; reports money earned/lost

        fill(255);
        textAlign(CENTER, CENTER);
        textSize(40);
        if (cardGame.playerMoney > 0) { // Final message
            text("You're a pro gambler! You earned $" + cardGame.playerMoney + "!", width / 2, height / 2);
        } else if (cardGame.playerMoney == 0) {
            text("You earned nothing!", width / 2, height / 2);
        } else {
            text("Better quit gambling! You lost $" + -1 * cardGame.playerMoney + "!", width / 2, height / 2);
        }

        latch.countDown();
    }

    void checkHover(Button button) { // Changes color of button if mouse is over it
        float[] rectColor = { 255, 0, 0 };
        float[] textColor = { 255, 255, 255 };
        float[] hoverRectColor = { 255, 255, 255 };
        float[] hoverTextColor = { 255, 0, 0 };

        if (button.isClicked(mouseX, mouseY)) {
            button.rectColor = hoverRectColor;
            button.textColor = hoverTextColor;
        } else {
            button.rectColor = rectColor;
            button.textColor = textColor;

        }
    }

    public String getGameName(){
        return "Blackjack";
    }

    public String getScore(){
        return String.valueOf(cardGame.playerMoney) + " dollars";
    }

    public boolean isHighScore(String score, String currentHighScore){
        if (currentHighScore == null){
            return true;
        } else{
        return Integer.valueOf(score.replace(" dollars", "")) > Integer.valueOf(currentHighScore.replace(" dollars", ""));
        }
    }

}
