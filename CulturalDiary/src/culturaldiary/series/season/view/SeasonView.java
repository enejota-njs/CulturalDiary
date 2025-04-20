package culturaldiary.series.season.view;

import culturaldiary.series.season.model.SeasonModel;

public class SeasonView {
    public void registrationMsg(int a, int b, int c) {
        switch (a) {
            case 1 : {
                System.out.print("\nDigite o gênero da " + c + "° temporada: ");
                break;
            }
            case 2 : {
                System.out.print("\nQuantas pessoas tem no elenco da " + c + "° temporada ? ");
                break;
            }
            case 3 : {
                System.out.print("\n" + b + "° pessoa da " + c + "° temporada: ");
                break;
            }
            case 4 : {
                System.out.print("\nDigite o ano de lançamento da " + c + "° temporada: ");
                break;
            }
            case 5 : {
                System.out.println("\nDeve haver pelo menos 1 pessoa.");
                break;
            }
            default:
                break;
        }
    }

    public void seasonInformation(SeasonModel season) {
        System.out.printf("| %-23s -> %s\n", "Temporada n°", season.getSeasonIndex());
        System.out.printf("| %-23s -> %s\n", "Gênero", season.getGenre());
        System.out.printf("| %-23s -> %s\n", "Elenco", season.getCastAsString());
        System.out.printf("| %-23s -> %d\n", "Ano de lançamento", season.getYearSeason());

        if (season.getSeasonReview() == null) {
            System.out.printf("| %-23s -> %s\n", "Avaliação", "Temporada não avaliada");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-23s -> %s\n", "Já foi assistido?", season.getSeasonReview().isConsumed() ? "Sim" : "Não");
            System.out.printf("| %-23s -> %.2f\n", "Nota", season.getSeasonReview().getScore());
            System.out.printf("| %-23s -> %s\n", "Data de visualização", season.getSeasonReview().getConsumptionDate());
            System.out.printf("| %-23s -> %s\n", "Comentários", season.getSeasonReview().getComment());
            System.out.println("+-----------------------+");
        }
    }

    public void evaluationMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.print("\nVocê já assistiu essa temporada ? ");
                break;
            }
            case 2 : {
                System.out.println("\nEssa temporada não pode ser avaliada.");
                break;
            }
            case 3 : {
                System.out.println("\nEssa temporada já foi avaliada.");
                System.out.print("Deseja avaliar novamente ? ");
                break;
            }
            case 4 : {
                System.out.print("\nDigite uma nota entre 1 e 5: ");
                break;
            }
            case 5 : {
                System.out.println("\nO valor precisa estar entre 1 e 5.");
                break;
            }
            case 6 : {
                System.out.print("\nDigite a data de visualização (dd/mm/yyyy): ");
                break;
            }
            case 7 : {
                System.out.print("\nDigite os comentários: ");
                break;
            }
            case 8 : {
                System.out.println("\nAvaliação cadastrada.");
                break;
            }
            case 9 : {
                System.out.println("\nInválido.");
                System.out.println("Digite Sim ou Não.");
                System.out.println("Tente novamente.");
                break;
            }
            case 10 : {
                System.out.println("\nPor favor, digite um número válido.");
                break;
            }
            default:
                break;
        }
    }

    public void invalidYearMsg(int a, int start, int end) {
        switch (a) {
            case 1 : {
                System.out.println("\nO valor precisa ser um número inteiro.");
                System.out.println("Tente novamente.");
                break;
            }
            case 2 : {
                System.out.println("\nO valor precisa estar entre " + start + " e " + end + ".");
                System.out.println("Tente novamente.");
                break;
            }
            default:
                break;
        }
    }

    public void invalidDateMsg(int a, int b) {
        switch (a) {
            case 1 : {
                System.out.println("\nNão é permitido inserir datas futuras.");
                System.out.println("Tente novamente.");
                break;
            }
            case 2 : {
                System.out.println("\nData inválida.");
                System.out.println("Tente novamente.");
                break;
            }
            case 3 : {
                System.out.println(String.format("\nO ano de lançamento da temporada é %d.", b));
                System.out.println("Digite um valor depois do ano de lançamento.");
                System.out.println("Tente novamente.");
                break;
            }
            default:
                break;
        }
    }

    public void invalidValueMsg() {
        System.out.println("\nInválido.");
        System.out.println("Tente novamente.");

    }

    public void emptyValueMsg() {
        System.out.println("\nValor vazio.");
        System.out.println("Tente novamente.");
    }
}