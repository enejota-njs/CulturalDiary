package culturaldiary.main.model;

import culturaldiary.media.controller.MediaController;

public class CulturalDiaryModel {
    private MediaController mediaController = new MediaController();

    public MediaController getMediaController() {
        return mediaController;
    }

    public void setMediaController(MediaController mediaController) {
        this.mediaController = mediaController;
    }
}