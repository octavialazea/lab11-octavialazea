package ro.ubb.catalog.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.web.dto.*;

import java.util.Scanner;

@Component
public class ClientConsole {
    @Autowired
    private RestTemplate restTemplate;

    private static final String playerUrl = "http://localhost:8080/api/players";
    private static final String gameUrl = "http://localhost:8080/api/games";
    private static final String zoneUrl = "http://localhost:8080/api/zones";
    private static final String computerUrl = "http://localhost:8080/api/computers";
    private static final String installedUrl = "http://localhost:8080/api/installations";


    public void menu() {
        System.out.println("\nAdd operation - add (entity) (parameters)");
        System.out.println("Update operation - update (entity) (id) (parameters)");
        System.out.println("Remove operation - remove (entity) (id)");
        System.out.println("List operation - list (entity)");
        System.out.println("Help! - help");
        System.out.println("Exit - exit\n");
    }

    public void instructions() {
        System.out.println("\nPlayer has 3 parameters: name dateOfBirth (dd/mm/yyyy) email");
        System.out.println("Game has 5 parameters: name company price rating IDofBestPlayer");
        System.out.println("Computer has 3 parameters: zoneID operatingSystem purchaseDate");
        System.out.println("Zone has 3 parameters: name theme capacity");
        System.out.println("Installed has 3 parameters: gameID computerID performanceGrade");

    }

    public void runConsole(){
        this.menu();
        String command;
        String[] params;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Command>>");
            command = scanner.nextLine();
            params = command.trim().split(" ");
            try {
                switch (params[0]) {
                    case "add" -> this.add(params);
                    case "update" -> this.update(params);
                    case "remove" -> this.remove(params);
                    case "list" -> this.list(params);
                    case "filter" -> this.filter(params);
                    case "help" -> this.instructions();
                    case "exit" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid command");
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }

    }

    private void list(String[] params) {
        String entity = params[1];
        switch (entity) {
            case "players" -> {
                PlayersDto players = restTemplate.getForObject(playerUrl, PlayersDto.class);
                assert players != null;
                players.getPlayers().forEach(System.out::println);
            }
            case "games" -> {
                GamesDto games = restTemplate.getForObject(gameUrl, GamesDto.class);
                assert games != null;
                games.getGames().forEach(System.out::println);
            }
            case "computers" -> {
                ComputersDto computers = restTemplate.getForObject(computerUrl, ComputersDto.class);
                assert computers != null;
                computers.getComputers().forEach(System.out::println);
            }
            case "zones" -> {
                ZonesDto zones = restTemplate.getForObject(zoneUrl, ZonesDto.class);
                assert zones != null;
                zones.getZones().forEach(System.out::println);
            }
            case "installations" -> {
                InstallationsDto installations = restTemplate.getForObject(installedUrl, InstallationsDto.class);
                assert installations != null;
                installations.getInstallations().forEach(System.out::println);
            }
            default -> System.out.println("Invalid command");
        }
    }

    private void remove(String[] params) throws Exception {
        String entity = params[1];
        switch (entity) {
            case "player" -> {
                if (params.length != 3) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                try {
                    restTemplate.delete(playerUrl + "/{id}", id);
                    System.out.println("Player deleted successfully!");
                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot remove player!");
                }
            }
            case "game" -> {
                if (params.length != 3) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                try {
                    restTemplate.delete(gameUrl + "/{id}", id);
                    System.out.println("Game deleted successfully!");
                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot remove game!");
                }
            }
            case "computer" -> {
                if (params.length != 3) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                try {
                    restTemplate.delete(computerUrl + "/{id}", id);
                    System.out.println("Computer deleted successfully!");
                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot remove computer!");
                }
            }
            case "zone" -> {
                if (params.length != 3) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                try {
                    restTemplate.delete(zoneUrl + "/{id}", id);
                    System.out.println("Zone deleted successfully!");
                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot remove zone!");
                }
            }
            case "installed" -> {
                if (params.length != 3) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                try {
                    restTemplate.delete(installedUrl + "/{id}", id);
                    System.out.println("Installed deleted successfully!");
                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot remove zone!");
                }
            }
            default -> System.out.println("Invalid command");
        }
    }

    private void update(String[] params) throws Exception {String entity = params[1];
        switch (entity) {
            case "player" -> {
                if (params.length != 6) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                PlayerDto player = new PlayerDto(params[3], params[4], params[5]);
                try {
                    restTemplate.put(playerUrl + "/{id}", player, id);
                    System.out.println("Player updated successfully!");

                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot update this player!");
                }
            }
            case "game" -> {
                if (params.length != 8) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                GameDto game = new GameDto(params[3], params[4], Integer.parseInt(params[5]), Double.parseDouble(params[6]), Long.parseLong(params[7]));
                try {
                    restTemplate.put(gameUrl + "/{id}", game, id);
                    System.out.println("Game updated successfully!");

                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot update this game!");
                }

            }
            case "computer" -> {
                if (params.length != 6) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                ComputerDto computer = new ComputerDto(Long.parseLong(params[3]), params[4], params[5]);
                try {
                    restTemplate.put(computerUrl + "/{id}", computer, id);
                    System.out.println("Computer updated successfully!");

                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot update this computer!");
                }
            }
            case "zone" -> {
                if (params.length != 6) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                ZoneDto zone = new ZoneDto(params[3], params[4], Integer.parseInt(params[5]));
                try {
                    restTemplate.put(zoneUrl + "/{id}", zone, id);
                    System.out.println("Zone updated successfully!");

                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot update this zone!");
                }
            }
            case "installed" -> {
                if (params.length != 6) throw new Exception("Incorrect number of parameters");
                Long id = Long.parseLong(params[2]);
                InstalledDto dto = new InstalledDto(Long.parseLong(params[3]), Long.parseLong(params[4]), Integer.parseInt(params[5]));
                try {
                    restTemplate.put(installedUrl + "/{id}", dto, id);
                    System.out.println("Installed updated successfully!");

                } catch (HttpStatusCodeException ex) {
                    System.out.println("Cannot update this zone!");
                }
            }
            default -> System.out.println("Invalid command");
        }


    }

    public void add(String[] params) throws Exception {
        String entity = params[1];
        switch (entity) {
            case "player" -> {
                if (params.length != 5) throw new Exception("Incorrect number of parameters");
                PlayerDto player = new PlayerDto(params[2], params[3], params[4]);
                try{
                    restTemplate.postForObject(playerUrl, player, PlayerDto.class);
                    System.out.println("Player added successfully!");
                }catch (HttpStatusCodeException e){
                    System.out.println("Cannot add player!");
                }
            }
            case "game" -> {
                if (params.length != 7) throw new Exception("Incorrect number of parameters");
                GameDto game = new GameDto(params[2], params[3], Integer.parseInt(params[4]),
                        Double.parseDouble(params[5]), Long.parseLong(params[6]));
                try{
                    restTemplate.postForObject(gameUrl, game, GameDto.class);
                    System.out.println("Game added successfully!");
                }catch (HttpStatusCodeException e){
                    System.out.println("Cannot add game!");
                }
            }
            case "computer" -> {
                if (params.length != 5) throw new Exception("Incorrect number of parameters");
                ComputerDto computer = new ComputerDto(Long.parseLong(params[2]), params[3], params[4]);
                try{
                    restTemplate.postForObject(computerUrl, computer, ComputerDto.class);
                    System.out.println("Computer added successfully!");
                }catch (HttpStatusCodeException e){
                    System.out.println("Cannot add computer!");
                }
            }
            case "zone" -> {
                if (params.length != 5) throw new Exception("Incorrect number of parameters");
                ZoneDto zone = new ZoneDto(params[2], params[3], Integer.parseInt(params[4]));
                try{
                    restTemplate.postForObject(zoneUrl, zone, ZoneDto.class);
                    System.out.println("Zone added successfully!");
                }catch (HttpStatusCodeException e){
                    System.out.println("Cannot add zone!");
                }
            }
            case "installed" -> {
                if (params.length != 5) throw new Exception("Incorrect number of parameters");
                InstalledDto dto = new InstalledDto(Long.parseLong(params[2]), Long.parseLong(params[3]), Integer.parseInt(params[4]));
                try{
                    restTemplate.postForObject(installedUrl, dto, InstalledDto.class);
                    System.out.println("Installed added successfully!");
                }catch (HttpStatusCodeException e){
                    System.out.println("Cannot add installed!");
                }
            }

            default -> System.out.println("Bad command");
        }
    }

    private void filter(String[] params){
        String entity = params[1];
        switch (entity) {
            case "players" -> {
                String name = params[2];
                PlayersDto players = restTemplate.getForObject(playerUrl + "/filter/{name}" , PlayersDto.class, name);
                assert players != null;
                players.getPlayers().forEach(System.out::println);
            }
            case "zones" -> {
                String theme = params[2];
                ZonesDto zones = restTemplate.getForObject(zoneUrl + "/filter/{theme}" , ZonesDto.class, theme);
                assert zones != null;
                zones.getZones().forEach(System.out::println);
            }
            case "games" -> {
                Long playerId = Long.parseLong(params[2]);
                GamesDto games = restTemplate.getForObject(gameUrl + "/filter/{id}" , GamesDto.class, playerId);
                assert games != null;
                games.getGames().forEach(System.out::println);
            }
            case "computers" -> {
                Long zoneId = Long.parseLong(params[2]);
                ComputersDto computers = restTemplate.getForObject(computerUrl + "/filter/{id}" , ComputersDto.class, zoneId);
                assert computers != null;
                computers.getComputers().forEach(System.out::println);
            }
            case "installations" -> {
                Long gameId = Long.parseLong(params[2]);
                InstallationsDto installations = restTemplate.getForObject(installedUrl + "/filter/{gameId}", InstallationsDto.class, gameId);
                assert installations != null;
                installations.getInstallations().forEach(System.out::println);
            }
            default -> System.out.println("Bad command");
        }

    }



}
