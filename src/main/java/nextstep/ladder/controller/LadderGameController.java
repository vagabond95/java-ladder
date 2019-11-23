package nextstep.ladder.controller;

import nextstep.ladder.domain.game.Game;
import nextstep.ladder.domain.game.GameResults;
import nextstep.ladder.domain.line.LineResults;
import nextstep.ladder.domain.line.Lines;
import nextstep.ladder.domain.participant.Participants;
import nextstep.ladder.view.LadderGameView;

public class LadderGameController {

    public static void start(LadderGameView view) {
        try {
            Participants participants = new Participants(view.getNames());
            Lines lines = new Lines(view.getHeight(), participants.size());
            LineResults results = new LineResults(view.getLineResults());

            Game ladderGame = Game.ready(participants, lines, results);
            view.showLines(ladderGame);

            GameResults gameResults = ladderGame.play(view.getTargetParticipant());
            view.showGameResults(gameResults);
        } catch (Exception exception) {
            view.showText(exception.getMessage());
            start(view);
        }
    }
}
