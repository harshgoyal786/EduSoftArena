/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

/**
 *
 * @author mayank
 */
public class Question {
    
    private int qId;
    private int qType;
    private String description;
    private int complexity;
    private int topicId;
    private int presentedBy;
    private int verifiedBy;

    /**
     * @return the qTd
     */
    public int getqId() {
        return qId;
    }

    /**
     * @param qTd the qTd to set
     */
    public void setqId(int qId) {
        this.qId = qId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the complexity
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * @param complexity the complexity to set
     */
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    /**
     * @return the topicId
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the presentedBy
     */
    public int getPresentedBy() {
        return presentedBy;
    }

    /**
     * @param presentedBy the presentedBy to set
     */
    public void setPresentedBy(int presentedBy) {
        this.presentedBy = presentedBy;
    }

    /**
     * @return the verifiedBy
     */
    public int getVerifiedBy() {
        return verifiedBy;
    }

    /**
     * @param verifiedBy the verifiedBy to set
     */
    public void setVerifiedBy(int verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * @return the qType
     */
    public int getqType() {
        return qType;
    }

    /**
     * @param qType the qType to set
     */
    public void setqType(int qType) {
        this.qType = qType;
    }

   
    
}
