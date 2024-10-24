package racingcar.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.constant.ErrorMessage;
import racingcar.constant.MovementCondition;

public class GameStatus {
    private static final int MOVEMENT_BASE = 0;
    private static final int ATTEMPTS_BASE = 0;
    private static final int MINIMUM_TOTAL_ATTEMPTS = 1;

    private final Map<Car, Integer> racers;
    private final int totalAttempts;
    private int currentAttempts;

    private GameStatus(final int totalAttempts, final List<Car> racers) {
        validateTotalAttemptsIsPositiveNumber(totalAttempts);

        this.totalAttempts = totalAttempts;
        this.currentAttempts = ATTEMPTS_BASE;

        this.racers = new LinkedHashMap<>();
        for (Car racer : racers) {
            this.racers.put(racer, MOVEMENT_BASE);
        }
    }

    public static GameStatus from(final int totalAttempts, final List<Car> racers) {
        return new GameStatus(totalAttempts, racers);
    }

    private void validateTotalAttemptsIsPositiveNumber(final int totalAttempts) {
        if (totalAttempts < MINIMUM_TOTAL_ATTEMPTS) {
            throw new IllegalArgumentException(ErrorMessage.ATTEMPTS_SIGN_POSITIVE.getMessage());
        }
    }

    public void attempt() {
        ++currentAttempts;
    }

    public boolean isEnd() {
        return currentAttempts == totalAttempts;
    }

    public void updateRacerByMovementCondition(Car racer, MovementCondition movementCondition) {
        if (movementCondition == MovementCondition.FORWARD) {
            racers.put(racer, racers.get(racer) + Car.MOVE_FORWARD_SPEED);
        }
    }

    public Map<Car, Integer> getRacers() {
        return Collections.unmodifiableMap(racers);
    }
}
