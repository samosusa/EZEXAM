import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FootballGame implements NamedPropertyChangeSubject {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoal;
    private int awayTeamGoal;

    private PropertyChangeSupport property; //  Declaration

    public FootballGame(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoal = 0;
        this.awayTeamGoal = 0;
        this.property = new PropertyChangeSupport(this);    // constructor as well
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void scoreGoal(String team) {
        if (team.equals(homeTeam)) {
            int oldValue = homeTeamGoal;
            homeTeamGoal++;
            int newValue = homeTeamGoal;
            property.firePropertyChange(team, oldValue, newValue);

        } else if (team.equals(awayTeam)) {
            int oldValue = awayTeamGoal;
            awayTeamGoal++;
            int newValue = awayTeamGoal;
            property.firePropertyChange(team, oldValue, newValue);
        }
    }



    public String getScore() {
        return homeTeamGoal + " - " + awayTeamGoal;
    }

    public String endGame() {
        return getScore();
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
