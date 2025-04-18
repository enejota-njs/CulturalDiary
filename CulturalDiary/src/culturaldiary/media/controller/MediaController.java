package culturaldiary.media.controller;

import culturaldiary.media.model.MediaModel;
import culturaldiary.media.view.MediaView;

import java.util.Scanner;

public class MediaController {
    MediaModel mediaModel = new MediaModel();
    MediaView mediaView = new MediaView();

    Scanner input = new Scanner(System.in);

    public boolean controlMedia(int chosenMethod) {
        switch (chosenMethod) {
            case 1 : {
                int chosenMedia = displayMenuMethods(1);
                switch (chosenMedia) {
                    case 1 :
                        mediaModel.getBookController().registerBook();
                        break;
                    case 2 :

                        break;
                    case 3 :

                        break;
                    default :
                        break;
                }
                break;
            }
            case 2 : {
                int chosenMedia = displayMenuMethods(2);
                switch (chosenMedia) {
                    case 1:
                        mediaModel.getBookController().searchBook();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    default:
                        break;
                }
                break;
            }
            case 3 : {
                int chosenMedia = displayMenuMethods(3);
                switch (chosenMedia) {
                    case 1:
                        mediaModel.getBookController().listBook();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    default:
                        break;
                }
                break;
            }
            default :
                break;
        }
        return true;
    }

    public int displayMenuMethods(int a) {
        switch (a) {
            case 1 : {
                mediaView.menuMethods(1);
                break;
            }
            case 2 : {
                mediaView.menuMethods(2);
                break;
            }
            case 3 : {
                mediaView.menuMethods(3);
                break;
            }
            default:
                break;
        }

        mediaView.chooseAnOption();
        String mediaOption = input.nextLine().trim();

        if (mediaOption.isEmpty()) {
            mediaView.emptyValue();
            return displayMenuMethods(a);
        }

        if (mediaOption.equals("1") || mediaOption.equalsIgnoreCase("livro")) {
            return 1;
        } else if (mediaOption.equals("2") || mediaOption.equalsIgnoreCase("filme")) {
            return 2;
        } else if (mediaOption.equals("3")
                || mediaOption.equalsIgnoreCase("série")
                || mediaOption.equalsIgnoreCase("serie")) {
            return 3;
        } else if (mediaOption.equals("4") || mediaOption.equalsIgnoreCase("menu")
                || mediaOption.equalsIgnoreCase("menu inicial")) {
            return 4;
        } else {
            mediaView.invalidMessage();
            return displayMenuMethods(a);
        }
    }

    public MediaModel getMediaModel() {
        return mediaModel;
    }

    public void setMediaModel(MediaModel mediaModel) {
        this.mediaModel = mediaModel;
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public void setMediaView(MediaView mediaView) {
            this.mediaView = mediaView;
        }
}