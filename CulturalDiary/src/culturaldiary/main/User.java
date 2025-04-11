package culturaldiary.main;

public class User {
    public static void main(String[] args) {
        CulturalDiaryInterface diaryCultural = new CulturalDiary();

        diaryCultural.open();
        diaryCultural.close();
    }
}