package t.model;

public class ReviewModel {
    private boolean isConsumed;
    private float score;
    private String consumptionDate;
    private String comment;

    public ReviewModel(boolean isConsumed, float score, String consumptionDate, String comment) {
        this.isConsumed = isConsumed;
        this.score = score;
        this.consumptionDate = consumptionDate;
        this.comment = comment;
    }

    public boolean isConsumed() {
        return isConsumed;
    }

    public void setConsumed(boolean consumed) {
        isConsumed = consumed;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(String consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
