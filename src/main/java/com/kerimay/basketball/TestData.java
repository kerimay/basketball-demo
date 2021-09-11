package com.kerimay.basketball;

import com.kerimay.basketball.controller.dto.GameDTO;
import com.kerimay.basketball.domain.*;
import com.kerimay.basketball.domain.enums.Conference;
import com.kerimay.basketball.repository.*;
import com.kerimay.basketball.service.GameService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
@Transactional
public class TestData implements ApplicationListener<ApplicationReadyEvent> {

    private final TeamRepository teamRepository;
    private final HeadCoachRepository headCoachRepository;
    private final PlayerRepository playerRepository;
    private final StatRepository statRepository;
    private final GameRepository gameRepository;
    private final GameService gameService;

    @Autowired
    public TestData(final TeamRepository teamRepository,
                    final HeadCoachRepository headCoachRepository,
                    final PlayerRepository playerRepository,
                    final StatRepository statRepository,
                    final GameRepository gameRepository,
                    final GameService gameService) {
        this.teamRepository = teamRepository;
        this.headCoachRepository = headCoachRepository;
        this.playerRepository = playerRepository;
        this.statRepository = statRepository;
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        populateTestData();
    }

    public void populateTestData() throws Exception {
        createTeams();
        createPlayers();
        createDummyStats();
        createMatches();
    }

    private void createPlayers() {
        createPlayer("Jayson", "Tatum", "American", 3);
        createPlayer("Jaylen", "Brown", "American", 3);
        createPlayer("Alperen", "Şengün", "Turkish", 12);
        createPlayer("Kevin", "Durant", "American", 4);
        createPlayer("Trae", "Young", "American", 2);
        createPlayer("Luka", "Doncic", "Slovenian", 8);
        createPlayer("Nikola", "Jokic", "Serbian", 9);
        createPlayer("Stephen", "Curry", "American", 11);
        createPlayer("LeBron", "James", "American", 15);
        createPlayer("Shane", "Larkin", "Turkish", 32);
    }

    private void createPlayer(String name, String lastName, String nationality, int teamId) {
        Optional<Team> team = teamRepository.getTeamById(teamId);
        Player player = Player.builder()
                .name(name)
                .lastName(lastName)
                .nationality(nationality)
                .team(team.orElseGet(teamRepository::getFreeAgent))
                .build();
        playerRepository.save(player);
    }

    private void createTeams() {
        createFreeAgent();
        createTeam("Atlanta Hawks", Conference.EAST, "Nate", "McMillan");
        createTeam("Boston Celtics", Conference.EAST, "Ime", "Udoka");
        createTeam("Brooklyn Nets", Conference.EAST, "Steve", "Nash");
        createTeam("Charlotte Hornets", Conference.EAST, "James", "Borrego");
        createTeam("Chicago Bulls", Conference.EAST, "Billy", "Donovan");
        createTeam("Cleveland Cavaliers", Conference.EAST, "Larry", "Drew");
        createTeam("Dallas Mavericks", Conference.WEST, "Jason", "Kidd");
        createTeam("Denver Nuggets", Conference.WEST, "Michael", "Malone");
        createTeam("Detroit Pistons", Conference.EAST, "Dwayne", "Casey");
        createTeam("Golden State Warriors", Conference.WEST, "Steve", "Kerr");
        createTeam("Houston Rockets", Conference.WEST, "Stephen", "Silas");
        createTeam("Indiana Pacers", Conference.EAST, "Rick", "Carlisle");
        createTeam("Los Angeles Clippers", Conference.WEST, "Tyronn", "Lue");
        createTeam("Los Angeles Lakers", Conference.WEST, "Frank", "Vogel");
        createTeam("Memphis Grizzlies", Conference.WEST, "Taylor", "Jenkins");
        createTeam("Miami Heat", Conference.EAST, "Erik", "Spoelstra");
        createTeam("Milwaukee Bucks", Conference.EAST, "Mike", "Budenholzer");
        createTeam("Minnesota Timberwolves", Conference.WEST, "Chris", "Finch");
        createTeam("New Orleans Pelicans", Conference.WEST, "Willie", "Green");
        createTeam("New York Knicks", Conference.EAST, "Tom", "Thibodeau");
        createTeam("Oklahoma City Thunder", Conference.WEST, "Mark", "Daigneault");
        createTeam("Orlando Magic", Conference.EAST, "Jamahl", "Mosley");
        createTeam("Philadelphia 76ers", Conference.EAST, "Doc", "Rivers");
        createTeam("Phoenix Suns", Conference.WEST, "Monty", "Williams");
        createTeam("Portland Trail Blazers", Conference.WEST, "Chauncey", "Billups");
        createTeam("Sacramento Kings", Conference.WEST, "Luke", "Walton");
        createTeam("San Antonio Spurs", Conference.WEST, "Gregg", "Popovich");
        createTeam("Toronto Raptors", Conference.EAST, "Nick", "Nurse");
        createTeam("Utah Jazz", Conference.WEST, "Quin", "Snyder");
        createTeam("Washington Wizards", Conference.EAST, "Wes", "Unseld Jr.");
        createTeam("Anadolu Efes", Conference.EUROPE, "Ergin", "Ataman");
    }

    private void createTeam(String name, Conference conference, String headCoachName, String headCoachLastName) {
        HeadCoach headCoach = HeadCoach.builder().name(headCoachName).lastName(headCoachLastName).build();
        headCoach = headCoachRepository.save(headCoach);
        Team team = Team.builder()
                .name(name)
                .conference(conference)
                .headCoach(headCoach)
                .build();
        teamRepository.save(team);
        headCoach.setTeam(team);

    }

    private void createFreeAgent() {
        teamRepository.save(Team.builder()
                .name("Free Agent")
                .conference(Conference.FREE)
                .build());
    }

    private void createDummyStats() throws Exception {
        createDummyStat(Duration.ofMinutes(48).plusSeconds(42).toMinutes(), 31, 7, 8, 2, 1, 3, 25, 14, 4, 3, 0, 0, 1);
        createDummyStat(Duration.ofMinutes(42).plusSeconds(17).toMinutes(), 27, 4, 6, 4, 2, 1, 22, 11, 3, 1, 2, 2, 1);
        createDummyStat(Duration.ofMinutes(23).plusSeconds(35).toMinutes(), 27, 11, 5, 1, 4, 2, 18, 11, 3, 1, 6, 4, 3);
    }

    private void createDummyStat(double playedMinutes, double points, double rebounds, double assists, double steals, double blocks, double turnovers,
                                 double fga, double fgm, double tpa, double tpm, double fta, double ftm, int playerId) throws Exception {
        statRepository.save(Stat.builder()
                .playedMinutes(playedMinutes)
                .points(points)
                .rebounds(rebounds)
                .assists(assists)
                .steals(steals)
                .blocks(blocks)
                .turnovers(turnovers)
                .fieldGoalAttempt(fga)
                .fieldGoalMade(fgm)
                .threePointAttempt(tpa)
                .threePointMade(tpm)
                .freeThrowAttempt(fta)
                .freeThrowMade(ftm)
                .player(playerRepository.getPlayerById(playerId).orElseThrow(() -> new Exception("Invalid stat because of invalid player")))
                .build());
    }

    private void createMatches() {
        createMatch(3, 14, 114, 97, 2);
        createMatch(9, 12, 84, 88, 3);
    }

    private void createMatch(int team1Id, int team2Id, int team1Score, int team2Score, int playerOfTheGame) {
        gameService.updateTeamsByWinAndLose(GameDTO.builder()
                .homeTeam(team1Id)
                .awayTeam(team2Id)
                .homeTeamScore(team1Score)
                .awayTeamScore(team2Score)
                .playerOfTheGame(playerOfTheGame)
                .build());
        gameRepository.save(Game.builder()
                .homeTeam(teamRepository.getTeamById(team1Id).orElseThrow(() -> new NullPointerException("Team1 not found")))
                .awayTeam(teamRepository.getTeamById(team2Id).orElseThrow(() -> new NullPointerException("Team2 not found")))
                .homeTeamScore(team1Score)
                .awayTeamScore(team2Score)
                .playerOfTheGame(playerRepository.getPlayerById(playerOfTheGame).orElseThrow(() -> new NullPointerException("Player not found")))
                .timestamp(LocalDateTime.now())
                .build());
    }
}
