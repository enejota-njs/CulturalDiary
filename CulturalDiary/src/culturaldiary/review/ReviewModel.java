package culturaldiary.review;

public class ReviewModel {
    private float score;
    private String consumptionDate;
    private String comment;

    public ReviewModel(float score, String consumptionDate, String comment) {
        this.score = score;
        this.consumptionDate = consumptionDate;
        this.comment = comment;
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
