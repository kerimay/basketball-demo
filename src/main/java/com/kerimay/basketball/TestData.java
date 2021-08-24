package com.kerimay.basketball;

import com.kerimay.basketball.domain.HeadCoach;
import com.kerimay.basketball.domain.Team;
import com.kerimay.basketball.domain.enums.Conference;
import com.kerimay.basketball.repository.HeadCoachRepository;
import com.kerimay.basketball.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@Transactional
public class TestData implements ApplicationListener<ApplicationReadyEvent> {

    private final TeamRepository teamRepository;
    private final HeadCoachRepository headCoachRepository;

    @Autowired
    public TestData(final TeamRepository teamRepository,
                    final HeadCoachRepository headCoachRepository) {
        this.teamRepository = teamRepository;
        this.headCoachRepository = headCoachRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        populateTestData();
    }

    public void populateTestData() {
        createTeams();
    }

    private void createTeams() {
        createTeam("Free Agent", Conference.FREE, null);
        createTeam("Atlanta Hawks", Conference.EAST, "Nate McMillan");
        createTeam("Boston Celtics", Conference.EAST, "Ime Udoka");
        createTeam("Brooklyn Nets", Conference.EAST, "Steve Nash");
        createTeam("Charlotte Hornets", Conference.EAST, "James Borrego");
        createTeam("Chicago Bulls", Conference.EAST, "Billy Donovan");
        createTeam("Cleveland Cavaliers", Conference.EAST, "Larry Drew");
        createTeam("Dallas Mavericks", Conference.WEST, "Jason Kidd");
        createTeam("Denver Nuggets", Conference.WEST, "Michael Malone");
        createTeam("Detroit Pistons", Conference.EAST, "Dwane Casey");
        createTeam("Golden State Warriors", Conference.WEST, "Steve Kerr");
        createTeam("Houston Rockets", Conference.WEST, "Stephen Silas");
        createTeam("Indiana Pacers", Conference.EAST, "Rick Carlisle");
        createTeam("Los Angeles Clippers", Conference.WEST, "Tyronn Lue");
        createTeam("Los Angeles Lakers", Conference.WEST, "Frank Vogel");
        createTeam("Memphis Grizzlies", Conference.WEST, "Taylor Jenkins");
        createTeam("Miami Heat", Conference.EAST, "Erik Spoelstra");
        createTeam("Milwaukee Bucks", Conference.EAST, "Mike Budenholzer");
        createTeam("Minnesota Timberwolves", Conference.WEST, "Chris Finch");
        createTeam("New Orleans Pelicans", Conference.WEST, "Willie Green");
        createTeam("New York Knicks", Conference.EAST, "Tom Thibodeau");
        createTeam("Oklahoma City Thunder", Conference.WEST, "Mark Daigneault");
        createTeam("Orlando Magic", Conference.EAST, "Jamahl Mosley");
        createTeam("Philadelphia 76ers", Conference.EAST, "Doc Rivers");
        createTeam("Phoenix Suns", Conference.WEST, "Monty Williams");
        createTeam("Portland Trail Blazers", Conference.WEST, "Chauncey Billups");
        createTeam("Sacramento Kings", Conference.WEST, "Luke Walton");
        createTeam("San Antonio Spurs", Conference.WEST, "Gregg Popovich");
        createTeam("Toronto Raptors", Conference.EAST, "Nick Nurse");
        createTeam("Utah Jazz", Conference.WEST, "Quin Snyder");
        createTeam("Washington Wizards", Conference.EAST, "Wes Unseld Jr.");
        createTeam("Anadolu Efes", Conference.EUROPE, "Ergin Ataman");
    }

    private void createTeam(String name, Conference conference, String headCoachName) {
        HeadCoach headCoach = headCoachRepository.getHeadCoachByName(headCoachName);
        teamRepository.save(
                Team.builder()
                        .name(name)
                        .conference(conference)
                        .headCoach(headCoach)
                        .build());
    }
}
