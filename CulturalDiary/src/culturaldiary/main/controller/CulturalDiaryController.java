package culturaldiary.main.controller;

import culturaldiary.main.model.CulturalDiaryModel;
import culturaldiary.main.view.CulturalDiaryView;

import java.util.Scanner;

public class CulturalDiaryController {
    CulturalDiaryModel diaryModel = new CulturalDiaryModel();
    CulturalDiaryView diaryView = new CulturalDiaryView();

    Scanner input = new Scanner(System.in);

    public boolean homeMenu() {
        String menuOption;
        do {
            diaryView.menuOptions();
            diaryView.chooseAnOption();

            menuOption = input.nextLine().trim();

            if (menuOption.isEmpty()) {
                diaryView.emptyValue();
            }
        } while (menuOption.isEmpty());

        switch (menuOption.toLowerCase()) {
            case "1" :
            case "cadastrar" :
            case "cadastrar mídia" :
            case "cadastrar midia" :
                diaryModel.getMediaController().controlMedia(1);
                break;
            case "2" :
            case "buscar" :
            case "buscar mídia" :
            case "buscar midia" :
                diaryModel.getMediaController().controlMedia(2);
                break;
            case "3" :
            case "listar" :
            case "listar mídia" :
            case "listar midia" :
                diaryModel.getMediaController().controlMedia(3);
                break;
            case "4" :
            case "fechar" :
                diaryView.close();
                return true;
            default:
                diaryView.invalidMessage();
                break;
        }

        return homeMenu();
    }
}
