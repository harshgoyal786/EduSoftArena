/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mooc.beans;

/**
 *
 * @author mayank
 */
public class QuestionHistory {
    
    private int id;
    private int attempts;
    private int correctAttempt;
    private int incorrectAttempt;
    private float average;
    private Question question;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @param attempts the attempts to set
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * @return the correctAttempt
     */
    public int getCorrectAttempt() {
        return correctAttempt;
    }

    /**
     * @param correctAttempt the correctAttempt to set
     */
    public void setCorrectAttempt(int correctAttempt) {
        this.correctAttempt = correctAttempt;
    }

    /**
     * @return the incorrectAttempt
     */
    public int getIncorrectAttempt() {
        return incorrectAttempt;
    }

    /**
     * @param incorrectAttempt the incorrectAttempt to set
     */
    public void setIncorrectAttempt(int incorrectAttempt) {
        this.incorrectAttempt = incorrectAttempt;
    }

    /**
     * @return the average
     */
    public float getAverage() {
        return average;
    }

    /**
     * @param average the average to set
     */
    public void setAverage(float average) {
        this.average = average;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
}
