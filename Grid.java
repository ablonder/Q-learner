import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author aeck
 */
public class Grid {
    /** The height of the {@link Grid}. */
    public static final int HEIGHT = 6;
    
    /** The width of the {@link Grid}. */
    public static final int WIDTH = 15;
    
    /** The locations of the obstacles in the {@link Grid}. */
    private static final List<State> OBSTACLES = createObstacles();
    
    /** The final state of the {@link Grid}. */
    public static final State ABSORBING_STATE = new State(-1, -1);
    
    /** The default reward for most actions. */
    private static final double DEFAULT_REWARD = -0.1;
    
    /** The {@link Random} number generator to use for sampling next states. */
    private final Random rng;
    
    /** The list of all possible {@link State}s. */
    public final List<State> states;
    
    /** The list of terminal {@link State}s that preceed the 
     * {@see ABSORBING_STATE}. */
    public final List<State> terminalStates;
    
    /** The list of all possible actions. */
    public final List<String> actions;
    
    /** The transition function. */
    private final Map<State, Map<String, Map<State, Double>>> transitions;
    
    /** The reward function. */
    private final Map<State, Map<String, Double>> rewards;
    
    /**
     * Creates the list of obstacles.
     * 
     * @return The list of obstacles
     */
    private static List<State> createObstacles() {
        List<State> ret = new ArrayList<State>();
        
        ret.add(new State(2, 2));
        ret.add(new State(2, 3));
        ret.add(new State(3, 2));
        ret.add(new State(3, 3));
        ret.add(new State(6, 2));
        ret.add(new State(6, 3));
        
        return ret;
    }

    /**
     * Constructs a new {@link Grid}.
     */
    public Grid() {
        // create the colletions
        states = new ArrayList<State>();
        terminalStates = new ArrayList<State>();
        actions = new ArrayList<String>();
        transitions = new HashMap<State, Map<String, Map<State, Double>>>();
        rewards = new HashMap<State, Map<String, Double>>();
        
        // create the objects
        rng = new Random(12345);
        
        // fill the lists and maps
        createStates();
        createActions();
        createTransitions();
        createRewards();
    }

    /**
     * Fills {@code states} and {@code terminalStates} with the necessary 
     * information.
     */
    private void createStates() {
        State state;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                state = new State(x, y);
                
                if (!OBSTACLES.contains(state)) {
                    states.add(state);
                }
            }
        }
        states.add(ABSORBING_STATE);

        terminalStates.add(new State(WIDTH - 1, HEIGHT - 2));
        terminalStates.add(new State(WIDTH - 1, HEIGHT - 1));
    }

    /**
     * Fills {@code actions} with the necessary information.
     */
    private void createActions() {
        actions.add("up");
        actions.add("down");
        actions.add("left");
        actions.add("right");
    }

    /**
     * Fills {@code transitions} with the necessary information.
     */
    private void createTransitions() {
        for (State state : states) {
            transitions.put(state, new HashMap<String, Map<State, Double>>());
            
            for (String action : actions) {
                transitions.get(state).put(action, new HashMap<State, Double>());
                
                for (State nextState : states) {
                    double prob = transitionProb(state, action, nextState);
                    transitions.get(state).get(action).put(nextState, prob);
                }
            }
        }
    }

    /**
     * Fills {@code rewards} with the necessary information.
     */
    private void createRewards() {
        for (State state : states) {
            rewards.put(state, new HashMap<String, Double>());
            
            for (String action : actions) {
                if (state.x == Grid.WIDTH - 1 && state.y == Grid.HEIGHT - 1) {
                    rewards.get(state).put(action, 1.0);
                } else if (state.x == Grid.WIDTH - 1 && state.y == Grid.HEIGHT - 2) {
                    rewards.get(state).put(action, -1.0);
                } else if (state.x == ABSORBING_STATE.x && state.y == ABSORBING_STATE.y) {
                    rewards.get(state).put(action, 0.0);
                }else {
                    rewards.get(state).put(action, DEFAULT_REWARD);
                }
            }
        }
    }

    /**
     * Computes the probability of transitioning from state to nextState 
     * after taking action
     * 
     * @param state The current {@link State}
     * @param action The chosen action
     * @param nextState The resulting next {@link State}
     * 
     * @return A probability
     */
    private double transitionProb(State state, String action, State nextState) {
        // terminal states are absorbing
        if (terminalStates.contains(state) || ABSORBING_STATE.equals(state)) {
            return ABSORBING_STATE.equals(nextState) ? 1.0 : 0.0;
        }

        State expected = null;
        State left = null;
        State right = null;
        
        if ("up".equals(action)) {
            expected = new State(state.x, state.y + 1);
            left = new State(state.x - 1, state.y);
            right = new State(state.x + 1, state.y);
        } else if ("down".equals(action)) {
            expected = new State(state.x, state.y - 1);
            left = new State(state.x + 1, state.y);
            right = new State(state.x - 1, state.y);
        } else if ("left".equals(action)) {
            expected = new State(state.x - 1, state.y);
            left = new State(state.x, state.y - 1);
            right = new State(state.x, state.y + 1);
        } else if ("right".equals(action)) {
            expected = new State(state.x + 1, state.y);
            left = new State(state.x, state.y + 1);
            right = new State(state.x, state.y - 1);
        }

        if (!states.contains(expected)) {
            expected = state;
        }
        
        if (!states.contains(left)) {
            left = state;
        }
        
        if (!states.contains(right)) {
            right = state;
        }

        double prob = 0.0;
        if (expected.x == nextState.x && expected.y == nextState.y) {
            prob += 0.8;
        }
        
        if (left.x == nextState.x && left.y == nextState.y) {
            prob += 0.1;
        }
        
        if (right.x == nextState.x && right.y == nextState.y) {
            prob += 0.1;
        }

        return prob;
    }

    /**
     * Generates the starting {@link State}. 
     * 
     * @return The starting {@link State}.
     */
    public State generateStartState() {
        return new State(0, 0);
    }

    /**
     * Generates a next state after taking an action in a given state.
     * 
     * @param state The current {@link State}
     * @param action The chosen action
     * 
     * @return A resulting next {@link State}
     */
    public State generateNextState(State state, String action) {
        List<State> validNext = new ArrayList<State>();
        
        for (State nextState : states) {
            if (transitions.get(state).get(action).get(nextState) > 0) {
                validNext.add(nextState);
            }
        }

        double rand = rng.nextDouble();
        double prob;
        for (State nextState : validNext) {
            prob = transitions.get(state).get(action).get(nextState);
            if (rand < prob) {
                return nextState;
            } else {
                rand -= prob;
            }
        }

        // make sure we return a state (due to Pythons float precision)
        return validNext.get(validNext.size() - 1);
    }

    /**
     * Generates a reward earned for taking an action in a state.
     * 
     * @param state The current {@link State}
     * @param action The chosen action
     * @return 
     */
    public double generateReward(State state, String action) {
        return rewards.get(state).get(action);
    }
}
